package com.laktostolerant.terrium.block.custom;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.TransparentBlock;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import org.betterx.bclib.blocks.BaseGlassBlock;
import org.betterx.bclib.client.render.BCLRenderLayer;
import org.betterx.bclib.interfaces.CustomColorProvider;
import org.betterx.bclib.interfaces.RenderLayerProvider;
import org.betterx.bclib.util.MHelper;
import org.betterx.ui.ColorUtil;

public class TestGlowBlock extends TransparentBlock implements RenderLayerProvider, CustomColorProvider {

    public static final Vec3i[] COLORS;

    static {
        COLORS = new Vec3i[]{
                new Vec3i(247, 77, 161),
                new Vec3i(120, 184, 255),
                new Vec3i(120, 255, 168),
                new Vec3i(243, 58, 255)
        };
    }

    public TestGlowBlock(Settings settings) {
        super(settings);
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
    public TestGlowBlock() {
        super(BaseGlassBlock.Settings.copy(Blocks.GLASS).luminance(state -> 15));

    }


    @Override
    public ItemColorProvider getItemProvider() {
        return (stack, tintIndex) -> {
            return ColorUtil.color(COLORS[3].getX(), COLORS[3].getY(), COLORS[3].getZ());
        };
    }

    @Override
    public BCLRenderLayer getRenderLayer() {
        return BCLRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isEnabled(FeatureSet enabledFeatures) {
        return super.isEnabled(enabledFeatures);
    }
}
