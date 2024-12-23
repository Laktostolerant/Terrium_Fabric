package com.laktostolerant.terrium.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PlantBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class SnowyGrassBlock extends PlantBlock {
    public SnowyGrassBlock(Settings settings) {
        super(settings);
        Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);
    }

    @Override
    protected MapCodec<? extends PlantBlock> getCodec() {
        return null;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor == Blocks.SNOW_BLOCK.getDefaultState();
    }
}
