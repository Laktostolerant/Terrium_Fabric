package com.laktostolerant.terrium.util;
/*
import com.laktostolerant.terrium.block.ModBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkProvider;
import net.minecraft.world.chunk.light.ChunkBlockLightProvider;
import net.minecraft.world.chunk.light.ChunkLightProvider;


public class DuskweedBlockLightProvider extends ChunkBlockLightProvider {

    public DuskweedBlockLightProvider(ChunkProvider chunkProvider) {
        super(chunkProvider, new DuskweedLightStorage(chunkProvider));
    }

//    private DuskweedLightStorage getDuskweedStorage() {
//        return (DuskweedLightStorage) this.lightStorage;
//    }
    /* ChunkBlockLightProviderAccessor accessor = (ChunkBlockLightProviderAccessor) this;
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
        }*/
/*
    //checknode in mojmap
    @Override
    protected void method_51529(long blockPos) {

        super.method_51529(blockPos);

    }
*/
    // propagateIncrease in mojmap, otherwise 31
 /*   @Override
    protected void method_51531(long blockPos, long flags, int lightLevel) {
        /*
               DuskweedLightStorage storage = getDuskweedStorage();
        boolean sourceIsDuskweedLit = storage.isDuskweedLit(blockPos);
        if (sourceIsDuskweedLit) {
            for (Direction dir : Direction.values()) {

                if (ChunkLightProvider.class_8531.isDirectionBitSet(flags, dir)) {
                    long neighborPos = BlockPos.offset(blockPos, dir);
                    int neighborLevel = storage.get(neighborPos);
                    if (neighborLevel >= (lightLevel - 1)) {
                        storage.setDuskweedLit(neighborPos);
                    }
                }

            }

         *//*
        ChunkBlockLightProviderAccessor accessor = (ChunkBlockLightProviderAccessor) this;
        BlockPos.Mutable mutablePos = accessor.getMutablePos();
        BlockState state = getStateForLighting(mutablePos.set(blockPos));

        if (state.isOf(ModBlocks.DUSKWEED)) {
            for (Direction direction : DIRECTIONS) {
                if (class_8531.isDirectionBitSet(lightLevel, direction)) {
                    long m = BlockPos.offset(blockPos, direction);
                    if (((LightStorageAccessor) this).sectionContains(ChunkSectionPos.fromBlockPos(m))) {
                        int i = ((LightStorageAccessor) this).InvokeGet(m);
                        int j = lightLevel - 4;
                        if (j > i) {
                            ((ChunkBlockLightProviderAccessor) this).getMutablePos().set(m);
                            BlockState blockState2 = this.getStateForLighting(((ChunkBlockLightProviderAccessor) this).getMutablePos());
                            int k = lightLevel - this.getOpacity(blockState2, ((ChunkBlockLightProviderAccessor) this).getMutablePos());
                            if (k > i) {
                                if (state == null) {
                                    state = class_8531.isTrivial(lightLevel) ? Blocks.AIR.getDefaultState() : this.getStateForLighting(((ChunkBlockLightProviderAccessor) this).getMutablePos().set(blockPos));
                                }

                                if (!this.shapesCoverFullCube(blockPos, state, m, blockState2, direction)) {
                                    ((LightStorageAccessor) this).InvokeSet(m, k);
                                    if (k > 1) {
                                        this.method_51566(m, class_8531.method_51574(k, isTrivialForLighting(blockState2), direction.getOpposite()));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            super.method_51531(blockPos, flags, lightLevel);
        }

    }
}
*/
   /* //propagaeDecrease in mojmap
    @Override
    protected void method_51530(long blockPos, long flags) {
       super.method_51530(blockPos, flags);
}}
*/
