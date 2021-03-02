package com.epicnuss55.epicsExtremeSurvival.Events;

import com.epicnuss55.epicsExtremeSurvival.EpicsExtremeSurvival;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.Difficulty;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EpicsExtremeSurvival.MODID)
public class ThirstStuffs extends FoodStats {

    //*EVENTS*\\
    //wont heal unless water is also high enough
    @SubscribeEvent
    public void Heal(LivingHealEvent event) {
        if (event.getEntity() instanceof PlayerEntity) {
            if (thirstValue < 8.5f) {
                EpicsExtremeSurvival.LOGGER.info("Heal Cancelled");
                event.setCanceled(true);
            } else {
                Dehydration = Dehydration + REGEN;
                EpicsExtremeSurvival.LOGGER.info("Healing");
                event.setCanceled(false);
            }
        }
    }

    //thirst logic
    @SubscribeEvent
    public void TDehydration(TickEvent.PlayerTickEvent event) {
        if (event.type == TickEvent.Type.PLAYER && event.phase == TickEvent.Phase.END && !event.player.isCreative()) {
            if (thirstValue != 0 && event.player.getEntityWorld().getDifficulty() != Difficulty.PEACEFUL)
                dehydrator(event.player);

            if (thirstValue == 0)
                dehydrated = true;

            if (thirstValue < 3f)
                dehydrationEvent(event.player);

        }
    }

    //If drinks any bottled liquids then adds half a bar back
    @SubscribeEvent
    public void DrinkWater(LivingEntityUseItemEvent.Finish event) {
        if (event.getResultStack().getItem() == Items.GLASS_BOTTLE.getItem() && event.getEntity().getEntityWorld().isRemote()) {
            float updateValue = thirstValue + 0.5f;
            if (updateValue <= 10)
                thirstValue = updateValue;
            Dehydration = 0;
            dehydrated = false;
        }
    }

    //after the player dies and respawns, thirst value resets
    @SubscribeEvent
    public void Respawn(PlayerEvent.PlayerRespawnEvent event) {
        if ((event.getEntity().equals(Minecraft.getInstance().player)) && (thirstValue != 15f)) {
            thirstValue = 10f;
        }
    }

    //Renderer
    @SubscribeEvent
    public void Overlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.FOOD) {
            Minecraft mc = Minecraft.getInstance();
            int y = mc.getMainWindow().getScaledHeight() - ForgeIngameGui.right_height;
            int x = mc.getMainWindow().getScaledWidth() / 2 + 82;
            MatrixStack stack = event.getMatrixStack();
            mc.getTextureManager().bindTexture(new ResourceLocation(EpicsExtremeSurvival.MODID, "textures/gui/overlays.png"));

            renderThirstBar(stack, x, y);

            mc.getTextureManager().bindTexture(AbstractGui.GUI_ICONS_LOCATION);
        }
    }


    //*LOGIC*\\
    //render logic
    public static void renderThirstBar(MatrixStack stack, int x, int y) {
        int fullAmount = (int) thirstValue;
        boolean drawHalf = fullAmount != thirstValue;
        int iterator = 0;

        if (Minecraft.getInstance().player.areEyesInFluid(FluidTags.WATER) || Minecraft.getInstance().player.getAir() < 300) {
            while (iterator != fullAmount) {
                draw(stack, x - (iterator * 8), y-9, 0, 0, 9, 9);
                iterator = iterator + 1;
            }
            if (drawHalf) {
                draw(stack, x - (iterator * 8), y-9, 10, 0, 9, 9);
                iterator = iterator + 1;
            }
            while (iterator != 10) {
                draw(stack, x - (iterator * 8), y-9, 30, 0, 9, 9);
                iterator = iterator + 1;
            }
        } else {
            while (iterator != fullAmount) {
                draw(stack, x - (iterator * 8), y, 0, 0, 9, 9);
                iterator = iterator + 1;
            }
            if (drawHalf) {
                draw(stack, x - (iterator * 8), y, 10, 0, 9, 9);
                iterator = iterator + 1;
            }
            while (iterator != 10) {
                draw(stack, x - (iterator * 8), y, 30, 0, 9, 9);
                iterator = iterator + 1;
            }
        }
    }

    //render simplifier lol
    private static void draw(MatrixStack stack, int ScreenXPos, int ScreenYPos, int textureXStartPos, int textureYStartPos, int textureXEndPos, int textureYEndPos) {
        Minecraft.getInstance().ingameGUI.blit(stack, ScreenXPos, ScreenYPos, textureXStartPos, textureYStartPos, textureXEndPos, textureYEndPos);
    }

    public static float thirstValue = 10f;
    public static int damageTick = 0;

    //when thirst value is below 3 bars, fire this
    public static void dehydrationEvent(PlayerEntity player) {
        EpicsExtremeSurvival.LOGGER.info("dehydration effects");
        if (player.isSprinting()) player.setSprinting(false);
        if (thirstValue == 0) {
            damageTick++;
            if (damageTick == 100) {
                damageTick = 0;
                switch (player.getEntityWorld().getDifficulty()) {
                    case NORMAL:
                    case EASY: if (player.getHealth() < 10f) player.attackEntityFrom(DamageSource.STARVE, 1);
                        break;
                    case HARD: player.attackEntityFrom(DamageSource.STARVE, 1);
                }
            }
        }
    }

    private static double Dehydration = 0;
    private static Boolean dehydrated = false;

    private static final double SWIMMING = 0.01;
    private static final double BLOCK_BREAKING = 1;
    private static final double SPRINTING = 0.05;
    private static final double JUMPING = 2;
    private static final double ATTACKING = 2;
    private static final double TAKING_DAMAGE = 4;
    private static final double DEHYDRATED_DEBUFF = 5;
    private static final double REGEN = 5;

    //reduces your thirst when preforming certain actions (see above)
    private static void dehydrator(PlayerEntity player) {
        if (player.getEntityWorld().getBlockState(player.getPosition()).equals(Blocks.WATER.getDefaultState()) && !dehydrated)
            Dehydration = Dehydration + SWIMMING;

        if (player.isSprinting() && !dehydrated)
            Dehydration = Dehydration + SPRINTING;

        EpicsExtremeSurvival.LOGGER.info(Dehydration);
        if (Dehydration > 50) {
            Dehydration = 0;
            thirstValue = thirstValue - 0.5f;
        }

    }

    @SubscribeEvent
    public void BlockBreak(BlockEvent.BreakEvent event) {
        if (!dehydrated)
            Dehydration = Dehydration + BLOCK_BREAKING;
    }

    @SubscribeEvent
    public void Jump(LivingEvent.LivingJumpEvent event) {
        if (event.getEntity() == Minecraft.getInstance().player && !dehydrated)
            Dehydration = Dehydration + JUMPING;
    }

    @SubscribeEvent
    public void Attack(AttackEntityEvent event) {
        if (!dehydrated)
            Dehydration = Dehydration + ATTACKING;
    }

    @SubscribeEvent
    public void Damage(LivingDamageEvent event) {
        if (event.getEntity() == Minecraft.getInstance().player && !dehydrated) {
            Dehydration = Dehydration + TAKING_DAMAGE;
        }
    }



    //TODO: fix up the saving system
    /*@Override
    public void write(CompoundNBT compound) {
        CompoundNBT thirst = new CompoundNBT();
        thirst.putFloat("thirst", thirstValue);
        thirst.putInt("prevFood", prevFoodLevel);
        compound.put("thirstNBT", thirst);

        super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        CompoundNBT thirst = compound.getCompund("thirstNBT");
        thirstValue = thirst.getFloat("thirst");
        prevFoodLevel = thirst.getInt("prevFood");

        super.read(compound);
    }
     */
}
