package com.epicnuss55.epicsExtremeSurvival.Init;

import com.epicnuss55.epicsExtremeSurvival.EpicsExtremeSurvival;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.RegistryObject;
import net.minecraft.item.Rarity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EpicsExtremeSurvival.MODID);

    public static final RegistryObject<Item> test_item = ITEMS.register("test_item", () ->
            new Item(new Item.Properties().
                    food(new Food.Builder().fastToEat().setAlwaysEdible().build()).maxStackSize(20).
                    rarity(Rarity.EPIC).
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE)));

}
