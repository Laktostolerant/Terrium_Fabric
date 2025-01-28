package com.laktostolerant.terrium.world.biome.surface;

import com.laktostolerant.terrium.world.biome.ModBiomes;
import com.terraformersmc.biolith.api.biome.BiomePlacement;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;

public class ModBiolithRegion {

    public static void init() {
        BiomePlacement.addOverworld(ModBiomes.ABYSS_BIOME,
                new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(-1f, 1f),
                        MultiNoiseUtil.ParameterRange.of(-1f, 1f),
                        MultiNoiseUtil.ParameterRange.of(-1f, 1f),
                        MultiNoiseUtil.ParameterRange.of(-1f, 1f),
                        MultiNoiseUtil.ParameterRange.of(2.4f, 3f),
                        MultiNoiseUtil.ParameterRange.of(-1f, 1f),
                        0L

                )
        );
        BiomePlacement.addOverworld(ModBiomes.DEEP_JUNGLE,
                new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(-1f, 1f),
                        MultiNoiseUtil.ParameterRange.of(-1f, 1f),
                        MultiNoiseUtil.ParameterRange.of(-1f, 1f),
                        MultiNoiseUtil.ParameterRange.of(-1f, 1f),
                        MultiNoiseUtil.ParameterRange.of(1.68f, 2.3f),
                        MultiNoiseUtil.ParameterRange.of(-1f, 1f),
                        0L

                )
        );

        BiomePlacement.removeOverworld(BiomeKeys.DEEP_DARK);
        BiomePlacement.addOverworld(ModBiomes.DEEP_DARK,
                new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(-1f, 1f),
                        MultiNoiseUtil.ParameterRange.of(-1f, 1f),
                        MultiNoiseUtil.ParameterRange.of(-1f, 1f),
                        MultiNoiseUtil.ParameterRange.of(-1f, -0.375f),
                        MultiNoiseUtil.ParameterRange.of(0.9f, 0.95f),
                        MultiNoiseUtil.ParameterRange.of(-1f, 1f),
                        0L

                )
        );

    }

}
