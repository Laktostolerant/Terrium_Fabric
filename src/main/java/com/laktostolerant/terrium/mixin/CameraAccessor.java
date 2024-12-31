package com.laktostolerant.terrium.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

//accessor for non static import
@Mixin(Camera.class)
public interface CameraAccessor {
    @Accessor("area")
    BlockView getArea();
}