package com.laktostolerant.terrium.block.custom;

import com.laktostolerant.terrium.block.ModBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.BlockView;
import org.betterx.bclib.client.render.BCLRenderLayer;
import org.betterx.bclib.interfaces.CustomColorProvider;
import org.betterx.bclib.interfaces.RenderLayerProvider;
import org.betterx.bclib.util.MHelper;
import org.betterx.ui.ColorUtil;
import org.jetbrains.annotations.Nullable;

public class DuskweedPlantBlock extends PlantBlock implements RenderLayerProvider, CustomColorProvider {
    public DuskweedPlantBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends PlantBlock> getCodec() {
        return null;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor == ModBlocks.PURSHALE.getDefaultState() || floor == ModBlocks.WATCHFUL_PURSHALE.getDefaultState();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context){

        return VoxelShapes.cuboid(0.1f, 0, 0.1f, 0.9f, 0.5f, 0.9f);

    }
    public static final Vec3i[] COLORS;

    static {
        COLORS = new Vec3i[]{
                new Vec3i(247, 77, 161),
                new Vec3i(120, 184, 255),
                new Vec3i(120, 255, 168),
                new Vec3i(243, 58, 255)
        };
    }
    @Override
    public BlockColorProvider getProvider() {
        return (state, world, pos, tintIndex) -> {
            if (pos == null) {
                pos = BlockPos.ORIGIN;
            }
            ;

            long i = (long) pos.getX() + (long) pos.getY() + (long) pos.getZ();
            double delta = i * 0.1;
            int index = MHelper.floor(delta);
            int index2 = (index + 1) & 3;
            delta -= index;
            index &= 3;

            Vec3i color1 = COLORS[index];
            Vec3i color2 = COLORS[index2];

            int r = MHelper.floor(MathHelper.lerp(delta, color1.getX(), color2.getX()));
            int g = MHelper.floor(MathHelper.lerp(delta, color1.getY(), color2.getY()));
            int b = MHelper.floor(MathHelper.lerp(delta, color1.getZ(), color2.getZ()));

            return ColorUtil.color(r, g, b);
        };

    }

    @Override
    public ItemColorProvider getItemProvider() {
        return null;
    }

    @Override
    public BCLRenderLayer getRenderLayer() {
        return null;
    }

    @Override
    public BlockState getAppearance(BlockState state, BlockRenderView renderView, BlockPos pos, Direction side, @Nullable BlockState sourceState, @Nullable BlockPos sourcePos) {
        return super.getAppearance(state, renderView, pos, side, sourceState, sourcePos);
    }

    @Override
    public boolean isEnabled(FeatureSet enabledFeatures) {
        return super.isEnabled(enabledFeatures);
    }
}
