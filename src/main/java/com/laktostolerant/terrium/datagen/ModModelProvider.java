package com.laktostolerant.terrium.datagen;

import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PURSHALE);
        blockStateModelGenerator.registerSingleton(ModBlocks.WATCHFUL_PURSHALE, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.HELLITE_ORE);

        blockStateModelGenerator.registerRotatable(ModBlocks.PURSHALE_BRICKS);
        blockStateModelGenerator.registerRotatable(ModBlocks.COBBLED_PURSHALE);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LOAMSTONE);


        blockStateModelGenerator.registerPlantPart(ModBlocks.DARKELP, ModBlocks.DARKELP_PLANT, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.excludeFromSimpleItemModelGeneration(ModBlocks.DARKELP_PLANT);

        blockStateModelGenerator.registerSingleton(ModBlocks.DUSKWEED, TexturedModel.TEMPLATE_SEAGRASS);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PINECONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINECANNON, Models.HANDHELD_MACE);
        itemModelGenerator.register(ModItems.PINECANNON_CROSSBOW, Models.HANDHELD);
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

        itemModelGenerator.register(ModItems.DARKELP, Models.GENERATED);
        itemModelGenerator.register(ModItems.DUSKWEED, Models.GENERATED);
    }
}
