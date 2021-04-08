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
import net.minecraft.util.datafix.fixes.BiomeName;
import net.minecraft.world.Difficulty;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeRegistry;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
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

    public static void RegisterNewBiomeTemperature(String BiomeRegisteredName, int Temperature, boolean IsIncreasingTemperature) {
        BiomeTemp newInitBiome = new BiomeTemp(BiomeRegisteredName, Temperature, IsIncreasingTemperature);
        BIOME_TEMPS.add(newInitBiome);
    }

    public static class BiomeTemp {
        private int temperature;
        private String biome;
        private Boolean IncreaseTemperature;
        public BiomeTemp(String BiomeRegisteredName, int temperature, boolean IsIncreasingTemperature) {
            this.temperature = temperature;
            this.biome = BiomeRegisteredName;
            this.IncreaseTemperature = IsIncreasingTemperature;
        }

        public String getBiomeRegisteredName() {
            return this.biome;
        }

        public Boolean IsIncreasingTemperature() {
            return this.IncreaseTemperature;
        }

        public int getTemperature() {
            return this.temperature;
        }
    }

    public static void RegisterVanillaBiomes() {
        RegisterNewBiomeTemperature(Biomes.OCEAN.getLocation().toString(), 40, false);
        RegisterNewBiomeTemperature(Biomes.PLAINS.getLocation().toString(), 40, true);
        RegisterNewBiomeTemperature(Biomes.DESERT.getLocation().toString(), 60, true);
        RegisterNewBiomeTemperature(Biomes.MOUNTAINS.getLocation().toString(), 40, false);
        RegisterNewBiomeTemperature(Biomes.FOREST.getLocation().toString(), 45, true);
        RegisterNewBiomeTemperature(Biomes.TAIGA.getLocation().toString(), 40, false);
        RegisterNewBiomeTemperature(Biomes.SWAMP.getLocation().toString(), 40, true);
        RegisterNewBiomeTemperature(Biomes.RIVER.getLocation().toString(), 40, false);
        RegisterNewBiomeTemperature(Biomes.NETHER_WASTES.getLocation().toString(), 80, true);
        RegisterNewBiomeTemperature(Biomes.THE_END.getLocation().toString(), 55, false);
        RegisterNewBiomeTemperature(Biomes.FROZEN_OCEAN.getLocation().toString(), 30, false);
        RegisterNewBiomeTemperature(Biomes.FROZEN_RIVER.getLocation().toString(), 30, false);
        RegisterNewBiomeTemperature(Biomes.SNOWY_TUNDRA.getLocation().toString(), 30, false);
        RegisterNewBiomeTemperature(Biomes.SNOWY_MOUNTAINS.getLocation().toString(), 25, false);
        RegisterNewBiomeTemperature(Biomes.MUSHROOM_FIELDS.getLocation().toString(), 40, true);
        RegisterNewBiomeTemperature(Biomes.MUSHROOM_FIELD_SHORE.getLocation().toString(), 55, false);
    }
}