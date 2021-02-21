package com.epicnuss55.epicsExtremeSurvival.Events;

import com.epicnuss55.epicsExtremeSurvival.EpicsExtremeSurvival;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.FoodStats;

@OnlyIn(Dist.CLIENT)
public class Overlays extends FoodStats {

    @SubscribeEvent
    public void OverlayEvent(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.FOOD) {
            Minecraft mc = Minecraft.getInstance();
            thirstTick();
            int y = mc.getMainWindow().getScaledHeight() - ForgeIngameGui.right_height;
            int x = mc.getMainWindow().getScaledWidth() / 2 + 82;
            MatrixStack stack = event.getMatrixStack();
            mc.getTextureManager().bindTexture(new ResourceLocation(EpicsExtremeSurvival.MODID, "textures/gui/overlays.png"));

            renderThirstBar(stack, x, y);
        }
    }

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



    private static void draw(MatrixStack stack, int ScreenXPos, int ScreenYPos, int textureXStartPos, int textureYStartPos, int textureXEndPos, int textureYEndPos) {
        Minecraft.getInstance().ingameGUI.blit(stack, ScreenXPos, ScreenYPos, textureXStartPos, textureYStartPos, textureXEndPos, textureYEndPos);
    }


    public static int prevFoodLevel = 20;
    public static float thirstValue = 10f;
    public static int ticker = 0;

    private static void thirstTick() {
        ticker++;
        Boolean hungerChanged = foodChanged(Minecraft.getInstance());
        if(hungerChanged && thirstValue != 0f) thirstValue = thirstValue - 0.5f;
        if(thirstValue < 3f) Minecraft.getInstance().player.setSprinting(false);
        if(thirstValue == 0f && ticker == 20) Minecraft.getInstance().player.attackEntityFrom(DamageSource.STARVE, 0.5f);

        EpicsExtremeSurvival.LOGGER.info(thirstValue);

        if (ticker == 20) ticker = 0;
    }

    private static boolean foodChanged(Minecraft mc) {
        int currentFoodLevel = mc.player.getFoodStats().getFoodLevel();
        if(prevFoodLevel != currentFoodLevel) {
            prevFoodLevel = currentFoodLevel;
            return true;
        }
        return false;
    }

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
