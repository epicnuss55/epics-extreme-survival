package com.epicnuss55.epicsExtremeSurvival.Events;

import com.epicnuss55.epicsExtremeSurvival.EpicsExtremeSurvival;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.NoteBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = EpicsExtremeSurvival.MODID)
public class TemperatureStuffs {

    public static List<BiomeTemp> BIOME_TEMPS = new ArrayList<>();

    public static void RegisterNewBiomeTemperature(String BiomeRegisteredName, int Temperature) {
        BiomeTemp newInitBiome = new BiomeTemp(BiomeRegisteredName, Temperature);
        BIOME_TEMPS.add(newInitBiome);
    }

    public static class BiomeTemp {
        private int temperature;
        private String biome;

        public BiomeTemp(String BiomeRegisteredName, int temperature) {
            this.temperature = temperature;
            this.biome = BiomeRegisteredName;
        }

        public String getBiomeRegisteredName() {
            return this.biome;
        }

        public int getTemperature() {
            return this.temperature;
        }
    }

    static {
        RegisterNewBiomeTemperature(Biomes.OCEAN.getLocation().toString(), 35);
        RegisterNewBiomeTemperature(Biomes.PLAINS.getLocation().toString(), 55);
        RegisterNewBiomeTemperature(Biomes.DESERT.getLocation().toString(), 75);
        RegisterNewBiomeTemperature(Biomes.MOUNTAINS.getLocation().toString(), 35);
        RegisterNewBiomeTemperature(Biomes.FOREST.getLocation().toString(), 50);
        RegisterNewBiomeTemperature(Biomes.TAIGA.getLocation().toString(), 45);
        RegisterNewBiomeTemperature(Biomes.SWAMP.getLocation().toString(), 65);
        RegisterNewBiomeTemperature(Biomes.RIVER.getLocation().toString(), 40);
        RegisterNewBiomeTemperature(Biomes.NETHER_WASTES.getLocation().toString(), 80);
        RegisterNewBiomeTemperature(Biomes.THE_END.getLocation().toString(), 35);
        RegisterNewBiomeTemperature(Biomes.FROZEN_OCEAN.getLocation().toString(), 15);
        RegisterNewBiomeTemperature(Biomes.FROZEN_RIVER.getLocation().toString(), 20);
        RegisterNewBiomeTemperature(Biomes.SNOWY_TUNDRA.getLocation().toString(), 25);
        RegisterNewBiomeTemperature(Biomes.SNOWY_MOUNTAINS.getLocation().toString(), 15);
        RegisterNewBiomeTemperature(Biomes.MUSHROOM_FIELDS.getLocation().toString(), 40);
        RegisterNewBiomeTemperature(Biomes.MUSHROOM_FIELD_SHORE.getLocation().toString(), 45);
        RegisterNewBiomeTemperature(Biomes.BEACH.getLocation().toString(), 45);
        RegisterNewBiomeTemperature(Biomes.DESERT_HILLS.getLocation().toString(), 70);
        RegisterNewBiomeTemperature(Biomes.WOODED_HILLS.getLocation().toString(), 50);
        RegisterNewBiomeTemperature(Biomes.TAIGA_HILLS.getLocation().toString(), 45);
        RegisterNewBiomeTemperature(Biomes.MOUNTAIN_EDGE.getLocation().toString(), 30);
        RegisterNewBiomeTemperature(Biomes.JUNGLE.getLocation().toString(), 75);
        RegisterNewBiomeTemperature(Biomes.JUNGLE_HILLS.getLocation().toString(), 70);
        RegisterNewBiomeTemperature(Biomes.JUNGLE_EDGE.getLocation().toString(), 65);
        RegisterNewBiomeTemperature(Biomes.DEEP_OCEAN.getLocation().toString(), 25);
        RegisterNewBiomeTemperature(Biomes.STONE_SHORE.getLocation().toString(), 40);
        RegisterNewBiomeTemperature(Biomes.SNOWY_BEACH.getLocation().toString(), 20);
        RegisterNewBiomeTemperature(Biomes.BIRCH_FOREST.getLocation().toString(), 50);
        RegisterNewBiomeTemperature(Biomes.BIRCH_FOREST_HILLS.getLocation().toString(), 50);
        RegisterNewBiomeTemperature(Biomes.DARK_FOREST.getLocation().toString(), 50);
        RegisterNewBiomeTemperature(Biomes.SNOWY_TAIGA.getLocation().toString(), 20);
        RegisterNewBiomeTemperature(Biomes.SNOWY_TAIGA_HILLS.getLocation().toString(), 20);
        RegisterNewBiomeTemperature(Biomes.GIANT_TREE_TAIGA.getLocation().toString(), 45);
        RegisterNewBiomeTemperature(Biomes.GIANT_TREE_TAIGA_HILLS.getLocation().toString(), 45);
        RegisterNewBiomeTemperature(Biomes.GIANT_SPRUCE_TAIGA_HILLS.getLocation().toString(), 45);
        RegisterNewBiomeTemperature(Biomes.WOODED_MOUNTAINS.getLocation().toString(), 40);
        RegisterNewBiomeTemperature(Biomes.SAVANNA.getLocation().toString(), 65);
        RegisterNewBiomeTemperature(Biomes.SAVANNA_PLATEAU.getLocation().toString(), 65);
        RegisterNewBiomeTemperature(Biomes.BADLANDS.getLocation().toString(), 80);
        RegisterNewBiomeTemperature(Biomes.WOODED_BADLANDS_PLATEAU.getLocation().toString(), 75);
        RegisterNewBiomeTemperature(Biomes.BADLANDS_PLATEAU.getLocation().toString(), 80);
        RegisterNewBiomeTemperature(Biomes.SMALL_END_ISLANDS.getLocation().toString(), 35);
        RegisterNewBiomeTemperature(Biomes.END_MIDLANDS.getLocation().toString(), 35);
        RegisterNewBiomeTemperature(Biomes.END_HIGHLANDS.getLocation().toString(), 35);
        RegisterNewBiomeTemperature(Biomes.END_BARRENS.getLocation().toString(), 35);
        RegisterNewBiomeTemperature(Biomes.WARM_OCEAN.getLocation().toString(), 45);
        RegisterNewBiomeTemperature(Biomes.LUKEWARM_OCEAN.getLocation().toString(), 35);
        RegisterNewBiomeTemperature(Biomes.COLD_OCEAN.getLocation().toString(), 20);
        RegisterNewBiomeTemperature(Biomes.DEEP_WARM_OCEAN.getLocation().toString(), 30);
        RegisterNewBiomeTemperature(Biomes.DEEP_LUKEWARM_OCEAN.getLocation().toString(), 40);
        RegisterNewBiomeTemperature(Biomes.DEEP_COLD_OCEAN.getLocation().toString(), 15);
        RegisterNewBiomeTemperature(Biomes.DEEP_FROZEN_OCEAN.getLocation().toString(), 0);
        RegisterNewBiomeTemperature(Biomes.SUNFLOWER_PLAINS.getLocation().toString(), 55);
        RegisterNewBiomeTemperature(Biomes.DESERT_LAKES.getLocation().toString(), 70);
        RegisterNewBiomeTemperature(Biomes.GRAVELLY_MOUNTAINS.getLocation().toString(), 50);
        RegisterNewBiomeTemperature(Biomes.FLOWER_FOREST.getLocation().toString(), 55);
        RegisterNewBiomeTemperature(Biomes.TAIGA_MOUNTAINS.getLocation().toString(), 30);
        RegisterNewBiomeTemperature(Biomes.SWAMP_HILLS.getLocation().toString(), 60);
        RegisterNewBiomeTemperature(Biomes.ICE_SPIKES.getLocation().toString(), 25);
        RegisterNewBiomeTemperature(Biomes.MODIFIED_JUNGLE.getLocation().toString(), 60);
        RegisterNewBiomeTemperature(Biomes.MODIFIED_JUNGLE_EDGE.getLocation().toString(), 65);
        RegisterNewBiomeTemperature(Biomes.TALL_BIRCH_FOREST.getLocation().toString(), 50);
        RegisterNewBiomeTemperature(Biomes.TALL_BIRCH_HILLS.getLocation().toString(), 50);
        RegisterNewBiomeTemperature(Biomes.DARK_FOREST_HILLS.getLocation().toString(), 50);
        RegisterNewBiomeTemperature(Biomes.SNOWY_TAIGA_MOUNTAINS.getLocation().toString(), 20);
        RegisterNewBiomeTemperature(Biomes.GIANT_SPRUCE_TAIGA.getLocation().toString(), 45);
        RegisterNewBiomeTemperature(Biomes.GIANT_SPRUCE_TAIGA_HILLS.getLocation().toString(), 45);
        RegisterNewBiomeTemperature(Biomes.MODIFIED_GRAVELLY_MOUNTAINS.getLocation().toString(), 50);
        RegisterNewBiomeTemperature(Biomes.SHATTERED_SAVANNA.getLocation().toString(), 65);
        RegisterNewBiomeTemperature(Biomes.SHATTERED_SAVANNA_PLATEAU.getLocation().toString(), 65);
        RegisterNewBiomeTemperature(Biomes.ERODED_BADLANDS.getLocation().toString(), 80);
        RegisterNewBiomeTemperature(Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU.getLocation().toString(), 75);
        RegisterNewBiomeTemperature(Biomes.MODIFIED_BADLANDS_PLATEAU.getLocation().toString(), 80);
        RegisterNewBiomeTemperature(Biomes.BAMBOO_JUNGLE.getLocation().toString(), 70);
        RegisterNewBiomeTemperature(Biomes.BAMBOO_JUNGLE_HILLS.getLocation().toString(), 70);
        RegisterNewBiomeTemperature(Biomes.SOUL_SAND_VALLEY.getLocation().toString(), 85);
        RegisterNewBiomeTemperature(Biomes.CRIMSON_FOREST.getLocation().toString(), 85);
        RegisterNewBiomeTemperature(Biomes.WARPED_FOREST.getLocation().toString(), 85);
        RegisterNewBiomeTemperature(Biomes.BASALT_DELTAS.getLocation().toString(), 90);
    }


    private static final int TEMPERATURE_CHANGE_DELAY_VALUE = 40;
    private static int current_Val = 0;
    private static BiomeTemp currentBiome = BIOME_TEMPS.get(0);
    private static int PlayerTemperature = 20;

    @SubscribeEvent
    public void TemperatureEvent(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity && !(((PlayerEntity) event.getEntityLiving()).isCreative()) && !(((PlayerEntity) event.getEntityLiving()).isSpectator())) {
            if (current_Val >= TEMPERATURE_CHANGE_DELAY_VALUE) {
                current_Val = 0;

                World world = event.getEntityLiving().getEntityWorld();
                String BIOME = world.getBiome(event.getEntityLiving().getPosition()).getRegistryName().toString();
                EpicsExtremeSurvival.LOGGER.info("Currently in Biome " + BIOME);
                if (currentBiome.getBiomeRegisteredName().equals(BIOME)) {
                    if (PlayerTemperature > currentBiome.temperature) {
                        PlayerTemperature--;
                    } else if (PlayerTemperature < currentBiome.temperature) {
                        PlayerTemperature++;
                    }
                    EpicsExtremeSurvival.LOGGER.info(PlayerTemperature);

                    if (PlayerTemperature <= 35) {
                        if (PlayerTemperature <= 25) {
                            if (PlayerTemperature <= 20) {
                                if (PlayerTemperature <= 15) {
                                    event.getEntityLiving().attackEntityFrom(DamageSource.GENERIC, 2f);
                                } else event.getEntityLiving().attackEntityFrom(DamageSource.GENERIC, 1.5f);
                            } else event.getEntityLiving().attackEntityFrom(DamageSource.GENERIC, 1f);
                        } else event.getEntityLiving().attackEntityFrom(DamageSource.GENERIC, 0.5f);
                    } else if (PlayerTemperature >= 65) {
                        if (PlayerTemperature >= 75) {
                            if (PlayerTemperature >= 80) {
                                if (PlayerTemperature >= 85) {
                                    event.getEntityLiving().attackEntityFrom(DamageSource.ON_FIRE, 2f);
                                } else event.getEntityLiving().attackEntityFrom(DamageSource.ON_FIRE, 1.5f);
                            } else event.getEntityLiving().attackEntityFrom(DamageSource.ON_FIRE, 1f);
                        } else event.getEntityLiving().attackEntityFrom(DamageSource.ON_FIRE, 0.5f);
                    }
                } else {
                    EpicsExtremeSurvival.LOGGER.info("grabbing new Biome Temp");
                    for (BiomeTemp biome : BIOME_TEMPS) {
                        EpicsExtremeSurvival.LOGGER.info("checking " + biome.getBiomeRegisteredName());
                        if (BIOME.equals(biome.getBiomeRegisteredName())) {
                            EpicsExtremeSurvival.LOGGER.info("Found new Biome " + biome.getBiomeRegisteredName());
                            currentBiome = biome;
                            break;
                        }
                    }
                }
            } else current_Val++;
        }
    }

    @SubscribeEvent
    public static void OverlayEvent(RenderGameOverlayEvent.Pre event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.VIGNETTE) {
            if (PlayerTemperature >= 60) {
                RenderSystem.pushMatrix();
                RenderSystem.pushTextureAttributes();
                RenderSystem.enableAlphaTest();
                RenderSystem.enableBlend();
                RenderSystem.color4f(1F,1F,1F, (PlayerTemperature-60)/100f);
                Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(EpicsExtremeSurvival.MODID, "textures/gui/hotgradient.png"));
                Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), 0, 0, Minecraft.getInstance().getMainWindow().getScaledWidth(), Minecraft.getInstance().getMainWindow().getScaledHeight(), 0, 0, 256, 256, 256, 256);
                RenderSystem.popAttributes();
                RenderSystem.popMatrix();
            } else if (PlayerTemperature <= 40) {
                RenderSystem.pushMatrix();
                RenderSystem.pushTextureAttributes();
                RenderSystem.enableAlphaTest();
                RenderSystem.enableBlend();
                RenderSystem.color4f(1F,1F,1F, (40-PlayerTemperature)/100f);
                Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(EpicsExtremeSurvival.MODID, "textures/gui/coldgradient.png"));
                Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), 0, 0, Minecraft.getInstance().getMainWindow().getScaledWidth(), Minecraft.getInstance().getMainWindow().getScaledHeight(), 0, 0, 256, 256, 256, 256);
                RenderSystem.popAttributes();
                RenderSystem.popMatrix();
            }
            Minecraft.getInstance().getTextureManager().bindTexture(AbstractGui.GUI_ICONS_LOCATION);
        }
    }
}
