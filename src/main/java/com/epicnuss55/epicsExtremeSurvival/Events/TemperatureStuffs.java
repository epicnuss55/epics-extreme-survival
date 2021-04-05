package com.epicnuss55.epicsExtremeSurvival.Events;

import com.electronwill.nightconfig.core.AbstractConfig;
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
import net.minecraft.world.biome.Biome;
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

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = EpicsExtremeSurvival.MODID)
public class TemperatureStuffs {

    public static List<BiomeTemp> BIOME_TEMPS = new ArrayList<BiomeTemp>();

    public static void RegisterNewBiomeTemperature(Biome biome, int Temperature) {
        BiomeTemp newInitBiome = new BiomeTemp(biome, Temperature);
        BIOME_TEMPS.add(newInitBiome);
    }

    public static class BiomeTemp {
        private int temperature;
        private Biome biome;
        public BiomeTemp(Biome biome, int temperature) {
            this.temperature = temperature;
            this.biome = biome;
        }

        public Biome getBiome() {
            return this.biome;
        }

        public int getTemperature() {
            return this.temperature;
        }
    }
}