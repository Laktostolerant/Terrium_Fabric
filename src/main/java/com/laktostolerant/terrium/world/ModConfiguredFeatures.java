package com.laktostolerant.terrium.world;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.feature.DarkelpFeatureConfig;
import com.laktostolerant.terrium.feature.DeepSpikesFeature;
import com.laktostolerant.terrium.feature.DeepSpikesFeatureConfig;
import com.laktostolerant.terrium.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import net.minecraft.util.collection.DataPool;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.minecraft.world.gen.trunk.MegaJungleTrunkPlacer;

import java.util.List;

import javax.xml.catalog.CatalogFeatures.Feature;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> HELLITE_ORE_KEY = registerKey("hellite_ore");

    public static final RegistryKey<ConfiguredFeature<?, ?>> ABYSS_PLANTS_GROUP = registerKey("abyss_plants_group");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ABYSS_PLANTS_KEY = registerKey("abyss_plants");

    public static final RegistryKey<ConfiguredFeature<?, ?>> DARKELP_CONFIGURED_KEY = registerKey("darkelp_configured_key");

    public static final RegistryKey<ConfiguredFeature<?, ?>> ROSE_TREE_KEY = registerKey("rose");

    public static final RegistryKey<ConfiguredFeature<?, ?>> DEEP_JUNGLE_PLANTS_GROUP = registerKey("deep_jungle_plants_group");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEEP_JUNGLE_PLANTS_KEY = registerKey("deep_jungle_plants_key");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEEP_SPIKES_KEY = registerKey("deep_spikes_key");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEEP_JUNGLE_TREES = registerKey("deep_jungle_trees");

    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        RuleTest purshaleOreInDeepslate = new TagMatchRuleTest(ModTags.Blocks.PURSHALE_ORE_REPLACEABLES);
        List<OreFeatureConfig.Target> helliteOres = List.of(OreFeatureConfig.createTarget(purshaleOreInDeepslate, ModBlocks.HELLITE_ORE.getDefaultState()));

        register(
                context,
                DEEP_SPIKES_KEY,
                ModCustomFeatures.DEEP_SPIKES_FEATURE,
                new DeepSpikesFeatureConfig(4, 8, -270)
        );
        register(
                context,
                DARKELP_CONFIGURED_KEY,
                ModCustomFeatures.DARKELP_FEATURE,
                new DarkelpFeatureConfig(5, 5, 8)
        );

        register(
                context,
                HELLITE_ORE_KEY,
                Feature.ORE,
                new OreFeatureConfig(helliteOres, 4)
        );

        register(
                context,
                ABYSS_PLANTS_GROUP,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(
                        new WeightedBlockStateProvider(
                                DataPool.<BlockState>builder() // Explicitly specify BlockState
                                        .add(ModBlocks.DUSKWEED.getDefaultState(), 40)
                                        .build()
                    )
            )
        );

         register(
                context,
                ABYSS_PLANTS_KEY,
                Feature.VEGETATION_PATCH,
                new VegetationPatchFeatureConfig(
                        ModTags.Blocks.ABYSS_GROWABLES,
                        BlockStateProvider.of(ModBlocks.PURSHALE),
                        PlacedFeatures.createEntry(registryEntryLookup.getOrThrow(ABYSS_PLANTS_GROUP),
                                new PlacementModifier[0]), VerticalSurfaceType.FLOOR,
                        ConstantIntProvider.create(1),
                        0.0F,
                        10,
                        0.5F,
                        UniformIntProvider.create(6, 9),
                        0.4F
                )
        );

        register(context, ROSE_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.ROSE_LOG),
                new MegaJungleTrunkPlacer(10, 10, 10),

                BlockStateProvider.of(ModBlocks.ROSE_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(1), 3),

                new TwoLayersFeatureSize(1, 0, 2)).build()
        );

        register(context, DEEP_JUNGLE_TREES, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.JUNGLE_LOG),
                new ForkingTrunkPlacer(4, 2, 2),

                BlockStateProvider.of(Blocks.JUNGLE_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(2), 2),
                new TwoLayersFeatureSize(3, 0, 2)).build()
        );

        register(
                context,
                DEEP_JUNGLE_PLANTS_GROUP,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(
                        new WeightedBlockStateProvider(
                                DataPool.<BlockState>builder() // Explicitly specify BlockState
                                        .add(Blocks.SHORT_GRASS.getDefaultState(), 40)
                                        .add(Blocks.LILY_OF_THE_VALLEY.getDefaultState(), 10)
                                        .build()
                        )
                )
        );

        register(
                context,
                DEEP_JUNGLE_PLANTS_KEY,
                Feature.VEGETATION_PATCH,
                new VegetationPatchFeatureConfig(
                        BlockTags.DIRT,
                        BlockStateProvider.of(Blocks.GRASS_BLOCK),
                        PlacedFeatures.createEntry(registryEntryLookup.getOrThrow(ABYSS_PLANTS_GROUP),
                                new PlacementModifier[0]), VerticalSurfaceType.FLOOR,
                        ConstantIntProvider.create(1),
                        0.0F,
                        10,
                        0.5F,
                        UniformIntProvider.create(6, 9),
                        0.4F
                )
        );
    }


    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Terrium.MOD_ID, name));
    }


    public static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                  RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
