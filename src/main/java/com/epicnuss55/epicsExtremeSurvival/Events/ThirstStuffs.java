package com.epicnuss55.epicsExtremeSurvival.Events;

import com.epicnuss55.epicsExtremeSurvival.EpicsExtremeSurvival;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.client.Minecraft;
import net.minecraft.util.FoodStats;

@OnlyIn(Dist.CLIENT)
public class ThirstStuffs extends FoodStats {

    //*EVENTS*\\
    //wont heal unless water is also high enough
    @SubscribeEvent
    public void event(LivingHealEvent event) {
        if (!(ThirstStuffs.thirstValue == 8.5f && event.getEntity().equals(Minecraft.getInstance().player) && Minecraft.getInstance().player.getFoodStats().getFoodLevel() == 20)) {
            EpicsExtremeSurvival.LOGGER.info("Heal Cancelled");
            event.setCanceled(true);
        }
    }

    //after the player dies and respawns, thirst value resets
    @SubscribeEvent
    public void event(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity().equals(Minecraft.getInstance().player)) {
            ThirstStuffs.prevFoodLevel = 20;
            ThirstStuffs.thirstValue = 10f;
        }
    }

    //Renderer (doubles as a tick event)
    @SubscribeEvent
    public void OverlayEvent(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.FOOD) {
            Minecraft mc = Minecraft.getInstance();
            int y = mc.getMainWindow().getScaledHeight() - ForgeIngameGui.right_height;
            int x = mc.getMainWindow().getScaledWidth() / 2 + 82;
            MatrixStack stack = event.getMatrixStack();
            mc.getTextureManager().bindTexture(new ResourceLocation(EpicsExtremeSurvival.MODID, "textures/gui/overlays.png"));

            thirstTick();
            renderThirstBar(stack, x, y);
        }
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

    //even more logic lol -- everytime the hunger bar changes, the thirst bar ticks down once
    private static void thirstTick() {
        ticker++;
        Boolean hungerChanged = foodChanged(Minecraft.getInstance());
        if(hungerChanged && thirstValue != 0f) thirstValue = thirstValue - 0.5f;
        if(thirstValue < 3f) Minecraft.getInstance().player.setSprinting(false);
        if(thirstValue == 0f && ticker == 20) Minecraft.getInstance().player.attackEntityFrom(DamageSource.STARVE, 0.5f);

        EpicsExtremeSurvival.LOGGER.info(thirstValue);

        if (ticker == 20) ticker = 0;
    }

    //returns true everytime the food value changes
    private static boolean foodChanged(Minecraft mc) {
        int currentFoodLevel = mc.player.getFoodStats().getFoodLevel();
        if(prevFoodLevel != currentFoodLevel) {
            prevFoodLevel = currentFoodLevel;
            return true;
        }
        return false;
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
