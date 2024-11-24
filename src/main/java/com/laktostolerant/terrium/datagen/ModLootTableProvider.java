package com.laktostolerant.terrium.datagen;

import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.item.ModItems;
import io.netty.util.internal.shaded.org.jctools.queues.MpscArrayQueue;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.PURSHALE_BRICKS);

        addDrop(ModBlocks.PURSHALE, block -> this.drops(block, ModBlocks.COBBLED_PURSHALE));

        addDrop(ModBlocks.HELLITE_ORE, oreDrops(ModBlocks.HELLITE_ORE, ModItems.RAW_HELLITE));
    }
}
