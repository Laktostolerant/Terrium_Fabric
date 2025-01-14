package com.laktostolerant.terrium.world.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class ModOverworldRegion extends Region {
    public ModOverworldRegion(Identifier name, RegionType type, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube,
                    RegistryKey<Biome>>> mapper) {

        this.addModifiedVanillaOverworldBiomes(mapper, modifiedVanillaOverworldBuilder -> {
            modifiedVanillaOverworldBuilder.replaceBiome(BiomeKeys.LUSH_CAVES, ModBiomes.DEEP_JUNGLE);
        });
        MultiNoiseUtil.ParameterRange temperature = MultiNoiseUtil.ParameterRange.of(0.7F, 0.8F);
        MultiNoiseUtil.ParameterRange humidity = MultiNoiseUtil.ParameterRange.of(0.4F, 0.6F);
        MultiNoiseUtil.ParameterRange continentalness = MultiNoiseUtil.ParameterRange.of(-1.0F, -0.7F);
        MultiNoiseUtil.ParameterRange erosion = MultiNoiseUtil.ParameterRange.of(0.0F, 0.2F);
        MultiNoiseUtil.ParameterRange depth = MultiNoiseUtil.ParameterRange.of(2F, 2.5F);
        MultiNoiseUtil.ParameterRange weirdness = MultiNoiseUtil.ParameterRange.of(-0.5F, -0.2F);

        float offset = 0.0F;

        // Call addBiome to map your custom biome
        this.addBiome(mapper, temperature, humidity, continentalness, erosion, weirdness, depth, offset, ModBiomes.ABYSS_BIOME);
        this.addBiome(mapper, temperature, humidity, continentalness, erosion, weirdness, depth, offset, ModBiomes.DEEP_JUNGLE);
    }
}