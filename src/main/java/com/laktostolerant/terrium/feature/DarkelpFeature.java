package com.laktostolerant.terrium.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.KelpBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class DarkelpFeature extends Feature<DefaultFeatureConfig> {
    private final BlockState darkelpState; // Base block state for DARKELP
    private final int maxHeight; // Maximum height for DARKELP

    public DarkelpFeature(Codec<DefaultFeatureConfig> codec, BlockState darkelpState, int maxHeight) {
        super(codec);
        this.darkelpState = darkelpState;
        this.maxHeight = maxHeight;
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();

        int height = random.nextInt(maxHeight) + 1; // Random height from 1 to maxHeight
        int placedBlocks = 0;

        // Ensure the block below is solid and the origin is not underwater
        if (!world.getBlockState(origin.down()).isAir() && !world.getBlockState(origin).getFluidState().isEmpty()) {
            return false; // Abort placement
        }

        // Place blocks upward to the determined height
        for (int i = 0; i < height; i++) {
            BlockPos currentPos = origin.up(i);

            // Check if the current position is suitable for placement
            if (world.getBlockState(currentPos).isAir() && world.getBlockState(currentPos.down()).isSolidBlock(world, currentPos.down())) {
                world.setBlockState(currentPos, darkelpState, 3);
                placedBlocks++;
            } else {
                break; // Stop placement if obstructed
            }
        }

        return placedBlocks > 0; // Return true if at least one block was placed
    }
}
