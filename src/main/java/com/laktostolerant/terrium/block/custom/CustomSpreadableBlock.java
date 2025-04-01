package com.laktostolerant.terrium.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;

public class CustomSpreadableBlock extends SpreadableBlock {
    public static final MapCodec<CustomSpreadableBlock> CODEC = createCodec(CustomSpreadableBlock::new);

    public CustomSpreadableBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends SpreadableBlock> getCodec() {
        return CODEC;
    }


    private static boolean canSpread(BlockState state, WorldView world, BlockPos pos) {
        // Check if the Y altitude is above 400
        return pos.getY() > 400;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getLightLevel(pos.up()) >= 3) {
            BlockState blockState = this.getDefaultState();

            for (int i = 0; i < 4; ++i) {
                BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);

                // Check if the target block is an amethyst block and the position is above altitude 400
                if (world.getBlockState(blockPos).isOf(Blocks.AMETHYST_BLOCK) && canSpread(blockState, world, blockPos)) {
                    world.setBlockState(blockPos, blockState);
                }
            }
        }
    }
}
