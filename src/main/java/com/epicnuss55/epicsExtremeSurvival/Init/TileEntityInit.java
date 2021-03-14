package com.epicnuss55.epicsExtremeSurvival.Init;

import com.epicnuss55.epicsExtremeSurvival.Blocks.LilyPadFilterer;
import com.epicnuss55.epicsExtremeSurvival.EpicsExtremeSurvival;
import com.epicnuss55.epicsExtremeSurvival.Tiles.LilyPadFilterer_Processed;
import com.epicnuss55.epicsExtremeSurvival.Tiles.LilyPadFilterer_Processing;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityInit {
    public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, EpicsExtremeSurvival.MODID);

    public static final RegistryObject<TileEntityType<LilyPadFilterer_Processing>> Lilypad_filterer_processing = TILES.register("lillypad_filter_proccessing", () -> TileEntityType.Builder.create(LilyPadFilterer_Processing::new, BlockInit.LilyPadFilterBlock_Proccessing.get()).build(null));
    public static final RegistryObject<TileEntityType<LilyPadFilterer_Processing>> Lilypad_filterer_processing = TILES.register("miner_filter_proccessed", () -> TileEntityType.Builder.create(LilyPadFilterer_Processed::new, BlockInit.LilyPadFilterBlock_Proccessing.get()).build(null));
}
