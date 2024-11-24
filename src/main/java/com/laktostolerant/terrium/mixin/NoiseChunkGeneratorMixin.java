package com.laktostolerant.terrium.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.gen.chunk.AquiferSampler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(NoiseChunkGenerator.class)
public abstract class NoiseChunkGeneratorMixin {

    /**
     * Overwrites the method to create a custom fluid level sampler where lava fills air starting
     * 5 blocks above the bedrock level instead of sea level - 117.
     *
     * @author [Your Name]
     * @reason Customize fluid generation behavior for lava placement.
     */
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
}
