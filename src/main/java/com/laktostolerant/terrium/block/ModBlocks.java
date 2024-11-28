package com.laktostolerant.terrium.block;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.block.custom.CustomLandKelp;
import com.laktostolerant.terrium.block.custom.CustomLandKelpBlock;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    public static final Block PURSHALE = registerBlock("purshale",
            new Block(AbstractBlock.Settings.create()
                    .strength(4f, 7)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE)
            )
    );

    public static final Block WATCHFUL_PURSHALE = registerBlock("watchful_purshale",
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

    public static final Block LOAMSTONE = registerBlock("loamstone",
            new Block(AbstractBlock.Settings.create()
                            .strength(0.4f)
                            .requiresTool()
                            .sounds(BlockSoundGroup.NETHERRACK)
            )
    );

    public static final Block DARKELP_PLANT = registerBlock("darkelp_plant",
            new CustomLandKelpBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.WATER_BLUE)
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.WET_GRASS)
                    .luminance(state -> 3) // Emits light with level 7
                    .pistonBehavior(PistonBehavior.DESTROY),

                    Direction.UP, // Growth direction
                    Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 9.0, 16.0), // Example VoxelShape
                    false // Does not tick water
            )
    );

    public static final Block DARKELP = registerBlock("darkelp",
            new CustomLandKelp(AbstractBlock.Settings.create()
                    .mapColor(MapColor.WATER_BLUE)
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.WET_GRASS)
                    .luminance(state -> 3) // Emits light with level 7
                    .pistonBehavior(PistonBehavior.DESTROY),


                    Direction.UP, // Growth direction
                    Block.createCuboidShape(0.0, 0.0, 0.0, 14.0, 9.0, 16.0), // Example VoxelShape
                    false, // Does not tick water
                    0.1, // Growth chance
                    ModBlocks.DARKELP_PLANT // The plant block associated with this kelp
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
            entries.add(ModBlocks.WATCHFUL_PURSHALE);
            entries.add(ModBlocks.HELLITE_ORE);
            entries.add(ModBlocks.COBBLED_PURSHALE);
            entries.add(ModBlocks.PURSHALE_BRICKS);

            entries.add(ModBlocks.LOAMSTONE);

            entries.add(DARKELP);
        });
    }


}
