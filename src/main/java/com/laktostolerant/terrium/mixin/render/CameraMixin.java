package com.laktostolerant.terrium.mixin.render;

import com.laktostolerant.terrium.mixin.accessors.CameraAccessor;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.render.Camera;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Camera.class)
public abstract class CameraMixin {

    @Inject(
            method = "getSubmersionType",
            at = @At("TAIL"),
            cancellable = true
    )
    private void injectGoopSubmersionType(CallbackInfoReturnable<CameraSubmersionType> cir) {
        if (cir.getReturnValue() != CameraSubmersionType.NONE) {
            return;
        }
        Camera self = (Camera) (Object) this;

        BlockView area = ((CameraAccessor) self).getArea();
        if (area == null) {
            return;
        }
        Vec3d cameraPos = self.getPos();
        BlockPos blockPos = BlockPos.ofFloored(cameraPos);
        FluidState fluidState = area.getFluidState(blockPos);
    }
}