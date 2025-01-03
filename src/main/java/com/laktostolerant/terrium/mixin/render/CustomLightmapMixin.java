package com.laktostolerant.terrium.mixin.render;

import com.laktostolerant.terrium.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldRenderer.class)
public abstract class CustomLightmapMixin {

    @Inject(
            method = "getLightmapCoordinates(Lnet/minecraft/world/BlockRenderView;Lnet/minecraft/util/math/BlockPos;)I",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void onGetLightmapCoordinatesBlockPos(
            BlockRenderView world, BlockPos pos,
            CallbackInfoReturnable<Integer> cir
    ) {
        BlockState state = world.getBlockState(pos);
        if (ModBlocks.isSpecialBlock(state.getBlock())) {
            int sky = 8;
            int block = 11;
            int packed = (sky << 20) | (block << 4);
            cir.setReturnValue(packed);
            cir.cancel();
        }
    }
    @Inject(
            method = "getLightmapCoordinates(Lnet/minecraft/world/BlockRenderView;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;)I",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void onGetLightmapCoordinatesBlockState(
            BlockRenderView world, BlockState state, BlockPos pos,
            CallbackInfoReturnable<Integer> cir
    ) {
        if (ModBlocks.isSpecialBlock(state.getBlock())) {
            int sky = 8;
            int block = 11;
            int packed = (sky << 20) | (block << 4);

            cir.setReturnValue(packed);
            cir.cancel();
        }
    }
}