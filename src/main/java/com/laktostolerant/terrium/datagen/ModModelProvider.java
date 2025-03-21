package com.laktostolerant.terrium.datagen;

import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSingleton(ModBlocks.WATCHFUL_PURSHALE, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.HELLITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.NATURAL_ASTRALITE);

        blockStateModelGenerator.registerRotatable(ModBlocks.PURSHALE_BRICKS);
        blockStateModelGenerator.registerRotatable(ModBlocks.COBBLED_PURSHALE);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LOAMSTONE);


        blockStateModelGenerator.registerPlantPart(ModBlocks.DARKELP, ModBlocks.DARKELP_PLANT, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.excludeFromSimpleItemModelGeneration(ModBlocks.DARKELP_PLANT);

        blockStateModelGenerator.registerSingleton(ModBlocks.DUSKWEED, TexturedModel.TEMPLATE_SEAGRASS);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLUBBER);

        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.MURKROOT, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerLog(ModBlocks.ROSE_LOG).log(ModBlocks.ROSE_LOG).wood(ModBlocks.ROSE_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_ROSE_LOG).log(ModBlocks.STRIPPED_ROSE_LOG).wood(ModBlocks.STRIPPED_ROSE_WOOD);

        BlockStateModelGenerator.BlockTexturePool roseWoodPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.ROSE_PLANKS);
        roseWoodPool.family(new BlockFamily.Builder(ModBlocks.ROSE_PLANKS).build());
        roseWoodPool.stairs(ModBlocks.ROSE_STAIRS);
        roseWoodPool.slab(ModBlocks.ROSE_SLAB);
        roseWoodPool.button(ModBlocks.ROSE_BUTTON);
        roseWoodPool.fence(ModBlocks.ROSE_FENCE);
        roseWoodPool.fenceGate(ModBlocks.ROSE_GATE);
        roseWoodPool.pressurePlate(ModBlocks.ROSE_PRESSURE_PLATE);

        blockStateModelGenerator.registerDoor(ModBlocks.ROSE_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.ROSE_TRAPDOOR);


        blockStateModelGenerator.registerSingleton(ModBlocks.ROSE_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.ROSE_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerSingleton(ModBlocks.PURSHALE, TexturedModel.END_FOR_TOP_CUBE_COLUMN);
        blockStateModelGenerator.registerSingleton(ModBlocks.PYSCORIA, TexturedModel.END_FOR_TOP_CUBE_COLUMN);
        blockStateModelGenerator.registerSingleton(ModBlocks.BLACKROCK_PILLAR, TexturedModel.END_FOR_TOP_CUBE_COLUMN);

        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.SNOWY_GRASS, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FACTORY_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FACTORY_GRATE);

        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.METAL_WALL);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.RUBBLE);

        //blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SMALL_MUD_BRICKS);

        BlockStateModelGenerator.BlockTexturePool smallMudBricksPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SMALL_MUD_BRICKS);
        smallMudBricksPool.family(new BlockFamily.Builder(ModBlocks.SMALL_MUD_BRICKS).build());
        smallMudBricksPool.stairs(ModBlocks.SMALL_MUD_BRICKS_STAIRS);
        smallMudBricksPool.slab(ModBlocks.SMALL_MUD_BRICKS_SLAB);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SMOOTH_PACKED_MUD);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PINECONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINECANNON, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINECANNON_CROSSBOW, Models.HANDHELD);
        itemModelGenerator.register(ModItems.PINECONE_JAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.POTION_OF_RECALL, Models.GENERATED);

        itemModelGenerator.register(ModItems.HELLITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.HELLITE_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_HELLITE, Models.GENERATED);

        itemModelGenerator.register(ModItems.ASTRALITE_DUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.ASTRALITE_SHARD, Models.GENERATED);

        itemModelGenerator.register(ModItems.HELLITE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HELLITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HELLITE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HELLITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HELLITE_HOE, Models.HANDHELD);

        itemModelGenerator.registerArmor((ArmorItem) ModItems.HELLITE_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.HELLITE_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.HELLITE_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.HELLITE_BOOTS);

        itemModelGenerator.registerArmor((ArmorItem) ModItems.ASTRALITE_HELMET);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.ASTRALITE_CHESTPLATE);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.ASTRALITE_LEGGINGS);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.ASTRALITE_BOOTS);

        itemModelGenerator.register(ModItems.DARKELP, Models.GENERATED);
        itemModelGenerator.register(ModItems.DUSKWEED, Models.GENERATED);
        itemModelGenerator.register(ModItems.MURKROOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SNOWY_GRASS, Models.GENERATED);

        itemModelGenerator.register(ModItems.ASTRALITE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ASTRALITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ASTRALITE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ASTRALITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ASTRALITE_HOE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.DYNAMITE, Models.GENERATED);
    }
}
