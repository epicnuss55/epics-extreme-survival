package com.epicnuss55.epicsExtremeSurvival.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.stream.Stream;

public class LilyPadFilterer extends Block {
    protected static final IntegerProperty LilyPadFilterer_WaterAmount = IntegerProperty.create("waterlevel", 0, 17);

    public LilyPadFilterer(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(LilyPadFilterer_WaterAmount, 0));
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

    @Override
        public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            int waterVal = state.get(LilyPadFilterer_WaterAmount).intValue();

            switch (waterVal) {
                case 0: {
                    if (player.getHeldItem(handIn).getItem().equals(Items.WATER_BUCKET)) {
                        player.setHeldItem(handIn, Items.BUCKET.getDefaultInstance());
                        worldIn.addParticle(ParticleTypes.SPLASH, pos.getX(), pos.getY(), pos.getZ(), 1, 0.5, 1);
                        worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.BLOCKS, 2, 1, false);
                        setWaterLevel(worldIn, pos, state, 16);
                    }
                    break;
                }
                case 1: {
                    if (player.getHeldItem(handIn).getItem().equals(Items.WATER_BUCKET.getItem())) {
                        player.setHeldItem(handIn, Items.BUCKET.getDefaultInstance());
                        worldIn.addParticle(ParticleTypes.SPLASH, pos.getX(), pos.getY(), pos.getZ(), 1, 0.5, 1);
                        worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.BLOCKS, 2, 1, false);
                        setWaterLevel(worldIn, pos, state, 6);
                        player.setHeldItem(handIn, Items.BUCKET.getDefaultInstance());
                    } else {
                        worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 1, 1, false);
                        setWaterLevel(worldIn, pos, state, 0);
                    }
                    break;
                }
                case 2: {
                    if (player.getHeldItem(handIn).getItem().equals(Items.WATER_BUCKET.getItem())) {
                        player.setHeldItem(handIn, Items.BUCKET.getDefaultInstance());
                        worldIn.addParticle(ParticleTypes.SPLASH, pos.getX(), pos.getY(), pos.getZ(), 1, 0.5, 1);
                        worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.BLOCKS, 2, 1, false);
                        setWaterLevel(worldIn, pos, state, 7);
                        player.setHeldItem(handIn, Items.BUCKET.getDefaultInstance());
                    } else {
                        worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 1, 1, false);
                        setWaterLevel(worldIn, pos, state, 1);
                    }
                    break;
                }
                case 3: {
                    if (player.getHeldItem(handIn).getItem().equals(Items.WATER_BUCKET.getItem())) {
                        player.setHeldItem(handIn, Items.BUCKET.getDefaultInstance());
                        worldIn.addParticle(ParticleTypes.SPLASH, pos.getX(), pos.getY(), pos.getZ(), 1, 0.5, 1);
                        worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.BLOCKS, 2, 1, false);
                        setWaterLevel(worldIn, pos, state, 8);
                        player.setHeldItem(handIn, Items.BUCKET.getDefaultInstance());
                    } else {
                        worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 1, 1, false);
                        setWaterLevel(worldIn, pos, state, 2);
                    }
                    break;
                }
                case 4: {
                    if (player.getHeldItem(handIn).getItem().equals(Items.WATER_BUCKET.getItem())) {
                        player.setHeldItem(handIn, Items.BUCKET.getDefaultInstance());
                        worldIn.addParticle(ParticleTypes.SPLASH, pos.getX(), pos.getY(), pos.getZ(), 1, 0.5, 1);
                        worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.BLOCKS, 2, 1, false);
                        setWaterLevel(worldIn, pos, state, 9);
                        player.setHeldItem(handIn, Items.BUCKET.getDefaultInstance());
                    } else {
                        worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 1, 1, false);
                        setWaterLevel(worldIn, pos, state, 3);
                    }
                    break;
                }
                case 5: {
                    if (player.getHeldItem(handIn).getItem().equals(Items.WATER_BUCKET.getItem())) {
                        player.setHeldItem(handIn, Items.BUCKET.getDefaultInstance());
                        worldIn.addParticle(ParticleTypes.SPLASH, pos.getX(), pos.getY(), pos.getZ(), 1, 0.5, 1);
                        worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.BLOCKS, 2, 1, false);
                        setWaterLevel(worldIn, pos, state, 10);
                        player.setHeldItem(handIn, Items.BUCKET.getDefaultInstance());
                    } else {
                        worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 1, 1, false);
                        setWaterLevel(worldIn, pos, state, 4);
                    }
                    break;
                }
                case 6: {
                    if (player.isCrouching()) {
                        worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 0, 1, 0);
                        worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.BLOCKS, 1, 2, false);
                        setWaterLevel(worldIn, pos, state, 11);
                    } else {
                        worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX(), pos.getY(), pos.getZ(), 0, 3, 0);
                        worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 1, 1, false);
                        setWaterLevel(worldIn, pos, state, 16);
                    }
                    break;
                }
                case 7: {
                    if (player.isCrouching()) {
                        worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 0, 1, 0);
                        worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.BLOCKS, 1, 2, false);
                        setWaterLevel(worldIn, pos, state, 12);
                    } else {
                        worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX(), pos.getY(), pos.getZ(), 0, 3, 0);
                        worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 1, 1, false);
                        setWaterLevel(worldIn, pos, state, 6);
                    }
                    break;
                }
                case 8: {
                    if (player.isCrouching()) {
                        worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 0, 1, 0);
                        worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.BLOCKS, 1, 2, false);
                        setWaterLevel(worldIn, pos, state, 13);
                    } else {
                        worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX(), pos.getY(), pos.getZ(), 0, 3, 0);
                        worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 1, 1, false);
                        setWaterLevel(worldIn, pos, state, 7);
                    }
                    break;
                }
                case 9: {
                    if (player.isCrouching()) {
                        worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 0, 1, 0);
                        worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.BLOCKS, 1, 2, false);
                        setWaterLevel(worldIn, pos, state, 14);
                    } else {
                        worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX(), pos.getY(), pos.getZ(), 0, 3, 0);
                        worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 1, 1, false);
                        setWaterLevel(worldIn, pos, state, 8);
                    }
                    break;
                }
                case 10: {
                    worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX(), pos.getY(), pos.getZ(), 0, 3, 0);
                    worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 1, 1, false);
                    setWaterLevel(worldIn, pos, state, 9);
                    break;
                }
                case 11: {
                    if (player.isCrouching()) {
                        worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 0, 1, 0);
                        worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.BLOCKS, 1, 2, false);
                        setWaterLevel(worldIn, pos, state, 5);
                    } else {
                        worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX(), pos.getY(), pos.getZ(), 0, 3, 0);
                        worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 1, 1, false);
                        setWaterLevel(worldIn, pos, state, 17);
                    }
                    break;
                }
                case 12: {
                    if (player.isCrouching()) {
                        worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 0, 1, 0);
                        worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.BLOCKS, 1, 2, false);
                        setWaterLevel(worldIn, pos, state, 5);
                    } else {
                        worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX(), pos.getY(), pos.getZ(), 0, 3, 0);
                        worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 1, 1, false);
                        setWaterLevel(worldIn, pos, state, 11);
                    }
                    break;
                }
                case 13: {
                    if (player.isCrouching()) {
                        worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 0, 1, 0);
                        worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.BLOCKS, 1, 2, false);
                        setWaterLevel(worldIn, pos, state, 5);
                    } else {
                        worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX(), pos.getY(), pos.getZ(), 0, 3, 0);
                        worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 1, 1, false);
                        setWaterLevel(worldIn, pos, state, 12);
                    }
                    break;
                }
                case 14: {
                    if (player.isCrouching()) {
                        worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 0, 1, 0);
                        worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.BLOCKS, 1, 2, false);
                        setWaterLevel(worldIn, pos, state, 5);
                    } else {
                        worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX(), pos.getY(), pos.getZ(), 0, 3, 0);
                        worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 1, 1, false);
                        setWaterLevel(worldIn, pos, state, 13);
                    }
                    break;
                }
                case 15: {
                    if (player.isCrouching()) {
                        worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 0, 1, 0);
                        worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.AMBIENT_UNDERWATER_ENTER, SoundCategory.BLOCKS, 1, 2, false);
                        setWaterLevel(worldIn, pos, state, 5);
                    } else {
                        worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX(), pos.getY(), pos.getZ(), 0, 3, 0);
                        worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 1, 1, false);
                        setWaterLevel(worldIn, pos, state, 14);
                    }
                    break;
                }
                case 16: {
                    worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX(), pos.getY(), pos.getZ(), 0, 3, 0);
                    setWaterLevel(worldIn, pos, state, 17);
                    break;
                }
                case 17: {
                    worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 0, 1, 0);
                    setWaterLevel(worldIn, pos, state, 5);
                    break;
                }
            }
        }

        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    private static void setWaterLevel(World worldIn, BlockPos pos, BlockState state, int level) {
        worldIn.setBlockState(pos, state.with(LilyPadFilterer_WaterAmount, level));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(LilyPadFilterer_WaterAmount);
        super.fillStateContainer(builder);
    }
}
