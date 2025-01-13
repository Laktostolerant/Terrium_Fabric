package com.laktostolerant.terrium.item;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup TERRIUM_ITEMS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Terrium.MOD_ID, "terrium_items"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.HELLITE_INGOT))
                    .displayName(Text.translatable("itemgroup.terrium.terrium_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.HELLITE_INGOT);
                        entries.add(ModItems.RAW_HELLITE);
                        entries.add(ModItems.HELLITE_NUGGET);

                        entries.add(ModItems.ASTRALITE_DUST);
                        entries.add(ModItems.ASTRALITE_SHARD);

                        entries.add(ModItems.HELLITE_SWORD);
                        entries.add(ModItems.HELLITE_PICKAXE);
                        entries.add(ModItems.HELLITE_AXE);
                        entries.add(ModItems.HELLITE_SHOVEL);
                        entries.add(ModItems.HELLITE_HOE);

                        entries.add(ModItems.HELLITE_HELMET);
                        entries.add(ModItems.HELLITE_CHESTPLATE);
                        entries.add(ModItems.HELLITE_LEGGINGS);
                        entries.add(ModItems.HELLITE_BOOTS);

                        entries.add(ModItems.ASTRALITE_HELMET);
                        entries.add(ModItems.ASTRALITE_CHESTPLATE);
                        entries.add(ModItems.ASTRALITE_LEGGINGS);
                        entries.add(ModItems.ASTRALITE_BOOTS);

                        entries.add(ModItems.ASTRALITE_SWORD);
                        entries.add(ModItems.ASTRALITE_PICKAXE);
                        entries.add(ModItems.ASTRALITE_AXE);
                        entries.add(ModItems.ASTRALITE_SHOVEL);
                        entries.add(ModItems.ASTRALITE_HOE);

                        entries.add(ModItems.PINECONE);
                        entries.add(ModItems.PINECANNON);
                        entries.add(ModItems.PINECANNON_CROSSBOW);
                        entries.add(ModItems.PINECONE_JAM);

                        entries.add(ModBlocks.PURSHALE);
                        entries.add(ModBlocks.PYSCORIA);
                        entries.add(ModBlocks.WATCHFUL_PURSHALE);
                        entries.add(ModBlocks.COBBLED_PURSHALE);
                        entries.add(ModBlocks.PURSHALE_BRICKS);
                        entries.add(ModBlocks.HELLITE_ORE);
                        entries.add(ModBlocks.LOAMSTONE);

                        entries.add(ModItems.DARKELP);
                        entries.add(ModBlocks.DUSKWEED);

                        entries.add(ModBlocks.BLUBBER);

                        entries.add(ModItems.MURKROOT);

                        entries.add(ModBlocks.ROSE_LOG);
                        entries.add(ModBlocks.ROSE_WOOD);
                        entries.add(ModBlocks.STRIPPED_ROSE_LOG);
                        entries.add(ModBlocks.STRIPPED_ROSE_WOOD);
                        entries.add(ModBlocks.ROSE_LEAVES);
                        entries.add(ModBlocks.ROSE_SAPLING);

                        entries.add(ModBlocks.ROSE_PLANKS);
                        entries.add(ModBlocks.ROSE_STAIRS);
                        entries.add(ModBlocks.ROSE_FENCE);
                        entries.add(ModBlocks.ROSE_TRAPDOOR);
                        entries.add(ModBlocks.ROSE_DOOR);
                        entries.add(ModBlocks.ROSE_SLAB);
                        entries.add(ModBlocks.ROSE_GATE);
                        entries.add(ModBlocks.ROSE_BUTTON);
                        entries.add(ModBlocks.ROSE_PRESSURE_PLATE);

                        entries.add(ModBlocks.BLACKROCK_PILLAR);

                        entries.add(ModItems.GOOP_BUCKET);

                        entries.add(ModItems.SNOWY_GRASS);

                        entries.add(ModBlocks.NATURAL_ASTRALITE);

                        entries.add(ModItems.POTION_OF_RECALL);

                        entries.add(ModItems.DYNAMITE);
                    })

                    .build()
    );


    public static void registerItemGroups() {
        Terrium.LOGGER.info("Registering I0tem Groups for " + Terrium.MOD_ID);
    }
}
