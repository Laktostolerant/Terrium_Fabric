package com.laktostolerant.terrium.datagen;

import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.RABBIT_FOOD)
                .add(ModItems.PINECONE);

        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItems.HELLITE_SWORD)
                .add(ModItems.ASTRALITE_SWORD);

        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItems.HELLITE_PICKAXE)
                .add(ModItems.ASTRALITE_PICKAXE);

        getOrCreateTagBuilder(ItemTags.AXES)
                .add(ModItems.HELLITE_AXE)
                .add(ModItems.ASTRALITE_AXE);

        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(ModItems.HELLITE_SHOVEL)
                .add(ModItems.ASTRALITE_SHOVEL);

        getOrCreateTagBuilder(ItemTags.HOES)
                .add(ModItems.HELLITE_HOE)
                .add(ModItems.ASTRALITE_HOE);

        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
                .add(ModItems.HELLITE_HELMET)
                .add(ModItems.ASTRALITE_HELMET);

        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
                .add(ModItems.HELLITE_CHESTPLATE)
                .add(ModItems.ASTRALITE_CHESTPLATE);

        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
                .add(ModItems.HELLITE_LEGGINGS)
                .add(ModItems.ASTRALITE_LEGGINGS );

        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                .add(ModItems.HELLITE_BOOTS)
                .add(ModItems.ASTRALITE_BOOTS);

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.HELLITE_HELMET)
                .add(ModItems.HELLITE_CHESTPLATE)
                .add(ModItems.HELLITE_LEGGINGS)
                .add(ModItems.HELLITE_BOOTS);

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.ROSE_LOG.asItem())
                .add(ModBlocks.ROSE_WOOD.asItem())
                .add(ModBlocks.STRIPPED_ROSE_WOOD.asItem())
                .add(ModBlocks.STRIPPED_ROSE_LOG.asItem());

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.ROSE_PLANKS.asItem());
    }
}
