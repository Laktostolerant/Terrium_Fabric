/*package com.laktostolerant.terrium.mixin.render;

import com.laktostolerant.terrium.util.DuskweedBlockLightProvider;
import com.laktostolerant.terrium.util.DuskweedLightStorage;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.light.ChunkLightingView;
import net.minecraft.world.chunk.light.LightingProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {

    @Inject(
            method = "getLightmapCoordinates(Lnet/minecraft/world/BlockRenderView;Lnet/minecraft/util/math/BlockPos;)I",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void injectDuskweedColor(
            BlockRenderView blockRenderView,
            BlockPos pos,
            CallbackInfoReturnable<Integer> cir
    ) {
        // We only care if blockRenderView is an actual World instance
        if (!(blockRenderView instanceof World)) {
            return;
        }

        World world = (World) blockRenderView;
        // Grab the lighting provider from the world
        LightingProvider lightingProvider = world.getLightingProvider();
        if (lightingProvider == null) {
            return;
        }

        // We want the block light provider
        ChunkLightingView blockProvider = lightingProvider.get(LightType.BLOCK);
        if (!(blockProvider instanceof DuskweedBlockLightProvider duskweedProvider)) {
            // If it's not our custom provider, do nothing
            return;
        }

        // Our custom storage with the "isDuskweedLit" flag
        DuskweedLightStorage storage = (DuskweedLightStorage) duskweedProvider.lightStorage;

        long packedPos = pos.asLong();
        // Check if this block was flagged "duskweed-lit"
        if (storage.isDuskweedLit(packedPos)) {
            // If so, override the (sky, block) to, e.g., (0,8)
            int sky = 0;
            int block = 8;
            int packed = (sky << 20) | (block << 4);

            cir.setReturnValue(packed);
            cir.cancel();
        }
    }

}

*/