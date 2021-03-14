package com.epicnuss55.epicsExtremeSurvival.Tiles;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class LilyPadFilterer_Processed extends TileEntity implements ITickableTileEntity {
    public LilyPadFilterer_Processed(TileEntityType<?> tileEntityTypeIn) {
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
