package com.laktostolerant.terrium.datagen;

import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;



public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        SetupCustomTiers();

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
                .add(ModBlocks.NATURAL_ASTRALITE)
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

        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_ASTRALITE_TOOL)
                .add(ModBlocks.NATURAL_ASTRALITE);


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

    protected void SetupCustomTiers()
    {
        getOrCreateTagBuilder(ModTags.Blocks.INCORRECT_FOR_HELLITE_TOOL)
                .addOptionalTag(Identifier.of("terrium", "needs_astralite_tool"));

        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .addOptionalTag(Identifier.of("terrium", "needs_astralite_tool"))
                .addOptionalTag(Identifier.of("terrium", "needs_hellite_tool"));

        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .addOptionalTag(Identifier.of("terrium", "needs_astralite_tool"))
                .addOptionalTag(Identifier.of("terrium", "needs_hellite_tool"))
                .addOptionalTag(Identifier.of("terrium", "needs_netherite_tool"));

        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .addOptionalTag(Identifier.of("terrium", "needs_astralite_tool"))
                .addOptionalTag(Identifier.of("terrium", "needs_hellite_tool"))
                .addOptionalTag(Identifier.of("terrium", "needs_netherite_tool"));

        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .addOptionalTag(Identifier.of("terrium", "needs_astralite_tool"))
                .addOptionalTag(Identifier.of("terrium", "needs_hellite_tool"))
                .addOptionalTag(Identifier.of("terrium", "needs_netherite_tool"));

        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .addOptionalTag(Identifier.of("terrium", "needs_astralite_tool"))
                .addOptionalTag(Identifier.of("terrium", "needs_hellite_tool"))
                .addOptionalTag(Identifier.of("terrium", "needs_netherite_tool"));

        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .addOptionalTag(Identifier.of("terrium", "needs_astralite_tool"))
                .addOptionalTag(Identifier.of("terrium", "needs_hellite_tool"))
                .addOptionalTag(Identifier.of("terrium", "needs_netherite_tool"));
    }
}
