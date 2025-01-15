package com.laktostolerant.terrium.world.gen;

import com.laktostolerant.terrium.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.RandomSplitter;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;
import terrablender.api.TerrablenderOverworldBiomeBuilder;

public class CustomSurfaceBuilder extends SurfaceBuilder {

    public CustomSurfaceBuilder(NoiseConfig noiseConfig, BlockState defaultState, int seaLevel, RandomSplitter randomDeriver) {
        super(noiseConfig, defaultState, seaLevel, randomDeriver);

        //SurfaceRuleManager.addSurfaceRules();
    }

    public static MaterialRules.MaterialRule setupCustomSurface()
    {

        return MaterialRules.sequence(
                MaterialRules.condition(
                        MaterialRules.verticalGradient("terrium:pyscoria_gradient", YOffset.fixed(-230), YOffset.fixed(-220)),
                        MaterialRules.block(ModBlocks.PYSCORIA.getDefaultState())
                ),
                MaterialRules.condition(MaterialRules.verticalGradient("terrium:purshale_gradient", YOffset.fixed(-64), YOffset.fixed(-60)),
                        MaterialRules.block(ModBlocks.ROSE_PLANKS.getDefaultState())
                )
        );




                /*
        MaterialRules.MaterialRule purshaleRule = MaterialRules.sequence(
                // purshale
                MaterialRules.condition(
                        MaterialRules.verticalGradient("terrium:purshale_gradient", YOffset.fixed(-64), YOffset.fixed(-60)),
                        MaterialRules.block(ModBlocks.PURSHALE.getDefaultState())
                )
        );

        MaterialRules.MaterialRule pyscoriaRule = MaterialRules.sequence(
                // pyscoria
                MaterialRules.condition(
                        MaterialRules.verticalGradient("terrium:pyscoria_gradient", YOffset.fixed(-230), YOffset.fixed(-220)),
                        MaterialRules.block(ModBlocks.PYSCORIA.getDefaultState())
                )
        );

                 */

/*
        SurfaceRuleManager.addToDefaultSurfaceRulesAtStage(
                SurfaceRuleManager.RuleCategory.OVERWORLD,
                SurfaceRuleManager.RuleStage.BEFORE_BEDROCK,
                2,
                purshaleRule
        );

        SurfaceRuleManager.addToDefaultSurfaceRulesAtStage(
                SurfaceRuleManager.RuleCategory.OVERWORLD,
                SurfaceRuleManager.RuleStage.BEFORE_BEDROCK,
                1,
                pyscoriaRule
        );

 */


    }
}
