package com.epicnuss55.epicsExtremeSurvival.Init;

import com.epicnuss55.epicsExtremeSurvival.EpicsExtremeSurvival;
import com.epicnuss55.epicsExtremeSurvival.Events.TemperatureStuffs;
import com.epicnuss55.epicsExtremeSurvival.Events.ThirstStuffs;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = EpicsExtremeSurvival.MODID)
public class PlayerStatsSaver {

    //NBT tags
    public static final String ThirstNBT = "ThirstStats";
    public static final String TemperatureNBT = "TemperatureStats";

    //*----------------SAVER LOGIC & EVENTS----------------*\\

    //loads data
    @SubscribeEvent
    public void worldLoaded(WorldEvent.Load event) {
        if (!event.getWorld().isRemote() && event.getWorld() instanceof ServerWorld) {
            SavedDat saver = SavedDat.forWorld((ServerWorld) event.getWorld());

            if(saver.EpicsExtremeNBT.contains(ThirstNBT))
                ThirstStuffs.read(saver.EpicsExtremeNBT.getCompound(ThirstNBT));

            if(saver.EpicsExtremeNBT.contains(TemperatureNBT))
                TemperatureStuffs.read(saver.EpicsExtremeNBT.getCompound(TemperatureNBT));
        }
    }

    //saves data
    @SubscribeEvent
    public void worldSaved(WorldEvent.Save event) {
        if (!event.getWorld().isRemote() && event.getWorld() instanceof ServerWorld) {
            SavedDat saver = SavedDat.forWorld((ServerWorld) event.getWorld());
            CompoundNBT myData = new CompoundNBT();
            CompoundNBT thirstDat = new CompoundNBT();
            CompoundNBT tempDat = new CompoundNBT();
            myData.put(TemperatureNBT, tempDat);
            myData.put(ThirstNBT, thirstDat);

            ThirstStuffs.write(myData.getCompound(ThirstNBT));
            TemperatureStuffs.write(myData.getCompound(TemperatureNBT));

            saver.EpicsExtremeNBT = myData;
            saver.markDirty();
        }
    }

    //data saver to world
    public static class SavedDat extends WorldSavedData implements Supplier {

        public CompoundNBT EpicsExtremeNBT = new CompoundNBT();

        public SavedDat() {
            super(EpicsExtremeSurvival.MODID);
        }

        public SavedDat(String name) {
            super(name);
        }

        @Override
        public void read(CompoundNBT nbt) {
            if (!nbt.contains(EpicsExtremeSurvival.MODID))
                nbt.put(EpicsExtremeSurvival.MODID, new CompoundNBT());
            EpicsExtremeNBT = nbt.getCompound(EpicsExtremeSurvival.MODID);
        }

        @Override
        public CompoundNBT write(CompoundNBT compound) {
            compound.put(EpicsExtremeSurvival.MODID, EpicsExtremeNBT);
            return compound;
        }

        public static SavedDat forWorld(ServerWorld world) {
            DimensionSavedDataManager storage = world.getSavedData();
            Supplier<SavedDat> sup = new SavedDat();
            SavedDat saver = (SavedDat) storage.getOrCreate(sup, EpicsExtremeSurvival.MODID);
            if (saver == null) {
                saver = new SavedDat();
                storage.set(saver);
            }
            return saver;
        }

        @Override
        public Object get() {
            return this;
        }
    }
}
