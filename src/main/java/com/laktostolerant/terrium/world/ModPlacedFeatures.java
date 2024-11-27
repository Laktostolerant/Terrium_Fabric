package com.laktostolerant.terrium.world;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> HELLITE_ORE_PLACED_KEY = registerKey("hellite_ore_placed");

    public static final RegistryKey<PlacedFeature> DEPTHS_63_PLACED_KEY = registerKey("depths_63_placed");
    public static final RegistryKey<PlacedFeature> DEPTHS_79_PLACED_KEY = registerKey("depths_79_placed");
    public static final RegistryKey<PlacedFeature> DEPTHS_95_PLACED_KEY = registerKey("depths_95_placed");
    public static final RegistryKey<PlacedFeature> DEPTHS_111_PLACED_KEY = registerKey("depths_111_placed");
    public static final RegistryKey<PlacedFeature> DEPTHS_127_PLACED_KEY = registerKey("depths_127_placed");

    public static void boostrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, HELLITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.HELLITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(2,  //place per chunks
                        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(5), YOffset.aboveBottom(35))));

        // Register Depth Features
        register(context, DEPTHS_63_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.LAVA_REMOVER_KEY),
                List.of(HeightRangePlacementModifier.trapezoid(YOffset.fixed(-64), YOffset.fixed(-64))));

        register(context, DEPTHS_79_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.LAVA_REMOVER_KEY),
                List.of(HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80), YOffset.fixed(-80))));

        register(context, DEPTHS_95_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.LAVA_REMOVER_KEY),
                List.of(HeightRangePlacementModifier.trapezoid(YOffset.fixed(-96), YOffset.fixed(-96))));

        register(context, DEPTHS_111_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.LAVA_REMOVER_KEY),
                List.of(HeightRangePlacementModifier.trapezoid(YOffset.fixed(-112), YOffset.fixed(-112))));

        register(context, DEPTHS_127_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.LAVA_REMOVER_KEY),
                List.of(HeightRangePlacementModifier.trapezoid(YOffset.fixed(-122), YOffset.fixed(-122))));
    }


    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Terrium.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
