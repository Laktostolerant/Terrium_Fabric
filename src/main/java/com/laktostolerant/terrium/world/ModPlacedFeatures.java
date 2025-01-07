package com.laktostolerant.terrium.world;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.world.biome.ModBiomes;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;
import java.util.Set;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> HELLITE_ORE_PLACED_KEY = registerKey("hellite_ore_placed");
    public static final RegistryKey<PlacedFeature> ABYSS_PLANTS_PLACED_KEY = registerKey("abyss_plants_placed_key");

    public static final RegistryKey<PlacedFeature> DARKELP_PLACED_KEY = registerKey("darkelp_placed_key");


    private static final Set<RegistryKey<Biome>> EXCLUDED_BIOMES = Set.of(
            BiomeKeys.DEEP_DARK,
            BiomeKeys.LUSH_CAVES,
            BiomeKeys.DRIPSTONE_CAVES,
            ModBiomes.DEEP_JUNGLE
    );


    public static void boostrap(Registerable<PlacedFeature> context) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        RegistryEntry<ConfiguredFeature<?, ?>> abyss_plants_configured_key = registryEntryLookup.getOrThrow(ModConfiguredFeatures.ABYSS_PLANTS_KEY);

        RegistryEntry<ConfiguredFeature<?, ?>> darkelp_configured_key = registryEntryLookup.getOrThrow(ModConfiguredFeatures.DARKELP_CONFIGURED_KEY);

        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, HELLITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.HELLITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(2,  //place per chunks
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(5), YOffset.aboveBottom(35))));


        register(
                context,
                ABYSS_PLANTS_PLACED_KEY,
                abyss_plants_configured_key,
                CountPlacementModifier.of(25),
                SquarePlacementModifier.of(),
                PlacedFeatures.BOTTOM_TO_120_RANGE,
                EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12),
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)),
                BiomePlacementModifier.of()
        );


        register(
                context,
                DARKELP_PLACED_KEY,
                darkelp_configured_key,
                CountPlacementModifier.of(100),
                SquarePlacementModifier.of(),
                PlacedFeatures.BOTTOM_TO_120_RANGE,
                EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12),
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)),
                BiomePlacementModifier.of()
        );

    }










    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Terrium.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    public static void register(
            Registerable<PlacedFeature> featureRegisterable,
            RegistryKey<PlacedFeature> key,
            RegistryEntry<ConfiguredFeature<?, ?>> feature,
            PlacementModifier... modifiers
    ) {
        register(featureRegisterable, key, feature, List.of(modifiers));
    }
}
