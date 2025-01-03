package com.laktostolerant.terrium.mixin.render;

import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.texture.NativeImage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.laktostolerant.terrium.mixin.accessors.LightmapTextureManagerAccessor; // An accessor for private fields

@Mixin(LightmapTextureManager.class)
public abstract class LightmapTextureManagerMixin {

    @Inject(method = "update", at = @At("RETURN"))
    private void injectDuskweedColor(float delta, CallbackInfo ci) {
        LightmapTextureManager self = (LightmapTextureManager) (Object) this;
        NativeImage image = ((LightmapTextureManagerAccessor) self).getImage();
        int x = 8;
        int y = 0;
        // ARGB = 0xAARRGGBB
        int purple = 0xFFFF00FF;
        image.setColor(x, y, purple);
    }
}
