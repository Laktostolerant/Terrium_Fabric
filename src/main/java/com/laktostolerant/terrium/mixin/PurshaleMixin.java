package com.laktostolerant.terrium.mixin;

import com.laktostolerant.terrium.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import terrablender.worldgen.TBSurfaceRuleData;

@Mixin(TBSurfaceRuleData.class)
public class PurshaleMixin {

    @Inject(method = "overworldLike", at = @At("RETURN"), cancellable = true)
    private static void addPurshaleLayer(boolean checkAbovePreliminarySurface, boolean bedrockRoof, boolean bedrockFloor, CallbackInfoReturnable<MaterialRules.MaterialRule> cir) {
        MaterialRules.MaterialRule existingRules = cir.getReturnValue();

        // Condition to exclude bedrock
        MaterialRules.MaterialCondition noBedrock = MaterialRules.not(
                MaterialRules.verticalGradient("bedrock", YOffset.getBottom(), YOffset.aboveBottom(5)) // Bottom 5 layers
        );

        // Fully purshale layer below Y -65, excluding bedrock
        MaterialRules.MaterialRule fullyPurshale = MaterialRules.condition(
                MaterialRules.verticalGradient("purshale_full", YOffset.fixed(-65), YOffset.getBottom()),
                MaterialRules.condition(noBedrock, MaterialRules.block(ModBlocks.PURSHALE.getDefaultState())) // Exclude bedrock
        );

        // Choppy mixing strictly between Y -60 and Y -65, excluding bedrock
        MaterialRules.MaterialRule choppyMix = MaterialRules.condition(
                MaterialRules.verticalGradient("purshale_transition", YOffset.fixed(-60), YOffset.fixed(-65)), // Between -60 and -65
                MaterialRules.condition(
                        noBedrock, // Exclude bedrock
                        MaterialRules.condition(
                                MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -0.1, 0.1), // Random choppy effect
                                MaterialRules.block(ModBlocks.PURSHALE.getDefaultState()) // Purshale block
                        )
                )
        );

        // Combine the rules
        MaterialRules.MaterialRule combinedRules = MaterialRules.sequence(fullyPurshale, choppyMix, existingRules);

        cir.setReturnValue(combinedRules);
    }



}
