package com.epicnuss55.epicsExtremeSurvival.Init;

import com.epicnuss55.epicsExtremeSurvival.EpicsExtremeSurvival;
import com.epicnuss55.epicsExtremeSurvival.Items.*;
import net.minecraft.item.BlockItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.RegistryObject;
import net.minecraft.item.Rarity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EpicsExtremeSurvival.MODID);

    /*-------------------------------------------ITEMS-------------------------------------------*/
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
            new PurifiedWater(new Item.Properties().
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                    maxStackSize(1).
                    containerItem(bambooFilter_empty.get())
            ));
    public static final RegistryObject<Item> Apple_Juice = ITEMS.register("apple_juice", () ->
            new AppleJuice(new Item.Properties().
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                    maxStackSize(1).
                    containerItem(bambooFilter_empty.get())
            ));
    public static final RegistryObject<Item> Beetroot_Juice = ITEMS.register("beetroot_juice", () ->
            new BeetrootJuice(new Item.Properties().
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                    maxStackSize(1).
                    containerItem(bambooFilter_empty.get())
            ));
    public static final RegistryObject<Item> Carrot_Juice = ITEMS.register("carrot_juice", () ->
            new CarrotJuice(new Item.Properties().
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                    maxStackSize(1).
                    containerItem(bambooFilter_empty.get())
            ));
    public static final RegistryObject<Item> Chorus_Fruit_Juice = ITEMS.register("chorusfruit_juice", () ->
            new ChorusFruitJuice(new Item.Properties().
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                    maxStackSize(1).
                    containerItem(bambooFilter_empty.get())
            ));
    public static final RegistryObject<Item> God_Apple_Juice = ITEMS.register("enchantedgoldenapple_juice", () ->
            new GOD_GOLDEN_AppleJuice(new Item.Properties().
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                    maxStackSize(1).
                    containerItem(bambooFilter_empty.get())
            ));
    public static final RegistryObject<Item> Golden_Apple_Juice = ITEMS.register("goldenapple_juice", () ->
            new GOLDEN_AppleJuice(new Item.Properties().
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                    maxStackSize(1).
                    containerItem(bambooFilter_empty.get())
            ));
    public static final RegistryObject<Item> Golden_Carrot_Juice = ITEMS.register("goldencarrot_juice", () ->
            new GOLDEN_CarrotJuice(new Item.Properties().
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                    maxStackSize(1).
                    containerItem(bambooFilter_empty.get())
            ));
    public static final RegistryObject<Item> Melon_Juice = ITEMS.register("melon_juice", () ->
            new MelonJuice(new Item.Properties().
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                    maxStackSize(1).
                    containerItem(bambooFilter_empty.get())
            ));
    public static final RegistryObject<Item> SweetBerry_Juice = ITEMS.register("sweetberry_juice", () ->
            new SweetBerryJuice(new Item.Properties().
                    group(EpicsExtremeSurvival.SurvivalItemGroup.INSTANCE).
                    maxStackSize(1).
                    containerItem(bambooFilter_empty.get())
            ));
}