package com.epicnuss55.epicsExtremeSurvival.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class LilyPadFilterer extends Block {
    public LilyPadFilterer(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Stream.of(
                Block.makeCuboidShape(0, 0, 0, 2, 13, 2),
                Block.makeCuboidShape(0, 0, 14, 2, 13, 16),
                Block.makeCuboidShape(0, 13, 0, 1, 14, 16),
                Block.makeCuboidShape(15, 13, 0, 16, 14, 16),
                Block.makeCuboidShape(4, 8, 4, 12, 16, 12),
                Block.makeCuboidShape(7, 13, 1, 9, 14, 4),
                Block.makeCuboidShape(7, 13, 12, 9, 14, 15),
                Block.makeCuboidShape(12, 13, 7, 15, 14, 9),
                Block.makeCuboidShape(1, 13, 7, 4, 14, 9),
                Block.makeCuboidShape(1, 1, 1, 15, 7, 15),
                Block.makeCuboidShape(14, 0, 0, 16, 13, 2),
                Block.makeCuboidShape(14, 0, 14, 16, 13, 16),
                Block.makeCuboidShape(0, 13, 0, 16, 14, 1),
                Block.makeCuboidShape(0, 13, 15, 16, 14, 16)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
    }
}
