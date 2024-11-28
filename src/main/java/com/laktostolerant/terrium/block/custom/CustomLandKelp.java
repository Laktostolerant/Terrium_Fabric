package com.laktostolerant.terrium.block.custom;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.block.ModBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class CustomLandKelp extends AbstractPlantStemBlock {
    public Block PLANT_BLOCK;

    public CustomLandKelp(Settings settings, Direction growthDirection, VoxelShape outlineShape, boolean tickWater, double growthChance, Block plantBlock) {
        super(settings, growthDirection, outlineShape, tickWater, growthChance);
        PLANT_BLOCK = plantBlock;


    }


    @Override
    protected Block getPlant() {
        return PLANT_BLOCK;
    }

    @Override
    protected MapCodec<? extends AbstractPlantStemBlock> getCodec() {
        return null;
    }

    @Override
    protected int getGrowthLength(Random random) {
        return 0;
    }

    @Override
    protected boolean chooseStemState(BlockState state) {
        return false;
    }
}
