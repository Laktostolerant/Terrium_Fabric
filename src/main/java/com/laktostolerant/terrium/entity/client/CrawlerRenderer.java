package com.laktostolerant.terrium.entity.client;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.entity.custom.CrawlerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class CrawlerRenderer extends MobEntityRenderer<CrawlerEntity, CrawlerModel<CrawlerEntity>> {
    public CrawlerRenderer(EntityRendererFactory.Context context) {
        super(context, new CrawlerModel<>(context.getPart(CrawlerModel.CRAWLER)), 0.15F);
    }

    @Override
    public Identifier getTexture(CrawlerEntity entity) {
        return Identifier.of(Terrium.MOD_ID, "textures/entity/crawler/crawler.png");
    }

    @Override
    public void render(CrawlerEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(1F, 1F, 1F);
        }
        else {
            matrixStack.scale(2F, 2F, 2F);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
