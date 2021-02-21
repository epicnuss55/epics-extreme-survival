package com.epicnuss55.epicsExtremeSurvival.Events;

import com.epicnuss55.epicsExtremeSurvival.EpicsExtremeSurvival;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.client.Minecraft;

@OnlyIn(Dist.CLIENT)
public class HealEvent {

    @SubscribeEvent
    public void event(LivingHealEvent event) {
        if (!(Overlays.thirstValue == 10f && event.getEntity().equals(Minecraft.getInstance().player) && Minecraft.getInstance().player.getFoodStats().getFoodLevel() == 20)) {
            EpicsExtremeSurvival.LOGGER.info("Heal Cancelled");
            event.setCanceled(true);
        }
    }
}
