package com.laktostolerant.terrium.entity.client;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.entity.custom.CrawlerEntity;
import com.laktostolerant.terrium.entity.custom.VoidFishEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class CrawlerModel <T extends CrawlerEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer CRAWLER = new EntityModelLayer(Identifier.of(Terrium.MOD_ID, "crawler"), "main");

    private final ModelPart main_body;
    private final ModelPart teeth;
    private final ModelPart tail;
    private final ModelPart head;
    private final ModelPart spikes_left;
    private final ModelPart spikes_right;

    public CrawlerModel(ModelPart root) {
        this.main_body = root.getChild("main_body");
        this.teeth = root.getChild("teeth");
        this.tail = root.getChild("tail");
        this.head = root.getChild("head");
        this.spikes_left = root.getChild("spikes_left");
        this.spikes_right = root.getChild("spikes_right");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData main_body = modelPartData.addChild("main_body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.5F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 19.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData teeth = modelPartData.addChild("teeth", ModelPartBuilder.create().uv(40, 14).cuboid(-1.5F, -1.0F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 22.0F, -9.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData tail = modelPartData.addChild("tail", ModelPartBuilder.create().uv(0, 20).cuboid(-7.5F, -9.0F, 2.0F, 9.0F, 9.0F, 4.0F, new Dilation(0.0F))
                .uv(26, 20).cuboid(-7.0F, -8.0F, -1.0F, 8.0F, 8.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 33).cuboid(-6.5F, -6.0F, -4.0F, 7.0F, 6.0F, 3.0F, new Dilation(0.0F))
                .uv(40, 8).cuboid(-5.5F, -4.0F, -6.0F, 5.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 24.0F, 11.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(26, 31).cuboid(-4.0F, -8.0F, -5.0F, 8.0F, 8.0F, 2.0F, new Dilation(0.0F))
                .uv(40, 0).cuboid(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 24.0F, -10.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData spikes_left = modelPartData.addChild("spikes_left", ModelPartBuilder.create(), ModelTransform.of(3.0F, 14.0F, 2.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData cube_r1 = spikes_left.addChild("cube_r1", ModelPartBuilder.create().uv(0, -2).cuboid(0.0F, -3.0F, -2.5F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.5F, -5.1F, 0.0F, 0.0F, -0.5236F));

        ModelPartData cube_r2 = spikes_left.addChild("cube_r2", ModelPartBuilder.create().uv(0, -2).cuboid(0.0F, -3.0F, -3.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(0, -2).cuboid(0.0F, -3.0F, 2.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.5F, 0.9F, 0.0F, 0.0F, -0.5236F));

        ModelPartData spikes_right = modelPartData.addChild("spikes_right", ModelPartBuilder.create(), ModelTransform.of(-5.0F, 14.0F, -2.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData cube_r3 = spikes_right.addChild("cube_r3", ModelPartBuilder.create().uv(0, -2).cuboid(0.0F, -3.0F, -3.5F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.5F, -8.1F, 0.0F, 0.0F, 0.5236F));

        ModelPartData cube_r4 = spikes_right.addChild("cube_r4", ModelPartBuilder.create().uv(0, -2).cuboid(0.0F, -3.0F, -3.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(0, -2).cuboid(0.0F, -3.0F, 2.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.5F, -3.1F, 0.0F, 0.0F, 0.5236F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    @Override
    public ModelPart getPart() {
        return main_body;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        main_body.render(matrices, vertexConsumer, light, overlay, color);
        teeth.render(matrices, vertexConsumer, light, overlay, color);
        tail.render(matrices, vertexConsumer, light, overlay, color);
        head.render(matrices, vertexConsumer, light, overlay, color);
        spikes_left.render(matrices, vertexConsumer, light, overlay, color);
        spikes_right.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.animateMovement(CrawlerAnimations.CRAWL, limbAngle, limbDistance, 2, 2.5F);
        this.updateAnimation(entity.idleAnimationState, CrawlerAnimations.IDLE, animationProgress, 1F);
    }
}
