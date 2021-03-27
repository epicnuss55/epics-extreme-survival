package com.epicnuss55.epicsExtremeSurvival.Blocks;

import com.epicnuss55.epicsExtremeSurvival.Events.ThirstStuffs;
import com.epicnuss55.epicsExtremeSurvival.Init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
        ).reduce((v1, v2) -> {
            return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
        }).get();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        int waterVal = state.get(LilyPadFilterer_WaterAmount).intValue();
        Item item = player.getHeldItem(handIn).getItem();
        if (!worldIn.isRemote()) {
            if (waterVal == 17) {
                setWaterLevel(worldIn, pos, state, 5);
                return ActionResultType.SUCCESS;
            }
            if (waterVal == 16) {
                setWaterLevel(worldIn, pos, state, 17);
                return ActionResultType.SUCCESS;
            }
            if (waterVal >= 0 && waterVal <= 5) {
                if (waterVal == 0 && item == Items.WATER_BUCKET) {
                    player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
                    setWaterLevel(worldIn, pos, state, 16);
                    return ActionResultType.CONSUME;
                } else if (item == Items.WATER_BUCKET) {
                    player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
                    setWaterLevel(worldIn, pos, state, waterVal + 5);
                    return ActionResultType.CONSUME;
                } else if (waterVal > 0){
                    if (item == Items.GLASS_BOTTLE)
                        player.setHeldItem(handIn, new ItemStack(ItemInit.Purified_Water.get()));
                    else
                        ThirstStuffs.AddThirst(1.5f);
                    setWaterLevel(worldIn, pos, state, waterVal - 1);
                    return ActionResultType.SUCCESS;
                }
            } else if (waterVal >= 6 && waterVal <= 10) {
                if (waterVal == 10) {
                    setWaterLevel(worldIn, pos, state, 9);
                } else if (waterVal == 6) {
                    if (player.isCrouching()) {
                        setWaterLevel(worldIn, pos, state, waterVal + 5);
                    } else {
                        setWaterLevel(worldIn, pos, state, 16);
                    }
                } else {
                    if (player.isCrouching()) {
                        setWaterLevel(worldIn, pos, state, waterVal + 5);
                    } else {
                        setWaterLevel(worldIn, pos, state, waterVal - 1);

                        if (item == Items.GLASS_BOTTLE)
                            player.setHeldItem(handIn, new ItemStack(ItemInit.Purified_Water.get()));
                        else
                        ThirstStuffs.AddThirst(1.5f);
                    }
                }
                return ActionResultType.SUCCESS;
            } else if (waterVal >= 11 && waterVal <= 15) {
                if (waterVal == 11) {
                    if (player.isCrouching()) {
                        setWaterLevel(worldIn, pos, state, 5);
                    } else {
                        setWaterLevel(worldIn, pos, state, 17);
                    }
                } else {
                    if (player.isCrouching()) {
                        setWaterLevel(worldIn, pos, state, 5);
                    } else {
                        setWaterLevel(worldIn, pos, state, waterVal - 1);
                        if (item == Items.GLASS_BOTTLE)
                            player.setHeldItem(handIn, new ItemStack(ItemInit.Purified_Water.get()));
                        else
                            ThirstStuffs.AddThirst(1.5f);
                    }
                }
                return ActionResultType.SUCCESS;
            }
        } else {
            if (waterVal == 17) {
                worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 0, 1, 0);
                worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ() + 1, 0, 1, 0);
                worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 0, 1, 0);
                worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY() + 1, pos.getZ(), 0, 1, 0);
                worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 0, 1, 0);
                worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 1, pos.getY(), pos.getZ(), 0, 1, 0);
                worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1, 0.7f, false);
            }
            if (waterVal == 16) {
                worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.5, 1.5, 0.5);
                worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, -0.5, 1.5, -0.5);
                worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.5, 1.5, -0.5);
                worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, -0.5, 1.5, 0.5);
                worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.25, 1.5, 0.25);
                worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, -0.25, 1.5, -0.25);
                worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.BLOCK_LILY_PAD_PLACE, SoundCategory.NEUTRAL, 1, 1.2f, false);
            }
            if (waterVal >= 0 && waterVal <= 5) {
                if (item == Items.WATER_BUCKET) {
                    worldIn.addParticle(ParticleTypes.SPLASH, pos.getX(), pos.getY() + 1, pos.getZ() + Math.random(), 1, 0.5, 1);
                    worldIn.addParticle(ParticleTypes.SPLASH, pos.getX(), pos.getY() + 1, pos.getZ() + Math.random(), 1, 0.5, 1);
                    worldIn.addParticle(ParticleTypes.SPLASH, pos.getX() + Math.random(), pos.getY() + 1, pos.getZ(), 1, 0.5, 1);
                    worldIn.addParticle(ParticleTypes.SPLASH, pos.getX() + Math.random(), pos.getY() + 1, pos.getZ(), 1, 0.5, 1);
                    worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 2, 0.7f, false);
                } else if (waterVal > 0){
                    if (item == Items.GLASS_BOTTLE)
                        worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1, 1, false);
                    else
                        worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.NEUTRAL, 1, 1, false);
                }
            } else if (waterVal >= 6 && waterVal <= 10) {
                if (player.isCrouching()) {
                    worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 0, 1, 0);
                    worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ() + 1, 0, 1, 0);
                    worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 0, 1, 0);
                    worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY() + 1, pos.getZ(), 0, 1, 0);
                    worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 0, 1, 0);
                    worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 1, pos.getY(), pos.getZ(), 0, 1, 0);
                    worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LILY_PAD_PLACE, SoundCategory.BLOCKS, 1, 1.2f, false);
                } else {
                    worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.5, 1.5, 0.5);
                    worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, -0.5, 1.5, -0.5);
                    worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.5, 1.5, -0.5);
                    worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, -0.5, 1.5, 0.5);
                    worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.25, 1.5, 0.25);
                    worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, -0.25, 1.5, -0.25);

                    if (item == Items.GLASS_BOTTLE)
                        worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1, 1, false);
                    else
                        worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.NEUTRAL, 1, 1, false);
                }
            } else if (waterVal >= 11 && waterVal <= 15) {
                if (player.isCrouching()) {
                    worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 0, 1, 0);
                    worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ() + 1, 0, 1, 0);
                    worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 0, 1, 0);
                    worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY() + 1, pos.getZ(), 0, 1, 0);
                    worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX(), pos.getY(), pos.getZ(), 0, 1, 0);
                    worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 1, pos.getY(), pos.getZ(), 0, 1, 0);
                    worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1, 0.7f, false);
                } else {
                    worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.5, 1.5, 0.5);
                    worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, -0.5, 1.5, -0.5);
                    worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.5, 1.5, -0.5);
                    worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, -0.5, 1.5, 0.5);
                    worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.25, 1.5, 0.25);
                    worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, -0.25, 1.5, -0.25);

                    if (item == Items.GLASS_BOTTLE)
                        worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1, 1, false);
                    else
                        worldIn.playSound(player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.NEUTRAL, 1, 1, false);
                }
            }
            return ActionResultType.func_233537_a_(worldIn.isRemote());
        }
        return ActionResultType.PASS;
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