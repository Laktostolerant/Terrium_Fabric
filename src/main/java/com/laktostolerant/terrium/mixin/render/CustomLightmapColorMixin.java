package com.laktostolerant.terrium.mixin.render;

import com.laktostolerant.terrium.mixin.accessors.LightmapTextureManagerAccessor;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.texture.NativeImage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightmapTextureManager.class)
public abstract class CustomLightmapColorMixin {

    @Inject(
            method = "update",
            at = @At("RETURN")
    )
    private void onUpdate(float delta, CallbackInfo ci) {
        LightmapTextureManager self = (LightmapTextureManager) (Object) this;

        NativeImage image = ((LightmapTextureManagerAccessor)self).getImage();
        int specialColor = 0xFFFF0000;

        // x = blockLight=8, y=skyLight=15
        int x = 11;
        int y = 8;

        image.setColor(x, y, specialColor);
        // self.texture.upload();
    }
}
