package com.laktostolerant.terrium.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.Blocks;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.gen.chunk.AquiferSampler;
import net.minecraft.world.gen.noise.NoiseConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Supplier;

@Mixin(NoiseChunkGenerator.class)
public abstract class NoiseChunkGeneratorMixin {
    @Shadow protected abstract AquiferSampler.FluidLevelSampler createFluidLevelSampler(ChunkGeneratorSettings settings);

    @Shadow @Final private Supplier<AquiferSampler.FluidLevelSampler> fluidLevelSampler;

    @Shadow public abstract int getHeight(int x, int z, Heightmap.Type heightmap, HeightLimitView world, NoiseConfig noiseConfig);

    /**
     * Overwrites the method to create a custom fluid level sampler where lava fills air starting
     * 5 blocks above the bedrock level instead of sea level - 117.
     *
     * @author Laktostolerant
     * @reason Customize fluid generation behavior for lava placement.
     */

    @ModifyArg(
            method = "createFluidLevelSampler(Lnet/minecraft/world/gen/chunk/ChunkGeneratorSettings;)Lnet/minecraft/world/gen/chunk/AquiferSampler$FluidLevelSampler;",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Math;min(II)I"
            ),
            index = 0 // Modify the first argument to Math.min
    )
    private static int omitLavaLevelLogic(int original) {
        // Replace the hardcoded -54 (lava level) with a high value to effectively omit it
        return Integer.MIN_VALUE; // This ensures the lava threshold is never reached
    }


    /**
    @Overwrite
    private static AquiferSampler.FluidLevelSampler createFluidLevelSampler(ChunkGeneratorSettings settings) {
        int bedrockLavaStart = DimensionType.MIN_HEIGHT + 5; // 5 blocks above bedrock

        // Lava will fill below this level
        AquiferSampler.FluidLevel lavaLevel = new AquiferSampler.FluidLevel(
                bedrockLavaStart,
                Blocks.LAVA.getDefaultState()
        );

        // Water fills below sea level
        AquiferSampler.FluidLevel waterLevel = new AquiferSampler.FluidLevel(
                settings.seaLevel(),
                Blocks.WATER.getDefaultState()
        );

        // Default block (stone or terrain material) above sea level
        AquiferSampler.FluidLevel defaultLevel = new AquiferSampler.FluidLevel(
                settings.seaLevel(),
                settings.defaultBlock()
        );

        // Return the appropriate fluid level sampler
        return (x, y, z) -> y < bedrockLavaStart ? lavaLevel : (y < settings.seaLevel() ? waterLevel : defaultLevel);
    }
    **/
}
