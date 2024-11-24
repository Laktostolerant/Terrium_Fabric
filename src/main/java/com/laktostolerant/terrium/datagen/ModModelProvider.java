package com.laktostolerant.terrium.datagen;

import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURSHALE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.HELLITE_ORE);

        blockStateModelGenerator.registerRotatable(ModBlocks.PURSHALE_BRICKS);
        blockStateModelGenerator.registerRotatable(ModBlocks.COBBLED_PURSHALE);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.QUADRANT);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PINECONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINECONE_JAM, Models.GENERATED);

        itemModelGenerator.register(ModItems.HELLITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.HELLITE_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_HELLITE, Models.GENERATED);

        itemModelGenerator.register(ModItems.HELLITE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HELLITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HELLITE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HELLITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HELLITE_HOE, Models.HANDHELD);

        itemModelGenerator.registerArmor((ArmorItem) ModItems.HELLITE_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.HELLITE_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.HELLITE_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.HELLITE_BOOTS);
    }
}
