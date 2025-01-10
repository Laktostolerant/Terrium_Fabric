package com.laktostolerant.terrium.entity.client;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.entity.custom.PineconeShardEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class PineconeShardProjectileRenderer extends EntityRenderer<PineconeShardEntity> {
    protected  PineconeShardProjectileModel model;

    public PineconeShardProjectileRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new PineconeShardProjectileModel(ctx.getPart(PineconeShardProjectileModel.PINECONE_SHARD));
    }

    @Override
    public void render(PineconeShardEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        matrices.translate(0, -1.5F, 0);

        VertexConsumer vertexconsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers,
                this.model.getLayer(Identifier.of(Terrium.MOD_ID, "textures/entity/pinecone_shard/pinecone_shard.png")), false, false);

        this.model.render(matrices, vertexconsumer, light, OverlayTexture.DEFAULT_UV);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(PineconeShardEntity entity) {
        return Identifier.of(Terrium.MOD_ID, "textures/entity/pinecone_shard/pinecone_shard.png");
    }
}
