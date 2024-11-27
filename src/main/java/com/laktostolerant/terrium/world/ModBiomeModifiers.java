package com.laktostolerant.terrium.world;

import com.laktostolerant.terrium.Terrium;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.gen.GenerationStep;

public class ModBiomeModifiers {

    public static void modifyBiomes() {
        // Add Depth Features to all Overworld biomes
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD), // Select all Overworld biomes
                GenerationStep.Feature.UNDERGROUND_DECORATION,
                ModPlacedFeatures.DEPTHS_63_PLACED_KEY
        );

        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Feature.UNDERGROUND_DECORATION,
                ModPlacedFeatures.DEPTHS_79_PLACED_KEY
        );

        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Feature.UNDERGROUND_DECORATION,
                ModPlacedFeatures.DEPTHS_95_PLACED_KEY
        );

        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Feature.UNDERGROUND_DECORATION,
                ModPlacedFeatures.DEPTHS_111_PLACED_KEY
        );

        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Feature.UNDERGROUND_DECORATION,
                ModPlacedFeatures.DEPTHS_127_PLACED_KEY
        );
    }
}
