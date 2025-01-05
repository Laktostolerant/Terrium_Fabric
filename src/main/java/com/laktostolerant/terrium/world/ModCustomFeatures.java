package com.laktostolerant.terrium.world;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.feature.DarkelpFeature;
import com.mojang.serialization.Lifecycle;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class ModCustomFeatures {
    public static final RegistryKey<Feature<?>> DARKELP_FEATURE_KEY = RegistryKey.of(RegistryKeys.FEATURE, Identifier.of(Terrium.MOD_ID, "darkelp_custom_feature_key"));


    public static final Feature<DefaultFeatureConfig> DARKELP_FEATURE_CONFIG = new DarkelpFeature(DefaultFeatureConfig.CODEC, ModBlocks.DARKELP.getDefaultState(), 5);

    public static void bootstrap(Registerable<Feature<?>> context) {
        context.register(DARKELP_FEATURE_KEY, DARKELP_FEATURE_CONFIG, Lifecycle.stable());
    }
}
