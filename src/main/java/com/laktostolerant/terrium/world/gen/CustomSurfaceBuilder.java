package com.laktostolerant.terrium.world.gen;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.random.RandomSplitter;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public class CustomSurfaceBuilder extends SurfaceBuilder {

    public CustomSurfaceBuilder(NoiseConfig noiseConfig, BlockState defaultState, int seaLevel, RandomSplitter randomDeriver) {
        super(noiseConfig, defaultState, seaLevel, randomDeriver);
    }
}
