package com.laktostolerant.terrium.mixin;

import net.minecraft.world.gen.structure.StrongholdStructure;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StrongholdStructure.class)
public class StrongholdStructureMixin {

    @Redirect(
            method = "addPieces(Lnet/minecraft/structure/StructurePiecesCollector;Lnet/minecraft/world/gen/structure/Structure$Context;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/gen/chunk/ChunkGenerator;getMinimumY()I"
            )
    )
    private static int redirectGetMinimumY(ChunkGenerator generator) {
        int original = generator.getMinimumY();
        return Math.max(original, -60);
    }
}

