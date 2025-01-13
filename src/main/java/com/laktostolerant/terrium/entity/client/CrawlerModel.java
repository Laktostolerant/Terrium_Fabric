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

    private final ModelPart crawler;

    public CrawlerModel(ModelPart root) {
        this.crawler = root.getChild("crawler");

    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData crawler = modelPartData.addChild("crawler", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData main_body = crawler.addChild("main_body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.5F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, -5.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData teeth = crawler.addChild("teeth", ModelPartBuilder.create().uv(40, 14).cuboid(-1.5F, -1.0F, 0.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -2.0F, -9.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData tail = crawler.addChild("tail", ModelPartBuilder.create().uv(0, 20).cuboid(-7.5F, -9.0F, 2.0F, 9.0F, 9.0F, 4.0F, new Dilation(0.0F))
                .uv(26, 20).cuboid(-7.0F, -8.0F, -1.0F, 8.0F, 8.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 33).cuboid(-6.5F, -6.0F, -4.0F, 7.0F, 6.0F, 3.0F, new Dilation(0.0F))
                .uv(40, 8).cuboid(-5.5F, -4.0F, -6.0F, 5.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 0.0F, 11.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData head = crawler.addChild("head", ModelPartBuilder.create().uv(26, 31).cuboid(-4.0F, -8.0F, -5.0F, 8.0F, 8.0F, 2.0F, new Dilation(0.0F))
                .uv(40, 0).cuboid(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, -10.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData spikes_left = crawler.addChild("spikes_left", ModelPartBuilder.create(), ModelTransform.of(3.0F, -10.0F, 2.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData cube_r1 = spikes_left.addChild("cube_r1", ModelPartBuilder.create().uv(0, -2).cuboid(0.0F, -3.0F, -2.5F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.5F, -5.1F, 0.0F, 0.0F, -0.5236F));

        ModelPartData cube_r2 = spikes_left.addChild("cube_r2", ModelPartBuilder.create().uv(0, -2).cuboid(0.0F, -3.0F, -3.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(0, -2).cuboid(0.0F, -3.0F, 2.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.5F, 0.9F, 0.0F, 0.0F, -0.5236F));

        ModelPartData spikes_right = crawler.addChild("spikes_right", ModelPartBuilder.create(), ModelTransform.of(-5.0F, -10.0F, -2.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData cube_r3 = spikes_right.addChild("cube_r3", ModelPartBuilder.create().uv(0, -2).cuboid(0.0F, -3.0F, -3.5F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.5F, -8.1F, 0.0F, 0.0F, 0.5236F));

        ModelPartData cube_r4 = spikes_right.addChild("cube_r4", ModelPartBuilder.create().uv(0, -2).cuboid(0.0F, -3.0F, -3.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F))
                .uv(0, -2).cuboid(0.0F, -3.0F, 2.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.5F, -3.1F, 0.0F, 0.0F, 0.5236F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public ModelPart getPart() {
        return crawler;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        crawler.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        this.animateMovement(CrawlerAnimations.CRAWL, limbAngle, limbDistance, 2, 2.5F);
        this.updateAnimation(entity.idleAnimationState, CrawlerAnimations.IDLE, animationProgress, 1F);
    }
}
