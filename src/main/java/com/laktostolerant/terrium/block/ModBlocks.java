package com.laktostolerant.terrium.block;

import com.laktostolerant.terrium.Terrium;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    public static final Block PURSHALE = registerBlock("purshale",
            new Block(AbstractBlock.Settings.create()
                    .strength(4f, 7)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE)
            )
    );

    public static final Block COBBLED_PURSHALE = registerBlock("cobbled_purshale",
            new Block(AbstractBlock.Settings.create()
                    .strength(4f, 7)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE)
            )
    );

    public static final Block PURSHALE_BRICKS = registerBlock("purshale_bricks",
            new Block(AbstractBlock.Settings.create()
                    .strength(4f, 7)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE)
            )
    );

    public static final Block HELLITE_ORE = registerBlock("hellite_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5),
                    AbstractBlock.Settings.create()
                            .strength(4f, 7)
                            .requiresTool()
                            .sounds(BlockSoundGroup.DEEPSLATE)
            )
    );

    public static final Block QUADRANT = registerBlock("quadrant",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5),
                    AbstractBlock.Settings.create()
                            .strength(5f, 6)
                            .requiresTool()
                            .sounds(BlockSoundGroup.DEEPSLATE)
            )
    );


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Terrium.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Terrium.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }


    public static void registerModBlocks() {
        Terrium.LOGGER.info("Registering Mod Blocks for " + Terrium.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.PURSHALE);
            entries.add(ModBlocks.HELLITE_ORE);
            entries.add(ModBlocks.COBBLED_PURSHALE);
            entries.add(ModBlocks.PURSHALE_BRICKS);

            entries.add(ModBlocks.QUADRANT);
        });
    }


}
