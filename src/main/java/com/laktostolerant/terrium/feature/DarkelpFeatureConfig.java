package com.laktostolerant.terrium.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TwistingVinesFeatureConfig;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;

public record DarkelpFeatureConfig(int spreadWidth, int spreadHeight, int maxHeight) implements FeatureConfig  {
    public static final Codec<DarkelpFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(Codecs.POSITIVE_INT.fieldOf("spread_width")
                .forGetter(DarkelpFeatureConfig::spreadWidth), Codecs.POSITIVE_INT.fieldOf("spread_height")
                .forGetter(DarkelpFeatureConfig::spreadHeight), Codecs.POSITIVE_INT.fieldOf("max_height")
                .forGetter(DarkelpFeatureConfig::maxHeight)).apply(instance, DarkelpFeatureConfig::new);
    });

    public DarkelpFeatureConfig(int spreadWidth, int spreadHeight, int maxHeight) {
        this.spreadWidth = spreadWidth;
        this.spreadHeight = spreadHeight;
        this.maxHeight = maxHeight;
    }

    public int spreadWidth() {
        return this.spreadWidth;
    }

    public int spreadHeight() {
        return this.spreadHeight;
    }

    public int maxHeight() {
        return this.maxHeight;
    }
}
