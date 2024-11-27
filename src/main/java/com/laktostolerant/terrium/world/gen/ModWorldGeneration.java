package com.laktostolerant.terrium.world.gen;

import com.laktostolerant.terrium.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModWorldGeneration {
    public static void generateModWorldGen() {
        ModOreGeneration.generateOres();
    }

    public static void registerSurfaceRules() {
        // Define the vertical gradient condition for Y-levels below -60
        MaterialRules.MaterialCondition belowNegative60 = MaterialRules.verticalGradient(
                "terrium_purshale_gradient", // Unique name for the condition
                YOffset.fixed(-60), // Y-level where the condition starts being true
                YOffset.fixed(-255) // Maximum depth for the condition
        );

        // Define the rule to replace deepslate with purshale
        MaterialRules.MaterialRule purshaleRule = MaterialRules.condition(
                belowNegative60,
                MaterialRules.block(Blocks.PURPLE_TERRACOTTA.getDefaultState()) // Replace with your custom block
        );

        // Inject the custom rule into the overworld surface rules
        SurfaceRuleManager.addToDefaultSurfaceRulesAtStage(
                SurfaceRuleManager.RuleCategory.OVERWORLD,
                SurfaceRuleManager.RuleStage.AFTER_BEDROCK, // Apply the rule after bedrock generation
                10, // Priority (higher numbers are processed earlier)
                purshaleRule
        );
    }
}
