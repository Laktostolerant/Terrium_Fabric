package com.laktostolerant.terrium.world.biome;

import com.laktostolerant.terrium.Terrium;
import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;



import java.util.function.Consumer;

public class ModOverworldRegion extends Region {

    public static Identifier LOC = Identifier.of(Terrium.MOD_ID, "underground biomes");

    public ModOverworldRegion(int weight) {
        super(LOC, RegionType.OVERWORLD, weight);
    }


    MultiNoiseUtil.ParameterRange temperature = MultiNoiseUtil.ParameterRange.of(0.7F, 0.8F);
    MultiNoiseUtil.ParameterRange humidity = MultiNoiseUtil.ParameterRange.of(0.4F, 0.6F);
    MultiNoiseUtil.ParameterRange continentalness = MultiNoiseUtil.ParameterRange.of(-1.0F, 1F);
    MultiNoiseUtil.ParameterRange erosion = MultiNoiseUtil.ParameterRange.of(-1F, 1F);
    MultiNoiseUtil.ParameterRange weirdness = MultiNoiseUtil.ParameterRange.of(-1F, 1F);
    MultiNoiseUtil.ParameterRange purshale_depth = MultiNoiseUtil.ParameterRange.of(1.68F, 2.1F);
    MultiNoiseUtil.ParameterRange pyscoria_depth = MultiNoiseUtil.ParameterRange.of(2.3F, 3.0F);
    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube,
            RegistryKey<Biome>>> mapper) {


        float offset = 0.0F;

//        this.addBiome(mapper, temperature, humidity, continentalness, erosion, weirdness, purshale_depth, offset, ModBiomes.DEEP_JUNGLE);
//        this.addBiome(mapper, temperature, humidity, continentalness, erosion, weirdness, pyscoria_depth, offset, ModBiomes.ABYSS_BIOME);
//        mapper.accept(Pair.of(MultiNoiseUtil.createNoiseHypercube(temperature, humidity, continentalness, erosion, MultiNoiseUtil.ParameterRange.of(2.3f, 3f), weirdness, 1f), ModBiomes.ABYSS_BIOME));
//        mapper.accept(Pair.of(MultiNoiseUtil.createNoiseHypercube(temperature, humidity, continentalness, erosion, MultiNoiseUtil.ParameterRange.of(1.68f, 2.1f), weirdness, 1f), ModBiomes.DEEP_JUNGLE));
          this.addBiome(registry, mapper);

    }
}