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
                    .displayName(Text.translatable("itemgroup.tutorialmod.terrium_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.HELLITE_INGOT);
                        entries.add(ModItems.RAW_HELLITE);
                        entries.add(ModItems.HELLITE_NUGGET);

                        entries.add(ModItems.HELLITE_SWORD);
                        entries.add(ModItems.HELLITE_PICKAXE);
                        entries.add(ModItems.HELLITE_AXE);
                        entries.add(ModItems.HELLITE_SHOVEL);
                        entries.add(ModItems.HELLITE_HOE);

                        entries.add(ModItems.HELLITE_HELMET);
                        entries.add(ModItems.HELLITE_CHESTPLATE);
                        entries.add(ModItems.HELLITE_LEGGINGS);
                        entries.add(ModItems.HELLITE_BOOTS);

                        entries.add(ModItems.PINECONE);
                        entries.add(ModItems.PINECANNON);
                        entries.add(ModItems.PINECANNON_CROSSBOW);
                        entries.add(ModItems.PINECONE_JAM);

                        entries.add(ModBlocks.PURSHALE);
                        entries.add(ModBlocks.WATCHFUL_PURSHALE);
                        entries.add(ModBlocks.COBBLED_PURSHALE);
                        entries.add(ModBlocks.PURSHALE_BRICKS);
                        entries.add(ModBlocks.HELLITE_ORE);
                        entries.add(ModBlocks.LOAMSTONE);

                        entries.add(ModBlocks.DARKELP);
                    })

                    .build()
    );


    public static void registerItemGroups() {
        Terrium.LOGGER.info("Registering I0tem Groups for " + Terrium.MOD_ID);
    }
}
