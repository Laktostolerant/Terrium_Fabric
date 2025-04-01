package com.laktostolerant.terrium.mixin.world;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.world.gen.structure.JigsawStructure;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(JigsawStructure.class)
public class StructureJigsawSizeMixin {
    @ModifyConstant(method = "method_41662", constant = @Constant(intValue = 20, ordinal = 0))
    private static int modifySizeConstant(int original) {
        return 40;
    }
}