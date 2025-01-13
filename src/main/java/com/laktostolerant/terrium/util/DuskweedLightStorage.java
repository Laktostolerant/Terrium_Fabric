package com.laktostolerant.terrium.util;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;

import net.minecraft.world.chunk.ChunkNibbleArray;
import net.minecraft.world.chunk.ChunkProvider;
import net.minecraft.world.chunk.light.BlockLightStorage;

//duskweed lit tracker and storage
public class DuskweedLightStorage extends BlockLightStorage {


    protected DuskweedLightStorage(ChunkProvider chunkProvider) {
        super(chunkProvider);
    }

    public static class Data extends BlockLightStorage.Data {
        public Data(Long2ObjectOpenHashMap<ChunkNibbleArray> map) {
            super(map);
        }
    }
}
