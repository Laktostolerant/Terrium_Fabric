package com.laktostolerant.terrium.mixin;

import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class GravityTick {
    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        PlayerEntity self = (PlayerEntity)(Object)this;
        if (self.getY() <= -63) {
            self.getAttributeInstance(EntityAttributes.GENERIC_GRAVITY)
                    .setBaseValue(0.03D);
            self.getAttributeInstance(EntityAttributes.GENERIC_FALL_DAMAGE_MULTIPLIER).setBaseValue(0.05);
        } else {
            self.getAttributeInstance(EntityAttributes.GENERIC_GRAVITY)
                    .setBaseValue(0.08D);
            self.getAttributeInstance(EntityAttributes.GENERIC_FALL_DAMAGE_MULTIPLIER).setBaseValue(1);
        }
    }
}