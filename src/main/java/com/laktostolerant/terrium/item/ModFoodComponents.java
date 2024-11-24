package com.laktostolerant.terrium.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent PINECONE_JAM = new FoodComponent.Builder()
            .nutrition(3)
            .saturationModifier(0.3f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 50), 0.33f)
            .build();


}
