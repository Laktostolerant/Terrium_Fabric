package com.laktostolerant.terrium.mixin.render;
/*
import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.util.DuskweedBlockLightProvider;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.LightType;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.chunk.light.LightingProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(WorldChunk.class)
public abstract class WorldChunkMixin {


    @Inject(method = "setLoadedToWorld(Z)V", at = @At("TAIL"))
    private void onSetLoadedToWorld(boolean loadedToWorld, CallbackInfo ci) {
        if (!loadedToWorld) {
            return;
        }

        WorldChunk self = (WorldChunk) (Object) this;
        World world = self.getWorld();
        LightingProvider lightingProvider = world.getLightingProvider();
        if (!(lightingProvider.get(LightType.BLOCK) instanceof DuskweedBlockLightProvider duskweedProvider)) {
            return; // Not our custom provider, do nothing
        }

        // Iterate all chunk sections
        ChunkSection[] sections = self.getSectionArray();
        for (int sectionIndex = 0; sectionIndex < sections.length; sectionIndex++) {
            ChunkSection section = sections[sectionIndex];
            if (section == null || section.isEmpty()) {
                continue;
            }

            // For local x,y,z in [0..15], check if Duskweed
            for (int localX = 0; localX < 16; localX++) {
                for (int localY = 0; localY < 16; localY++) {
                    for (int localZ = 0; localZ < 16; localZ++) {
                        BlockState state = section.getBlockState(localX, localY, localZ);
                        if (state.isOf(ModBlocks.DUSKWEED)) {
                            // Convert from local coords -> absolute world coords
                            int absX = (self.getPos().x << 4) + localX;
                            int absY = (sectionIndex << 4) + localY;
                            int absZ = (self.getPos().z << 4) + localZ;

                            // Call our custom logic to ensure it's recognized
                            duskweedProvider.checkBlock(BlockPos.fromLong(BlockPos.asLong(absX, absY, absZ)));
                        }
                    }
                }
            }
        }
    }
}
*/