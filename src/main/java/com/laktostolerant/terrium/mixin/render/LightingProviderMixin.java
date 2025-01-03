package com.laktostolerant.terrium.mixin.render;

import com.laktostolerant.terrium.util.DuskweedBlockLightProvider;
import net.minecraft.world.chunk.ChunkProvider;
import net.minecraft.world.chunk.light.ChunkBlockLightProvider;
import net.minecraft.world.chunk.light.LightingProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LightingProvider.class)
public abstract class LightingProviderMixin {

    /**
     * Intercept the 'new' call that creates ChunkBlockLightProvider
     * and replace it with our DuskweedBlockLightProvider.
     */
    @Redirect(
            method = "<init>(Lnet/minecraft/world/chunk/ChunkProvider;ZZ)V",
            at = @At(
                    value = "NEW",
                    target = "net/minecraft/world/chunk/light/ChunkBlockLightProvider"
            )
    )
    private ChunkBlockLightProvider redirectBlockLightProvider(ChunkProvider chunkProvider) {
        return new DuskweedBlockLightProvider(chunkProvider);
    }
}