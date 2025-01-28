package com.laktostolerant.terrium.world.biome;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.world.ModPlacedFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.*;

import java.util.Set;

public class ModBiomes {
    public static final RegistryKey<Biome> ABYSS_BIOME = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(Terrium.MOD_ID, "abyss_biome"));

    public static final RegistryKey<Biome> DEEP_JUNGLE = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(Terrium.MOD_ID, "deep_jungle"));

    public static final RegistryKey<Biome> TEST_BIOME = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(Terrium.MOD_ID, "test_biome"));

    public static final RegistryKey<Biome> DEEP_DARK = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(Terrium.MOD_ID, "deep_dark"));

    private static final Set<RegistryKey<Biome>> EXCLUDED_BIOMES = Set.of(
            BiomeKeys.DEEP_DARK,
            BiomeKeys.LUSH_CAVES,
            BiomeKeys.DRIPSTONE_CAVES,
            ModBiomes.DEEP_JUNGLE, // Your custom biome
            ModBiomes.ABYSS_BIOME

    );


    public static void boostrap (Registerable<Biome> context) {
        RegistryEntryLookup<PlacedFeature> featureLookup = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
        RegistryEntryLookup<ConfiguredCarver<?>> carverLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER);
        context.register(ABYSS_BIOME, abyssBiome(context));
        context.register(DEEP_JUNGLE, deepJungle(context));
        context.register(TEST_BIOME, testBiome(context));
        context.register(DEEP_DARK, DEEP_DARK(context, featureLookup, carverLookup));
    }

    public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addSprings(builder);
        DefaultBiomeFeatures.addFrozenTopLayer(builder);

    }


    public static Biome abyssBiome(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        //spawnBuilder.spawn(SpawnGroup.AMBIENT, new SpawnSettings.SpawnEntry(ModEntities.VOID_FISH, 20, 10, 15));

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));


        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0xf68ad1)
                        .waterFogColor(0xbf1b26)
                        .skyColor(0x1a0632)
                        .grassColor(0x418dbe)
                        .foliageColor(0xd203fc)
                        .fogColor(0x22a1e6)
                        .moodSound(BiomeMoodSound.CAVE)
                        .music(MusicType.createIngameMusic(RegistryEntry.of(SoundEvents.BLOCK_AMETHYST_CLUSTER_HIT))).build())
                .build();
    }

    public static Biome deepJungle(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.BOGGED, 5, 1, 1));

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));


        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addMossyRocks(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addExtraGoldOre(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.DEEP_JUNGLE_PLANTS_PLACED_KEY);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.DEEP_JUNGLE_TREES_PLACED_KEY);

        //biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, UndergroundPlacedFeatures.LUSH_CAVES_VEGETATION);



        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x629d89)
                        .waterFogColor(0xbf1b26)
                        .skyColor(0x30c918)
                        .grassColor(0xab8d43)
                        .foliageColor(0x00d52c)
                        .fogColor(0x22a1e6)
                        .moodSound(BiomeMoodSound.CAVE)
                        .music(MusicType.createIngameMusic(RegistryEntry.of(SoundEvents.BLOCK_AMETHYST_CLUSTER_HIT))).build())
                .build();
    }

    public static Biome testBiome(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.AXOLOTL, 5, 4, 4));


        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));


        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addMossyRocks(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addExtraGoldOre(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS);
        DefaultBiomeFeatures.addForestFlowers(biomeBuilder);
        DefaultBiomeFeatures.addLargeFerns(biomeBuilder);

        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);



        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0xf68ad1)
                        .waterFogColor(0xbf1b26)
                        .skyColor(0x30c918)
                        .grassColor(0x2f022f)
                        .foliageColor(0xd203fc)
                        .fogColor(0x22a1e6)
                        .moodSound(BiomeMoodSound.CAVE)
                        .music(MusicType.createIngameMusic(RegistryEntry.of(SoundEvents.BLOCK_AMETHYST_CLUSTER_HIT))).build())
                .build();
    }


    public static Biome DEEP_DARK (Registerable<Biome> context, RegistryEntryLookup<PlacedFeature> featureLookup, RegistryEntryLookup<ConfiguredCarver<?>> carverLookup) {
        SpawnSettings.Builder builder = new SpawnSettings.Builder();
        GenerationSettings.LookupBackedBuilder lookupBackedBuilder = new GenerationSettings.LookupBackedBuilder(featureLookup, carverLookup);
        lookupBackedBuilder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.CAVE);
        lookupBackedBuilder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.CAVE_EXTRA_UNDERGROUND);
        lookupBackedBuilder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.CANYON);
        DefaultBiomeFeatures.addAmethystGeodes(lookupBackedBuilder);
        DefaultBiomeFeatures.addDungeons(lookupBackedBuilder);
        DefaultBiomeFeatures.addMineables(lookupBackedBuilder);
        DefaultBiomeFeatures.addFrozenTopLayer(lookupBackedBuilder);
        DefaultBiomeFeatures.addPlainsTallGrass(lookupBackedBuilder);
        DefaultBiomeFeatures.addDefaultOres(lookupBackedBuilder);
        DefaultBiomeFeatures.addDefaultDisks(lookupBackedBuilder);
        DefaultBiomeFeatures.addPlainsFeatures(lookupBackedBuilder);
        DefaultBiomeFeatures.addDefaultMushrooms(lookupBackedBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(lookupBackedBuilder);
        DefaultBiomeFeatures.addSculk(lookupBackedBuilder);
        MusicSound musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_DEEP_DARK);
        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.4f)
                .spawnSettings(builder.build())
                .generationSettings(lookupBackedBuilder.build())
                .effects((new BiomeEffects.Builder())
                .waterColor(4159204)     // Vanilla Deep Dark water color (#3F76E4)
                .waterFogColor(329011)   // Vanilla Deep Dark water fog (#050533)
                .fogColor(12638463)      // Vanilla Deep Dark fog color (#C0D8FF)// No foliage
                        .skyColor(7907327)
                .moodSound(BiomeMoodSound.CAVE)
                        .music(musicSound)
                        .build())// Cave ambiance
                .build();
    }
}
