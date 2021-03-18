package com.epicnuss55.epicsExtremeSurvival;

import com.epicnuss55.epicsExtremeSurvival.Init.BlockInit;
import com.epicnuss55.epicsExtremeSurvival.Init.PlayerStatsSaver;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import com.epicnuss55.epicsExtremeSurvival.Events.ThirstStuffs;
import com.epicnuss55.epicsExtremeSurvival.Init.ItemInit;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Logger;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

@Mod(EpicsExtremeSurvival.MODID)
public class EpicsExtremeSurvival {
    public static EpicsExtremeSurvival instance;
    public static final String MODID = "epicsextremesurvival";
    public static final Logger LOGGER = LogManager.getLogger();

    public EpicsExtremeSurvival() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::doClientStuff);
        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        instance = this;
        MinecraftForge.EVENT_BUS.register(new ThirstStuffs());
        MinecraftForge.EVENT_BUS.register(new PlayerStatsSaver());
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }
    public static class SurvivalItemGroup extends ItemGroup {

        public static final SurvivalItemGroup INSTANCE = new SurvivalItemGroup(ItemGroup.GROUPS.length, "epics_extreme_survival_item_group");

        public SurvivalItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return ItemInit.test_item.get().getDefaultInstance();
        }
    }
}
