package com.laktostolerant.terrium.util;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import net.minecraft.world.chunk.ChunkNibbleArray;
import net.minecraft.world.chunk.ChunkProvider;
import net.minecraft.world.chunk.light.BlockLightStorage;

//duskweed lit tracker and storage
public class DuskweedLightStorage extends BlockLightStorage {

    private final LongSet duskweedLitPositions = new LongOpenHashSet();

    public DuskweedLightStorage(ChunkProvider chunkProvider) {
        super(chunkProvider);
    }
    public boolean isDuskweedLit(long blockPos) {
        return duskweedLitPositions.contains(blockPos);
    }

    public void setDuskweedLit(long blockPos) {
        duskweedLitPositions.add(blockPos);
    }

    public void clearDuskweedLit(long blockPos) {
        duskweedLitPositions.remove(blockPos);
    }

    public static class Data extends BlockLightStorage.Data {
        public Data(Long2ObjectOpenHashMap<ChunkNibbleArray> map) {
            super(map);
        }
    }
}
