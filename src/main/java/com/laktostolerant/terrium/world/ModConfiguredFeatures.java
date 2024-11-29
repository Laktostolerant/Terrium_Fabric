package com.laktostolerant.terrium.world;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.util.ModTags;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> HELLITE_ORE_KEY = registerKey("hellite_ore");

    public static final RegistryKey<ConfiguredFeature<?, ?>> LAVA_REMOVER_KEY = registerKey("lava_remover");


    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest purshaleOreInDeepslate = new TagMatchRuleTest(ModTags.Blocks.PURSHALE_ORE_REPLACEABLES);
        List<OreFeatureConfig.Target> helliteOres =
                List.of(OreFeatureConfig.createTarget(purshaleOreInDeepslate, ModBlocks.HELLITE_ORE.getDefaultState()));


        register(context, HELLITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(helliteOres, 4));

        /**
        register(context, LAVA_REMOVER_KEY, Feature.GEODE, createLavaRemoverConfig());
         **/
    }




    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Terrium.MOD_ID, name));
    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                  RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    // Create Geode Configuration
    private static GeodeFeatureConfig createLavaRemoverConfig() {
        return new GeodeFeatureConfig(
                new GeodeLayerConfig(
                        BlockStateProvider.of(Blocks.AIR), // Filling block
                        BlockStateProvider.of(Blocks.AIR), // Inner layer block
                        BlockStateProvider.of(Blocks.AIR), // Alternate inner layer block
                        BlockStateProvider.of(Blocks.AIR), // Middle layer block
                        BlockStateProvider.of(Blocks.AIR), // Outer layer block
                        List.of(Blocks.AIR.getDefaultState()), // Inner blocks
                        TagKey.of(RegistryKeys.BLOCK, Identifier.of("terrium", "all_but_lava")), // Cannot replace
                        TagKey.of(RegistryKeys.BLOCK, Identifier.of("minecraft", "geode_invalid_blocks")) // Invalid blocks
                ),
                new GeodeLayerThicknessConfig(
                        25, 25, 25, 25 // Layer thickness values
                ),
                new GeodeCrackConfig(0, 0.0, 0), // Crack settings
                0.35, // Use potential placements chance
                0.083, // Use alternate layer 0 chance
                true, // Placements require layer 0 alternate
                UniformIntProvider.create(1, 1), // Outer wall distance
                UniformIntProvider.create(5, 5), // Distribution points
                UniformIntProvider.create(0, 0), // Point offset
                15, // Max distribution points
                0,  // Min point offset
                0.0, // Noise multiplier
                15   // Max generation offset
        );
    }
}
