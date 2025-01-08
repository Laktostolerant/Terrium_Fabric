package com.laktostolerant.terrium;

import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.entity.ModEntities;
import com.laktostolerant.terrium.entity.client.VoidFishModel;
import com.laktostolerant.terrium.entity.client.VoidFishRenderer;
import com.laktostolerant.terrium.fluid.ModFluids;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class TerriumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DARKELP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DARKELP_PLANT, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DUSKWEED, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLUBBER, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MURKROOT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SNOWY_GRASS, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROSE_SAPLING, RenderLayer.getCutout());


        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.GOOP_STILL, ModFluids.GOOP_FLOWING, new SimpleFluidRenderHandler(
                Identifier.of("minecraft:block/water_still"),
                Identifier.of("minecraft:block/water_flow"),
                0x4CC248
        ));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.GOOP_STILL, ModFluids.GOOP_FLOWING);

        EntityModelLayerRegistry.registerModelLayer(VoidFishModel.VOID_FISH, VoidFishModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.VOID_FISH, VoidFishRenderer::new);
    }
}
