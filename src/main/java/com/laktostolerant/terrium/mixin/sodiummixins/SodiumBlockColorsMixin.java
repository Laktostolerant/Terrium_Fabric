package com.laktostolerant.terrium.mixin.sodiummixins;

import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.BlockRenderer;
import net.caffeinemc.mods.sodium.client.render.frapi.mesh.MutableQuadViewImpl;
import net.caffeinemc.mods.sodium.client.render.chunk.terrain.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BlockRenderer.class, remap = false)
public abstract class SodiumBlockColorsMixin {
    @Inject(
            method = "bufferQuad(Lnet/caffeinemc/mods/sodium/client/render/frapi/mesh/MutableQuadViewImpl;[FLnet/caffeinemc/mods/sodium/client/render/chunk/terrain/material/Material;)V",
            at = @At("HEAD")
    )
    private void onBufferQuadHead(MutableQuadViewImpl quad, float[] brightnesses, Material material, CallbackInfo ci) {
        // no need to do anything yet
    }
}
