package com.laktostolerant.terrium.mixin;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityGravity {
        @Inject(method = "createPlayerAttributes", at = @At("RETURN"))
        private static void injectCustomGravityAttribute(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
                DefaultAttributeContainer.Builder builder = cir.getReturnValue();
                builder.add(EntityAttributes.GENERIC_GRAVITY, 0.08D);
}}