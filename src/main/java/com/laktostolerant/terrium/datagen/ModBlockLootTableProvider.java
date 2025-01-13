package com.laktostolerant.terrium.datagen;

import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    public ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.PURSHALE_BRICKS);
        addDrop(ModBlocks.LOAMSTONE);
        addDrop(ModBlocks.WATCHFUL_PURSHALE, block -> this.drops(block, ModBlocks.COBBLED_PURSHALE));
        addDrop(ModBlocks.PURSHALE, block -> this.drops(block, ModBlocks.COBBLED_PURSHALE));

        addDrop(ModBlocks.HELLITE_ORE, oreDrops(ModBlocks.HELLITE_ORE, ModItems.RAW_HELLITE));

        addDrop(ModBlocks.ROSE_LOG);
        addDrop(ModBlocks.ROSE_PLANKS);
        addDrop(ModBlocks.ROSE_WOOD);
        addDrop(ModBlocks.ROSE_SAPLING);
        addDrop(ModBlocks.STRIPPED_ROSE_LOG);
        addDrop(ModBlocks.STRIPPED_ROSE_WOOD);

        addDrop(ModBlocks.ROSE_LEAVES, leavesDrops(ModBlocks.ROSE_LEAVES, ModBlocks.ROSE_SAPLING, 0.005f));

        addDrop(ModBlocks.ROSE_STAIRS);
        addDrop(ModBlocks.ROSE_BUTTON);
        addDrop(ModBlocks.ROSE_FENCE);
        addDrop(ModBlocks.ROSE_GATE);
        addDrop(ModBlocks.ROSE_TRAPDOOR);

        addDrop(ModBlocks.ROSE_SLAB, slabDrops(ModBlocks.ROSE_SLAB));
        addDrop(ModBlocks.ROSE_DOOR, doorDrops(ModBlocks.ROSE_DOOR));
    }
}
