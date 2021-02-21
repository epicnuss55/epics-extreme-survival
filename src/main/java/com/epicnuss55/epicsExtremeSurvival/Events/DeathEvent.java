package com.epicnuss55.epicsExtremeSurvival.Events;

import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.client.Minecraft;

@OnlyIn(Dist.CLIENT)
public class DeathEvent {

    @SubscribeEvent
    public void event(LivingDeathEvent event) {
        if (event.getEntity().equals(Minecraft.getInstance().player)) {
            Overlays.thirstValue = 10f;
        }
    }
}
