package com.laktostolerant.terrium.util;

import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.mixin.accessors.ChunkBlockLightProviderAccessor;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.chunk.ChunkProvider;
import net.minecraft.world.chunk.light.ChunkBlockLightProvider;
import net.minecraft.world.chunk.light.ChunkLightProvider;


public class DuskweedBlockLightProvider extends ChunkBlockLightProvider {

    public DuskweedBlockLightProvider(ChunkProvider chunkProvider) {
        super(chunkProvider, new DuskweedLightStorage(chunkProvider));
    }

    private DuskweedLightStorage getDuskweedStorage() {
        return (DuskweedLightStorage) this.lightStorage;
    }
    //checknode in mojmap
    @Override
    protected void method_51529(long blockPos) {
        ChunkBlockLightProviderAccessor accessor = (ChunkBlockLightProviderAccessor) this;
        BlockPos.Mutable mutablePos = accessor.getMutablePos();
        BlockState state = getStateForLighting(mutablePos.set(blockPos));

        if (state.isOf(ModBlocks.DUSKWEED)) {
            getDuskweedStorage().set(blockPos, 15);
            getDuskweedStorage().setDuskweedLit(blockPos);

            this.method_51566(blockPos,
                    ChunkLightProvider.class_8531.method_51573(
                            15,
                            isTrivialForLighting(state)
                    )
            );
        }
        super.method_51529(blockPos);
    }
    // propagateIncrease in mojmap, otherwise 31
    @Override
    protected void method_51531(long blockPos, long flags, int lightLevel) {
        DuskweedLightStorage storage = getDuskweedStorage();
        boolean sourceIsDuskweedLit = storage.isDuskweedLit(blockPos);
        super.method_51531(blockPos, flags, lightLevel-8);
        if (sourceIsDuskweedLit && lightLevel > 1) {
            for (Direction dir : Direction.values()) {
                if (ChunkLightProvider.class_8531.isDirectionBitSet(flags, dir)) {
                    long neighborPos = BlockPos.offset(blockPos, dir);
                    int neighborLevel = storage.get(neighborPos);
                    if (neighborLevel >= (lightLevel - 1)) {
                        storage.setDuskweedLit(neighborPos);
                    }
                }
            }
        }
    }
    //propagaeDecrease in mojmap
    @Override
    protected void method_51530(long blockPos, long flags) {
        super.method_51530(blockPos, flags);
        DuskweedLightStorage storage = getDuskweedStorage();
        int newLevel = storage.get(blockPos);
        if (newLevel <= 0) {
            storage.clearDuskweedLit(blockPos);
        }
    }
}
