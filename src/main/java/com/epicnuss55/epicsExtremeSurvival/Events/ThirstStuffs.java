package com.epicnuss55.epicsExtremeSurvival.Events;

import com.epicnuss55.epicsExtremeSurvival.EpicsExtremeSurvival;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.Difficulty;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
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
public class ThirstStuffs {

    //*---------------EVENTS---------------*\\
    //wont heal unless water is also high enough
    @SubscribeEvent
    public void Heal(LivingHealEvent event) {
        if (event.getEntity() instanceof PlayerEntity) {
            if (thirstValue < 8.5f) {
                event.setCanceled(true);
            } else {
                Dehydration = Dehydration + REGEN;
                event.setCanceled(false);
            }
        }
    }

    //thirst logic
    @SubscribeEvent
    public void TDehydration(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity && !((PlayerEntity) event.getEntityLiving()).isCreative()) {
            if (thirstValue != 0 && event.getEntityLiving().getEntityWorld().getDifficulty() != Difficulty.PEACEFUL)
                dehydrator((PlayerEntity) event.getEntityLiving());

            if (thirstValue == 0)
                dehydrated = true;

            if (thirstValue < 3f)
                dehydrationEvent((PlayerEntity) event.getEntityLiving());

            animate();

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
        if (event.getEntity().equals(Minecraft.getInstance().player)) {
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


    //*---------------LOGIC---------------*\\
    //render logic
    public static void renderThirstBar(MatrixStack stack, int x, int y) {
        int fullAmount = (int) thirstValue;
        boolean drawHalf = fullAmount != thirstValue;

        int iterator = 0;

        if (Minecraft.getInstance().player.areEyesInFluid(FluidTags.WATER) || Minecraft.getInstance().player.getAir() < 300) {
            y = y-9;
        }

        while (iterator != fullAmount) {
            if (animate) {
                if ((iterator % 2) == 0) {
                    draw(stack, x - (iterator * 8), y + 1, 0, 0, 9, 9);
                } else draw(stack, x - (iterator * 8), y - 1, 0, 0, 9, 9);
            } else draw(stack, x - (iterator * 8), y, 0, 0, 9, 9);
            iterator = iterator + 1;
        }
        if (drawHalf) {
            if (animate) {
                if ((iterator % 2) == 0) {
                    draw(stack, x - (iterator * 8), y + 2, 20, 0, 9, 9);
                } else draw(stack, x - (iterator * 8), y - 2, 20, 0, 9, 9);
            } else draw(stack, x - (iterator * 8), y, 10, 0, 9, 9);
            iterator = iterator + 1;
        }
        while (iterator != 10) {
            if (animate) {
                if ((iterator % 2) == 0) {
                    draw(stack, x - (iterator * 8), y + 1, 30, 0, 9, 9);
                } else draw(stack, x - (iterator * 8), y - 1, 30, 0, 9, 9);
            } else draw(stack, x - (iterator * 8), y, 30, 0, 9, 9);
            iterator = iterator + 1;
        }
    }

    //hunger icon bobbing
    private static int animTick = 0;
    private static int animating = 0;
    private static boolean animate = false;
    private static void animate() {

        if (!animate) {
            animTick++;
            if (thirstValue <= 10f && thirstValue >= 9.5f && animTick > 640) {
                animate = true;
                animTick = 0;
            } else if (thirstValue <= 9f && thirstValue >= 8f && animTick > 200) {
                animate = true;
                animTick = 0;
            } else if (thirstValue <= 7.5f && thirstValue >= 6.5f && animTick > 160) {
                animate = true;
                animTick = 0;
            } else if (thirstValue <= 6f && thirstValue >= 5f && animTick > 80) {
                animate = true;
                animTick = 0;
            } else if (thirstValue <= 4.5f && thirstValue >= 3.5f && animTick > 20) {
                animate = true;
                animTick = 0;
            } else if (thirstValue <= 3f && thirstValue >= 2f && animTick > 10) {
                animate = true;
                animTick = 0;
            } else if (thirstValue <= 1.5f && thirstValue >= 0f && animTick > 5) {
                animate = true;
                animTick = 0;
            }
        }

        if (animate) {
            animating++;
            if (animating > 5) {
                animate = false;
                animating = 0;
            }
        }

/*
        if (animating > 5)
            animating = 0;

        if (animating == 0)
            animTick++;

        if (((thirstValue >= 0 && thirstValue <= 2.5) && (animTick > 20)) || (animating != 0)) {
            animate = true;
            animTick = 0;
            animating++;
        } else if ((thirstValue >= 3 && thirstValue <= 6.5) && (animTick > 120) && (animTick == 0)){
            animate = true;
            animTick = 0;
            animating++;
        } else if (animTick > 240) {
            animate = true;
            animTick = 0;
            animating++;
        }
 */
    }

    //render simplifier lol
    private static void draw(MatrixStack stack, int ScreenXPos, int ScreenYPos, int textureXStartPos, int textureYStartPos, int textureXEndPos, int textureYEndPos) {
        Minecraft.getInstance().ingameGUI.blit(stack, ScreenXPos, ScreenYPos, textureXStartPos, textureYStartPos, textureXEndPos, textureYEndPos);
    }

    public static float thirstValue = 10f;
    public static int damageTick = 0;

    //when thirst value is below 3 bars, fire this
    public static void dehydrationEvent(PlayerEntity player) {
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

    //Thirst adder
    public static void AddThirst(float added) {
        float finalVal = added + thirstValue;
        if (finalVal <= 10) {
            thirstValue = finalVal;
        } else thirstValue = 10f;
    }

    //Thirst save system
    public static final String THIRST_NBT = "thirstval";
    public static final String DEHYDRATION_NBT = "dehydrationval";

    public static void read(CompoundNBT compound) {
        thirstValue = compound.getFloat(THIRST_NBT);
        Dehydration = compound.getFloat(DEHYDRATION_NBT);
    }

    public static void write(CompoundNBT compound) {
        compound.putFloat(THIRST_NBT, thirstValue);
        compound.putDouble(DEHYDRATION_NBT, Dehydration);
    }
}
