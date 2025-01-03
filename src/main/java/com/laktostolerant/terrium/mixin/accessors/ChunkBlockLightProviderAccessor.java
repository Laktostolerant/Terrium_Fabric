package com.laktostolerant.terrium.mixin.accessors;


import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.light.ChunkBlockLightProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ChunkBlockLightProvider.class)
public interface ChunkBlockLightProviderAccessor {
    @Invoker("getLightSourceLuminance")
    int invokeGetLightSourceLuminance(long blockPos, BlockState blockState);
    @Accessor("mutablePos")
    BlockPos.Mutable getMutablePos();
}
