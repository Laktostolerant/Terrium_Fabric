package com.laktostolerant.terrium.block.custom;

import com.laktostolerant.terrium.block.ModBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractPlantBlock;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.KelpPlantBlock;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;

public class CustomLandKelpBlock extends AbstractPlantBlock {

    public CustomLandKelpBlock(Settings settings, Direction direction, VoxelShape voxelShape, boolean bl) {
        super(settings, direction, voxelShape, bl);
    }


    @Override
    protected MapCodec<CustomLandKelpBlock> getCodec() {
        return null;
    }

    @Override
    protected AbstractPlantStemBlock getStem() {
        return (AbstractPlantStemBlock) ModBlocks.DARKELP;
    }
}
