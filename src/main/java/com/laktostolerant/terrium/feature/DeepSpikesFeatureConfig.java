package com.laktostolerant.terrium.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

public record DeepSpikesFeatureConfig(int minRadius, int maxRadius, int minYThreshold) implements FeatureConfig {

    public static final Codec<DeepSpikesFeatureConfig> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("min_radius").orElse(4).forGetter(DeepSpikesFeatureConfig::minRadius),
                    Codec.INT.fieldOf("max_radius").orElse(6).forGetter(DeepSpikesFeatureConfig::maxRadius),
                    Codec.INT.fieldOf("min_y_threshold").orElse(-270).forGetter(DeepSpikesFeatureConfig::minYThreshold)
            ).apply(instance, DeepSpikesFeatureConfig::new)
    );
}