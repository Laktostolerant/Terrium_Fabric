package com.laktostolerant.terrium.entity.client;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.entity.custom.VoidFishEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class VoidFishModel<T extends VoidFishEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer VOID_FISH = new EntityModelLayer(Identifier.of(Terrium.MOD_ID, "void_fish"), "main");

    private final ModelPart body;

    public VoidFishModel(ModelPart root) {
        this.body = root.getChild("body");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -4.0F, 1.0F, 2.0F, 4.0F, 7.0F, new Dilation(0.0F))
                .uv(20, -6).cuboid(0.0F, -5.0F, 0.0F, 0.0F, 1.0F, 6.0F, new Dilation(0.0F))
                .uv(22, -1).cuboid(0.0F, 0.0F, 3.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-0.9992F, -2.0008F, -3.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(11, 0).cuboid(-1.0F, -2.0F, -2.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, 0.0F));

        ModelPartData leftFin = body.addChild("leftFin", ModelPartBuilder.create().uv(-2, 25).cuboid(0.0F, 0.0F, 1.0F, 5.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -1.0F, 0.0F, 0.0F, 0.0F, 0.6109F));

        ModelPartData rightFin = body.addChild("rightFin", ModelPartBuilder.create().uv(-3, 19).cuboid(-5.0F, 0.0F, 1.0F, 5.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -1.0F, 0.0F, 0.0F, 0.0F, -0.6109F));

        ModelPartData tailfin = body.addChild("tailfin", ModelPartBuilder.create().uv(19, 0).cuboid(0.0F, -6.0F, 0.0F, 0.0F, 7.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 8.0F));

        ModelPartData waist = body.addChild("waist", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        body.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return body;
    }

    @Override
    public void setAngles(VoidFishEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.animateMovement(VoidFishAnimations.ANIM_VOIDFISH_SWIM, limbAngle, limbDistance, 2, 2.5F);
        this.updateAnimation(entity.idleAnimationState, VoidFishAnimations.ANIM_VOIDFISH_IDLE, animationProgress, 1F);
    }
}
