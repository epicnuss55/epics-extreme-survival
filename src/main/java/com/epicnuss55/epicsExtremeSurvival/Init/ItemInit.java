package com.epicnuss55.epicsExtremeSurvival.Init;

import com.epicnuss55.epicsExtremeSurvival.EpicsExtremeSurvival;
import com.epicnuss55.epicsExtremeSurvival.Items.*;
import net.minecraft.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EpicsExtremeSurvival.MODID);

    /*----------------------test item----------------------*/
    public static final RegistryObject<Item> test_item = ITEMS.register("test_item", () ->
            new Item(new Item.Properties().
                    food(new Food.Builder().fastToEat().setAlwaysEdible().build()).maxStackSize(20).
                    rarity(Rarity.EPIC).
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE)
            ));

    /*----------------------Lilypad filterer item----------------------*/
    public static final RegistryObject<BlockItem> lilyPadFilterBlockItem = ITEMS.register("lily_pad_filterer", () ->
            new BlockItem(BlockInit.LilyPadFilterBlock.get(), new Item.Properties()
                    .maxStackSize(64)
                    .group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE)
            ));

    /*----------------------Bamboo Filter----------------------*/
    public static final RegistryObject<Item> bambooFilter_empty = ITEMS.register("bamboofilter_empty", () ->
            new BambooFilter_empty(new Item.Properties().
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                    setNoRepair().
                    maxStackSize(1).
                    maxDamage(30)
            ));
    public static final RegistryObject<Item> bambooFilter_fill = ITEMS.register("bamboofilter_fill", () ->
            new BambooFilter_fill(new Item.Properties().
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                    setNoRepair().
                    maxStackSize(1).
                    maxDamage(30).
                    containerItem(bambooFilter_empty.get())
            ));

    /*----------------------Drinks/Juices----------------------*/
    public static final RegistryObject<Item> Purified_Water = ITEMS.register("purified_water", () ->
            new Juices.JuiceItem(1.5f, new Item.Properties().
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                    maxStackSize(1)
            ));
    public static final RegistryObject<Item> Apple_Juice = ITEMS.register("apple_juice", () ->
            new Juices.JuiceItem(3.5f, new Item.Properties().
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                    maxStackSize(1)
            ));
    public static final RegistryObject<Item> Beetroot_Juice = ITEMS.register("beetroot_juice", () ->
            new Juices.JuiceItem(2.5f, new Item.Properties().
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                    maxStackSize(1)
            ));
    public static final RegistryObject<Item> Carrot_Juice = ITEMS.register("carrot_juice", () ->
            new Juices.JuiceItem(3f, new Item.Properties().
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                    maxStackSize(1)
            ));
    public static final RegistryObject<Item> Chorus_Fruit_Juice = ITEMS.register("chorusfruit_juice", () ->
            Juices.createChorusFruitJuice());
    public static final RegistryObject<Item> God_Apple_Juice = ITEMS.register("enchantedgoldenapple_juice", () ->
            Juices.createGodGoldenAppleJuice());
    public static final RegistryObject<Item> Golden_Apple_Juice = ITEMS.register("goldenapple_juice", () ->
            Juices.createGoldenAppleJuice());
    public static final RegistryObject<Item> Golden_Carrot_Juice = ITEMS.register("goldencarrot_juice", () ->
            new Juices.JuiceItem(4.5f, new Item.Properties().
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                    maxStackSize(1)
            ));
    public static final RegistryObject<Item> Melon_Juice = ITEMS.register("melon_juice", () ->
            new Juices.JuiceItem(2.5f, new Item.Properties().
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                    maxStackSize(1)
            ));
    public static final RegistryObject<Item> SweetBerry_Juice = ITEMS.register("sweetberry_juice", () ->
            new Juices.JuiceItem(2.5f, new Item.Properties().
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                    maxStackSize(1)
            ));
}