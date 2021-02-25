package com.epicnuss55.epicsExtremeSurvival.Events;

import com.epicnuss55.epicsExtremeSurvival.EpicsExtremeSurvival;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
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

@Mod.EventBusSubscriber(modid = EpicsExtremeSurvival.MODID, value = Dist.CLIENT)
public class ThirstStuffs {

    //*EVENTS*\\
    //wont heal unless water is also high enough
    @SubscribeEvent
    public void Heal(LivingHealEvent event) {
        if (thirstValue < 8.5f && event.getEntity().equals(Minecraft.getInstance().player.getEntity())) {
            EpicsExtremeSurvival.LOGGER.info("Heal Cancelled");
            event.setCanceled(true);
        } else {
            Dehydration = Dehydration + REGEN;
            EpicsExtremeSurvival.LOGGER.info("Healing");
            event.setCanceled(false);
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
        }
    }

    //after the player dies and respawns, thirst value resets
    @SubscribeEvent
    public void Respawn(PlayerEvent.PlayerRespawnEvent event) {
        if ((event.getEntity().equals(Minecraft.getInstance().player)) && (thirstValue != 15f)) {
            prevFoodLevel = 20;
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

        }
        if (thirstValue != 0)
            dehydrator(Minecraft.getInstance());
    }


    //*LOGIC*\\
    //render logic
    public static void renderThirstBar(MatrixStack stack, int x, int y) {
        int fullAmount = (int) thirstValue;
        boolean drawHalf = fullAmount != thirstValue;
        int iterator = 0;

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

    //render simplifier lol
    private static void draw(MatrixStack stack, int ScreenXPos, int ScreenYPos, int textureXStartPos, int textureYStartPos, int textureXEndPos, int textureYEndPos) {
        Minecraft.getInstance().ingameGUI.blit(stack, ScreenXPos, ScreenYPos, textureXStartPos, textureYStartPos, textureXEndPos, textureYEndPos);
    }


    public static int prevFoodLevel = 20;
    public static float thirstValue = 10f;
    public static int ticker = 0;


    public static void dehydrationEvent(Minecraft mc) {
        EpicsExtremeSurvival.LOGGER.info("dehydration effects");
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKey(), false);
        if (mc.player.isSprinting()) mc.player.setSprinting(false);
        if (thirstValue == 0 && ticker == 1 && mc.player.getEntityWorld().isRemote) {
            mc.player.getEntity().attackEntityFrom(DamageSource.GENERIC, 4);
            EpicsExtremeSurvival.LOGGER.info("thirst damage");
        }
    }

    private static double Dehydration = 0;

    private static final double SWIMMING = 0.01;
    private static final double BLOCK_BREAKING = 1;
    private static final double SPRINTING = 0.0001;
    private static final double JUMPING = 1;
    private static final double ATTACKING = 2;
    private static final double TAKING_DAMAGE = 5;
    private static final double DEHYDRATED_DEBUFF = 10;
    private static final double REGEN = 2.5;

    //reduces your thirst when preforming certain actions (see above)
    private static void dehydrator(Minecraft mc) {
        if (Minecraft.getInstance().world.getBlockState(mc.player.getPosition()).equals(Blocks.WATER.getDefaultState())) Dehydration = Dehydration + SWIMMING;
        if (mc.player.isSprinting()) Dehydration = Dehydration + SPRINTING;

        EpicsExtremeSurvival.LOGGER.info(Dehydration);
        if (Dehydration > 50) {
            Dehydration = 0;
            thirstValue = thirstValue - 0.5f;
        }
        if (thirstValue < 3f)
            dehydrationEvent(mc);
    }

    @SubscribeEvent
    public void BlockBreak(BlockEvent.BreakEvent event) {
        Dehydration = Dehydration + BLOCK_BREAKING;
    }

    @SubscribeEvent
    public void Jump(LivingEvent.LivingJumpEvent event) {
        if (event.getEntity() == Minecraft.getInstance().player)
            Dehydration = Dehydration + JUMPING;
    }

    @SubscribeEvent
    public void Attack(AttackEntityEvent event) {
        Dehydration = Dehydration + ATTACKING;
    }

    @SubscribeEvent
    public void Damage(LivingDamageEvent event) {
        if (event.getEntity() == Minecraft.getInstance().player) {
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
        CompoundNBT thirst = compound.getCompound("thirstNBT");
        thirstValue = thirst.getFloat("thirst");
        prevFoodLevel = thirst.getInt("prevFood");

        super.read(compound);
    }
     */
}
