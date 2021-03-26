package com.epicnuss55.epicsExtremeSurvival.Items;

import com.epicnuss55.epicsExtremeSurvival.EpicsExtremeSurvival;
import com.epicnuss55.epicsExtremeSurvival.Events.ThirstStuffs;
import com.epicnuss55.epicsExtremeSurvival.Init.ItemInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class BambooFilter_fill extends Item {
    public BambooFilter_fill(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack oldItem = playerIn.getHeldItem(handIn);
        ItemStack newItem = new ItemStack(ItemInit.bambooFilter_empty.get());
        playerIn.setHeldItem(handIn, newItem);
        ThirstStuffs.AddThirst(1f);
        if (!playerIn.isCreative()) {
            playerIn.getHeldItem(handIn).setDamage(oldItem.getDamage() + 1);
            EpicsExtremeSurvival.LOGGER.info(playerIn.getHeldItem(handIn).getDamage());
            if (playerIn.getHeldItem(handIn).getDamage() == ItemInit.bambooFilter_fill.get().getMaxDamage()) {
                playerIn.setHeldItem(handIn, ItemStack.EMPTY);
                worldIn.playSound(playerIn, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                playerIn.sendBreakAnimation(handIn);
            }
        }
        worldIn.playSound(playerIn, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 30;
    }

    @Override
    public boolean isDamageable() {
        return true;
    }

}