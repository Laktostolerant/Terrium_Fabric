package com.laktostolerant.terrium.world;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.feature.DarkelpFeature;
import com.laktostolerant.terrium.feature.DarkelpFeatureConfig;
import com.laktostolerant.terrium.feature.DeepSpikesFeature;
import com.laktostolerant.terrium.feature.DeepSpikesFeatureConfig;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

public class ModCustomFeatures{
    public static final Feature<DarkelpFeatureConfig> DARKELP_FEATURE = new DarkelpFeature(DarkelpFeatureConfig.CODEC);
    public static final Feature<DeepSpikesFeatureConfig> DEEP_SPIKES_FEATURE = new DeepSpikesFeature(DeepSpikesFeatureConfig.CODEC);

    public static RegistryKey<Feature<?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.FEATURE, Identifier.of(Terrium.MOD_ID, name));
    }

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return (F) Registry.register(Registries.FEATURE, name, feature);
    }


    public static void bootstrap() {
        Terrium.LOGGER.info("BOOTSTRAPPING FEATURES");

        register("darkelp_feature", DARKELP_FEATURE);
        register("deep_spikes_featre", DEEP_SPIKES_FEATURE);
    }
}
