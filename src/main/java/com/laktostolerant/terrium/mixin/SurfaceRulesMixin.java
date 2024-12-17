package com.laktostolerant.terrium.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.chunk.AquiferSampler;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(NoiseChunkGenerator.class)
public class SurfaceRulesMixin {
    @Inject(method = "createFluidLevelSampler", at = @At("RETURN"), cancellable = true)
    private static void modifyFluidLevelSampler(ChunkGeneratorSettings settings, CallbackInfoReturnable<AquiferSampler.FluidLevelSampler> cir) {

        AquiferSampler.FluidLevel customFluidLevelLava = new AquiferSampler.FluidLevel(-128, Blocks.WATER.getDefaultState());
        AquiferSampler.FluidLevel customFluidLevelWater = new AquiferSampler.FluidLevel(settings.seaLevel(), settings.defaultFluid());

        AquiferSampler.FluidLevelSampler modifiedSampler = (x, y, z) -> {
            if (y < -128) {
                return customFluidLevelLava;
            } else {
                return customFluidLevelWater;
            }
        };

        // Return the modified sampler
        cir.setReturnValue(modifiedSampler);
    }
}