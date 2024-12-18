package com.laktostolerant.terrium.world;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import net.minecraft.util.collection.DataPool;
import net.minecraft.util.collection.Weighted;

import java.util.List;

public class ModConfiguredFeatures {


    public static final RegistryKey<ConfiguredFeature<?, ?>> HELLITE_ORE_KEY = registerKey("hellite_ore");

    public static final RegistryKey<ConfiguredFeature<?, ?>> ABYSS_PLANTS_GROUP = registerKey("abyss_plants_group");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ABYSS_PLANTS_KEY = registerKey("abyss_plants");

    public static final RegistryKey<ConfiguredFeature<?, ?>> ABYSS_ROOTS_GROUP = registerKey("abyss_roots_group");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ABYSS_ROOTS_KEY = registerKey("abyss_roots");

    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        RuleTest purshaleOreInDeepslate = new TagMatchRuleTest(ModTags.Blocks.PURSHALE_ORE_REPLACEABLES);
        List<OreFeatureConfig.Target> helliteOres =
                List.of(OreFeatureConfig.createTarget(purshaleOreInDeepslate, ModBlocks.HELLITE_ORE.getDefaultState()));


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
                                        .add(ModBlocks.DARKELP_PLANT.getDefaultState(), 1)
                                        .add(ModBlocks.DUSKWEED.getDefaultState(), 25)
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
                        0.4F)
        );


        register(
                context,
                ABYSS_ROOTS_GROUP,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(
                        new WeightedBlockStateProvider(
                                DataPool.<BlockState>builder() // Explicitly specify BlockState
                                        .add(ModBlocks.MURKROOT.getDefaultState(), 1)
                                        .build()
                        )
                )
        );

        register(
                context,
                ABYSS_ROOTS_KEY,
                Feature.VEGETATION_PATCH,
                new VegetationPatchFeatureConfig(
                        ModTags.Blocks.ABYSS_GROWABLES,
                        BlockStateProvider.of(ModBlocks.PURSHALE),
                        PlacedFeatures.createEntry(registryEntryLookup.getOrThrow(ABYSS_PLANTS_GROUP),
                                new PlacementModifier[0]), VerticalSurfaceType.CEILING,
                        ConstantIntProvider.create(1),
                        0.0F,
                        20,
                        0.2F,
                        UniformIntProvider.create(2, 4),
                        0.25F)
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
