package com.laktostolerant.terrium.world.biome;

import com.laktostolerant.terrium.world.ModPlacedFeatures;
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
                ModPlacedFeatures.ABYSS_PLANTS_PLACED_KEY
        );

        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Feature.UNDERGROUND_DECORATION,
                ModPlacedFeatures.DARKELP_PLACED_KEY
        );

        /*
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.DEEP_JUNGLE),
                GenerationStep.Feature.UNDERGROUND_DECORATION,
                ModPlacedFeatures.DEEP_JUNGLE_PLACED_KEY
        );

         */
    }
}
