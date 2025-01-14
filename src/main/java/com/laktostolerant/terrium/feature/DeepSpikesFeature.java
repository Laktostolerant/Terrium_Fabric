package com.laktostolerant.terrium.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class DeepSpikesFeature extends Feature<DeepSpikesFeatureConfig> {


    public DeepSpikesFeature(Codec<DeepSpikesFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DeepSpikesFeatureConfig> context) {
        WorldAccess world = context.getWorld();
        Random random = context.getRandom();
        BlockPos origin = context.getOrigin();
        if (origin.getY() > -270) {
            return false;
        }
        BlockPos groundPos = findSurface(world, origin);
        if (groundPos == null) {
            return false;
        }
        int radius = 4 + random.nextInt(3);
        buildGlassCone(world, groundPos, radius);

        return true;
    }

    private BlockPos findSurface(WorldAccess world, BlockPos start) {
        int surfaceY = world.getTopY(Heightmap.Type.WORLD_SURFACE, start.getX(), start.getZ());
        if (surfaceY < world.getBottomY()) {
            return null;
        }
        if (surfaceY > -270) {
            return null;
        }
        return new BlockPos(start.getX(), surfaceY, start.getZ());
    }
    private void buildGlassCone(WorldAccess world, BlockPos groundPos, int baseRadius) {
        int currentRadius = baseRadius;
        int height = 0;
        while (currentRadius > 0) {
            fillCircle(world, groundPos.up(height), currentRadius);
            currentRadius--;
            height++;
        }
    }

    private void fillCircle(WorldAccess world, BlockPos centerPos, int radius) {
        int rSq = radius * radius;
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                if (dx * dx + dz * dz <= rSq) {
                    BlockPos pos = centerPos.add(dx, 0, dz);
                    if (world.isAir(pos)) {
                        world.setBlockState(pos, Blocks.GLASS.getDefaultState(), 3);
                    }
                }
            }
        }
    }
}