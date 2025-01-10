package com.laktostolerant.terrium.entity.client;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.entity.custom.VoidFishEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class VoidFishRenderer extends MobEntityRenderer<VoidFishEntity, VoidFishModel<VoidFishEntity>> {
    public VoidFishRenderer(EntityRendererFactory.Context context) {
        super(context, new VoidFishModel<>(context.getPart(VoidFishModel.VOID_FISH)), 0.15F);
    }

    @Override
    public Identifier getTexture(VoidFishEntity entity) {
        return Identifier.of(Terrium.MOD_ID, "textures/entity/void_fish/void_fish.png");
    }

    @Override
    public void render(VoidFishEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5F, 0.5F, 0.5F);
        }
        else {
            matrixStack.scale(1F, 1F, 1F);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
