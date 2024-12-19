package com.laktostolerant.terrium.mixin;


import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.chunk.AquiferSampler;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AquiferSampler.Impl.class)
public class AquiferSamplerMixin {

    @ModifyVariable(method = "apply(Lnet/minecraft/world/gen/densityfunction/DensityFunction$NoisePos;D)Lnet/minecraft/block/BlockState;",
            at = @At("STORE"),
            ordinal = 0)
    private BlockState forceWaterBelowYNeg60(BlockState original, DensityFunction.NoisePos pos, double density) {
        if (pos.blockY() < -80 && original != null && original.isOf(Blocks.LAVA)) {
            return Blocks.WATER.getDefaultState();
        }
        return original;
    }
    // it should actually be replacing aquifers with purely water but now it just nukes them
}
