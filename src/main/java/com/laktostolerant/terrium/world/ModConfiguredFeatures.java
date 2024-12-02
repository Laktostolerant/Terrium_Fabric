package com.laktostolerant.terrium.world;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.util.ModTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> HELLITE_ORE_KEY = registerKey("hellite_ore");

    public static final RegistryKey<ConfiguredFeature<?, ?>> ABYSS_PLANTS_KEY = registerKey("abyss_plants");

    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest purshaleOreInDeepslate = new TagMatchRuleTest(ModTags.Blocks.PURSHALE_ORE_REPLACEABLES);
        List<OreFeatureConfig.Target> helliteOres =
                List.of(OreFeatureConfig.createTarget(purshaleOreInDeepslate, ModBlocks.HELLITE_ORE.getDefaultState()));


        register(context, HELLITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(helliteOres, 4));


        register(
                context,
                ABYSS_PLANTS_KEY,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(
                        new WeightedBlockStateProvider(
                                DataPool.<BlockState>builder()
                                        .add(ModBlocks.DUSKWEED.getDefaultState(), 25)
                                        .add(ModBlocks.DARKELP.getDefaultState(), 7)
                                        .build()
                        )
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
