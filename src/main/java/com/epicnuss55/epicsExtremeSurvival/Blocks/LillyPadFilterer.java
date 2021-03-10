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

public class LillyPadFilterer extends Block {
    public LillyPadFilterer(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Stream.of(Block.makeCuboidShape(0, 0, 0, 2, 13, 2), Block.makeCuboidShape(14, 0, 0, 16, 13, 2), Block.makeCuboidShape(14, 0, 14, 16, 13, 16), Block.makeCuboidShape(0, 0, 14, 2, 13, 16), Block.makeCuboidShape(0, 13, 0, 1, 14, 15), Block.makeCuboidShape(15, 13, 1, 16, 14, 16), Block.makeCuboidShape(0, 13, 15, 15, 14, 16), Block.makeCuboidShape(1, 13, 0, 16, 14, 1), Block.makeCuboidShape(1, 1, 1, 15, 2, 15), Block.makeCuboidShape(1, 2, 1, 2, 6, 15), Block.makeCuboidShape(7, 8, 9, 9, 8, 11), Block.makeCuboidShape(7, 8, 5, 9, 8, 7), Block.makeCuboidShape(9, 8, 5, 11, 8, 11), Block.makeCuboidShape(5, 8, 5, 7, 8, 11), Block.makeCuboidShape(0, 6.25, 0, 16, 6.25, 16), Block.makeCuboidShape(12, 13, 7, 15, 14, 9), Block.makeCuboidShape(7, 13, 12, 9, 14, 15), Block.makeCuboidShape(7, 13, 1, 9, 14, 4), Block.makeCuboidShape(1, 13, 7, 4, 14, 9), Block.makeCuboidShape(0, 6.75, 0, 16, 6.75, 16), Block.makeCuboidShape(0, 7.25, 0, 16, 7.25, 16), Block.makeCuboidShape(0, 7.75, 0, 16, 7.75, 16), Block.makeCuboidShape(14, 2, 1, 15, 6, 15), Block.makeCuboidShape(1, 2, 14, 15, 6, 15), Block.makeCuboidShape(1, 2, 1, 15, 6, 2), Block.makeCuboidShape(5, 8, 11, 11, 16, 12), Block.makeCuboidShape(11, 8, 5, 12, 16, 11), Block.makeCuboidShape(4, 8, 5, 5, 16, 11), Block.makeCuboidShape(5, 8, 4, 11, 16, 5), Block.makeCuboidShape(11, 8, 11, 12, 16, 12), Block.makeCuboidShape(11, 8, 4, 12, 16, 5), Block.makeCuboidShape(4, 8, 4, 5, 16, 5), Block.makeCuboidShape(4, 8, 11, 5, 16, 12)).reduce((v1, v2) -> { return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
    }
}
