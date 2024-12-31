package com.laktostolerant.terrium.mixin;

import com.laktostolerant.terrium.fluid.GoopFluid;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.render.BackgroundRenderer.FogType;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.mojang.blaze3d.systems.RenderSystem;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {
    @Inject(method = "render", at = @At("TAIL"))
    private static void injectGoopFogColor(
            Camera camera,
            float tickDelta,
            ClientWorld world,
            int viewDistance,
            float skyDarkness,
            CallbackInfo ci
    ) {
        if (isInGoopFluid(camera)) {
            RenderSystem.clearColor(0.2F, 0.8F, 0.2F, 0.0F);
        }
    }
    @Inject(method = "applyFog", at = @At("TAIL"))
    private static void injectGoopFogDistance(
            Camera camera,
            BackgroundRenderer.FogType fogType,
            float viewDistance,
            boolean thickFog,
            float tickDelta,
            CallbackInfo ci
    ) {
        if (isInGoopFluid(camera)) {
            float fogStart = -8.0F;
            float fogEnd   = 96.0F;
            RenderSystem.setShaderFogStart(fogStart);
            RenderSystem.setShaderFogEnd(fogEnd);
        }
    }
    private static boolean isInGoopFluid(Camera camera) {
        BlockView blockView = ((CameraAccessor) camera).getArea();
        if (blockView == null) {
            return false;
        }
        Vec3d cameraPos = camera.getPos();
        BlockPos blockPos = BlockPos.ofFloored(cameraPos);
        FluidState fluidState = blockView.getFluidState(blockPos);
        return fluidState.getFluid() instanceof GoopFluid;
    }
}

