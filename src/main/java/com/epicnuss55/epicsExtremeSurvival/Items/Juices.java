package com.epicnuss55.epicsExtremeSurvival.Items;

import com.epicnuss55.epicsExtremeSurvival.EpicsExtremeSurvival;
import com.epicnuss55.epicsExtremeSurvival.Events.ThirstStuffs;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class Juices {

    public static class JuiceItem extends Item {
        private float thirst;

        public JuiceItem(float AddThirst, Properties properties) {
            super(properties);
            this.thirst = AddThirst;
        }

        @Override
        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            if (entityLiving instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entityLiving;
                CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
            }

            if (entityLiving instanceof PlayerEntity && !((PlayerEntity) entityLiving).abilities.isCreativeMode) {
                stack.shrink(1);
            }
            ThirstStuffs.AddThirst(thirst);

            return stack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : stack;
        }

        @Override
        public UseAction getUseAction(ItemStack stack) {
            return UseAction.DRINK;
        }

        @Override
        public int getUseDuration(ItemStack stack) {
            return 32;
        }

        @Override
        public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
            return DrinkHelper.startDrinking(worldIn, playerIn, handIn);
        }
    }


    public static ChorusFruitJuice createChorusFruitJuice() {
        return new ChorusFruitJuice(3.5f, new Item.Properties().
            group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
            maxStackSize(1)
        );
    }

    public static GodGoldenAppleJuice createGodGoldenAppleJuice() {
        return new GodGoldenAppleJuice(3.5f, new Item.Properties().
                group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                maxStackSize(1)
        );
    }

    public static GoldenAppleJuice createGoldenAppleJuice() {
        return new GoldenAppleJuice(3.5f, new Item.Properties().
                group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                maxStackSize(1)
        );
    }


    private static class ChorusFruitJuice extends AdvancedJuice {

        public ChorusFruitJuice(float AddThirst, Properties properties) {
            super(AddThirst, properties);
        }

        @Override
        public void Action(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            if (!worldIn.isRemote) {
                double d0 = entityLiving.getPosX();
                double d1 = entityLiving.getPosY();
                double d2 = entityLiving.getPosZ();

                for(int i = 0; i < 16; ++i) {
                    double d3 = entityLiving.getPosX() + (entityLiving.getRNG().nextDouble() - 0.5D) * 16.0D;
                    double d4 = MathHelper.clamp(entityLiving.getPosY() + (double)(entityLiving.getRNG().nextInt(16) - 8), 0.0D, (double)(worldIn.func_234938_ad_() - 1));
                    double d5 = entityLiving.getPosZ() + (entityLiving.getRNG().nextDouble() - 0.5D) * 16.0D;
                    if (entityLiving.isPassenger()) {
                        entityLiving.stopRiding();
                    }

                    if (entityLiving.attemptTeleport(d3, d4, d5, true)) {
                        SoundEvent soundevent = entityLiving instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT : SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                        worldIn.playSound((PlayerEntity)null, d0, d1, d2, soundevent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                        entityLiving.playSound(soundevent, 1.0F, 1.0F);
                        break;
                    }
                }

                if (entityLiving instanceof PlayerEntity) {
                    ((PlayerEntity)entityLiving).getCooldownTracker().setCooldown(this, 20);
                }
            }
        }
    }


    private static class GodGoldenAppleJuice extends AdvancedJuice {

        public GodGoldenAppleJuice(float AddThirst, Properties properties) {
            super(AddThirst, properties);
        }

        @Override
        public void Action(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            entityLiving.addPotionEffect(new EffectInstance(Effects.REGENERATION, 400, 1));
            entityLiving.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 6000, 0));
            entityLiving.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 6000, 0));
            entityLiving.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 2400, 3));
        }
    }

    private static class GoldenAppleJuice extends AdvancedJuice {
        public GoldenAppleJuice(float AddThirst, Properties properties) {
            super(AddThirst, properties);
        }

        @Override
        public void Action(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            entityLiving.addPotionEffect(new EffectInstance(Effects.REGENERATION, 100, 1));
            entityLiving.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 2400, 0));
        }
    }

    public static abstract class AdvancedJuice extends Item {

        private float thirst;

        public AdvancedJuice(float AddThirst, Properties properties) {
            super(properties);
            this.thirst = AddThirst;
        }

        public abstract void Action(ItemStack stack, World worldIn, LivingEntity entityLiving);

        @Override
        public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
            if (entityLiving instanceof ServerPlayerEntity) {
                ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entityLiving;
                CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
            }

            if (entityLiving instanceof PlayerEntity && !((PlayerEntity)entityLiving).abilities.isCreativeMode) {
                stack.shrink(1);
            }
            Action(stack, worldIn, entityLiving);
            ThirstStuffs.AddThirst(thirst);

            return stack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : stack;
        }

        @Override
        public UseAction getUseAction(ItemStack stack) {
            return UseAction.DRINK;
        }

        @Override
        public int getUseDuration(ItemStack stack) {
            return 32;
        }

        @Override
        public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
            return DrinkHelper.startDrinking(worldIn, playerIn, handIn);
        }
    }
}
