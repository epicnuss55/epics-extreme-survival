package com.epicnuss55.epicsExtremeSurvival.Init;

import com.epicnuss55.epicsExtremeSurvival.Blocks.LilyPadFilterer;
import com.epicnuss55.epicsExtremeSurvival.EpicsExtremeSurvival;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EpicsExtremeSurvival.MODID);

    /*-------------------------------------------BLOCKS-------------------------------------------*/
    /*----------------------Lily pad filter----------------------*/
    public static final RegistryObject<Block> LilyPadFilterBlock = BLOCKS.register("lily_pad_filterer", () ->
            new LilyPadFilterer(Block.Properties.create(Material.ROCK)
                    .harvestTool(ToolType.PICKAXE)
                    .harvestLevel(1)
                    .setRequiresTool()
                    .hardnessAndResistance(1f,4)
            ));
}
