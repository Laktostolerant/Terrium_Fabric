package com.laktostolerant.terrium.mixin;

import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(MultiNoiseUtil.ParameterRange.class)
public class MultiNoiseUtilParameterRangeMixin {
    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = -2.0F))
    private static float modifyMinRange(float original) {
        return -3F;
    }
    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 2.0F))
    private static float modifyMaxRange(float original) {
        return 3F;
    }
}
