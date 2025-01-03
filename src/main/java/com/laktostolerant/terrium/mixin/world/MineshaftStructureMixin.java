package com.laktostolerant.terrium.mixin.world;


import net.minecraft.world.gen.structure.MineshaftStructure;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MineshaftStructure.class)
public class MineshaftStructureMixin {

    @Redirect(
            method = "addPieces(Lnet/minecraft/structure/StructurePiecesCollector;Lnet/minecraft/world/gen/structure/Structure$Context;)I",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/gen/chunk/ChunkGenerator;getMinimumY()I"
            )
    )
    private int redirectGetMinimumY(ChunkGenerator generator) {
        int original = generator.getMinimumY();
        return Math.max(original, -60);
    }
}