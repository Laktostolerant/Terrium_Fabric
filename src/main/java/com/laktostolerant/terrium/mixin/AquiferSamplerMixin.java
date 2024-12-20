package com.laktostolerant.terrium.mixin;


import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.chunk.AquiferSampler;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(AquiferSampler.Impl.class)
public class AquiferSamplerMixin {
    @ModifyVariable(
            method = "apply(Lnet/minecraft/world/gen/densityfunction/DensityFunction$NoisePos;D)Lnet/minecraft/block/BlockState;",
            at = @At("STORE"),
            ordinal = 0
    )
    private BlockState modifyAquiferRanges(BlockState original, DensityFunction.NoisePos pos, double density) {
        int y = pos.blockY();

        if (y >= -120 && y <= -60 && original != null && original.isOf(Blocks.LAVA)) {
            return Blocks.NOTE_BLOCK.getDefaultState();
        } else if (y >= -190 && y < -120 && original != null && original.isOf(Blocks.LAVA)) {
            return Blocks.WARPED_FUNGUS.getDefaultState();
        }
        else if (y >= -250 && y < -190 && original != null && original.isOf(Blocks.LAVA)){
            return  Blocks.DEEPSLATE.getDefaultState();
        }
        return original;
    }
}
