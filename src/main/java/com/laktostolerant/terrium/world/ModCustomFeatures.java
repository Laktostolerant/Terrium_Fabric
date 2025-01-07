package com.laktostolerant.terrium.world;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.feature.DarkelpFeature;
import com.laktostolerant.terrium.feature.DarkelpFeatureConfig;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

public class ModCustomFeatures{
    public static final Feature<DarkelpFeatureConfig> DARKELP_FEATURE = new DarkelpFeature(DarkelpFeatureConfig.CODEC);
    // static final RegistryKey<Feature<?>> DARKELP_KEY = registerKey("darkelp_feature_key");

    public static RegistryKey<Feature<?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.FEATURE, Identifier.of(Terrium.MOD_ID, name));
    }

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registries.FEATURE, name, feature);
    }

    public static void bootstrap() {
        Terrium.LOGGER.info("BOOTSTRAPPING FEATURES");

        //Registry.register(Registries.FEATURE, Identifier.of("terrium", "darkelp_feature"), DARKELP_FEATURE);

        /*
        register(
                context,
                DARKELP,
                DARKELP_FEATURE,
                new DarkelpFeatureConfig(1, 2, 8)
        );
        */
    }
}
