package com.laktostolerant.terrium.datagen;

import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.fluid.ModFluids;
import com.laktostolerant.terrium.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;

import java.util.concurrent.CompletableFuture;



public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
                .add(ModBlocks.PURSHALE)
                .add(ModBlocks.WATCHFUL_PURSHALE);

        getOrCreateTagBuilder(ModTags.Blocks.ABYSS_GROWABLES)
                .add(ModBlocks.PURSHALE)
                        .add(ModBlocks.WATCHFUL_PURSHALE);

        getOrCreateTagBuilder(ModTags.Blocks.DEEP_JUNGLE_GROWABLES)
                .add(Blocks.GRASS_BLOCK);


        getOrCreateTagBuilder(BlockTags.BASE_STONE_OVERWORLD)
                .add(ModBlocks.PURSHALE);

        getOrCreateTagBuilder(BlockTags.DEEPSLATE_ORE_REPLACEABLES)
                .add(ModBlocks.PURSHALE);

        getOrCreateTagBuilder(ModTags.Blocks.PURSHALE_ORE_REPLACEABLES)
                .add(ModBlocks.PURSHALE);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.LOAMSTONE)
                .add(ModBlocks.WATCHFUL_PURSHALE)
                .add(ModBlocks.PURSHALE)
                .add(ModBlocks.PYSCORIA)
                .add(ModBlocks.HELLITE_ORE)
                .add(ModBlocks.COBBLED_PURSHALE)
                .add(ModBlocks.PURSHALE_BRICKS);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.PURSHALE)
                .add(ModBlocks.WATCHFUL_PURSHALE)
                .add(ModBlocks.COBBLED_PURSHALE)
                .add(ModBlocks.PURSHALE_BRICKS);

        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.PYSCORIA)
                .add(ModBlocks.HELLITE_ORE);


        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.ROSE_LOG)
                .add(ModBlocks.ROSE_WOOD)
                .add(ModBlocks.STRIPPED_ROSE_WOOD)
                .add(ModBlocks.STRIPPED_ROSE_LOG);

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.ROSE_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.ROSE_GATE);
    }
}
