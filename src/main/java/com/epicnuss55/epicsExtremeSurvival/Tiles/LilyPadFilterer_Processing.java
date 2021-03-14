package com.epicnuss55.epicsExtremeSurvival.Tiles;

import com.epicnuss55.epicsExtremeSurvival.Init.BlockInit;
import com.epicnuss55.epicsExtremeSurvival.Init.ItemInit;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class LilyPadFilterer_Processing extends TileEntity implements ITickableTileEntity {
    public LilyPadFilterer_Processing(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    private static int ticks = 0;
    @Override
    public void tick() {
        ticks++;
        if (ticks > 1000) {
            this.world.setBlockState(this.pos, )
        }
    }
}
