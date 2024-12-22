package com.laktostolerant.terrium.block;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.block.custom.CustomLandKelp;
import com.laktostolerant.terrium.block.custom.CustomLandKelpBlock;
import com.laktostolerant.terrium.block.custom.CustomStickyBlock;
import com.laktostolerant.terrium.block.custom.DuskweedPlantBlock;
import com.laktostolerant.terrium.fluid.ModFluids;
import com.laktostolerant.terrium.world.tree.ModSaplingGenerator;
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
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    public static final Block PURSHALE = registerBlock("purshale",
            new PillarBlock(AbstractBlock.Settings.create()
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
                    .luminance(state -> 3)
                    .pistonBehavior(PistonBehavior.DESTROY),

                    Direction.UP, // Growth direction
                    Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 9.0, 16.0),
                    false // Does not tick water
            )
    );

    public static final Block DARKELP = registerBlockWithoutItem("darkelp",
            new CustomLandKelp(AbstractBlock.Settings.create()
                    .mapColor(MapColor.WATER_BLUE)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.WET_GRASS)
                    .luminance(state -> 3) // Emits light with level 7
                    .pistonBehavior(PistonBehavior.DESTROY),


                    Direction.UP, // Growth direction
                    Block.createCuboidShape(0.0, 0.0, 0.0, 14.0, 9.0, 16.0),
                    false, // Does not tick water
                    0.15, // Growth chance
                    ModBlocks.DARKELP_PLANT // The plant block associated with this kelp
            )
    );

    public static final Block DUSKWEED = registerBlockWithoutItem("duskweed",
            new DuskweedPlantBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .replaceable()
                    .noCollision()
                    .breakInstantly()
                    .luminance(state -> 6)
                    .sounds(BlockSoundGroup.GRASS)
                    .offset(AbstractBlock.OffsetType.XZ)
                    .pistonBehavior(PistonBehavior.DESTROY)
            )
    );

    public static final Block BLUBBER = registerBlock("blubber",
            new CustomStickyBlock(AbstractBlock.Settings.create()
                    .strength(1, 1)
                    .velocityMultiplier(0.0F)
                    .jumpVelocityMultiplier(0.1F)
                    .sounds(BlockSoundGroup.SLIME)
                    .breakInstantly()
                    .nonOpaque()
            )
    );

    public static final Block MURKROOT = registerBlockWithoutItem("murkroot",
            new HangingRootsBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DIRT_BROWN)
                    .replaceable()
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.HANGING_ROOTS)
                    .offset(AbstractBlock.OffsetType.XZ)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)
            )
    );


    public static final Block ROSE_LOG = registerBlock("rose_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_LOG)));

    public static final Block ROSE_WOOD= registerBlock("rose_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_WOOD)));

    public static final Block STRIPPED_ROSE_LOG = registerBlock("stripped_rose_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_CHERRY_LOG)));

    public static final Block STRIPPED_ROSE_WOOD = registerBlock("stripped_rose_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_CHERRY_WOOD)));

    public static final Block ROSE_PLANKS = registerBlock("rose_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.CHERRY_PLANKS)));

    public static final Block ROSE_LEAVES = registerBlock("rose_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_LEAVES)));

    public static final Block ROSE_SAPLING = registerBlock("rose_sapling",
            new SaplingBlock(ModSaplingGenerator.ROSE, AbstractBlock.Settings.copy(Blocks.CHERRY_SAPLING)));

    public static final Block ROSE_STAIRS = registerBlock("rose_stairs",
            new StairsBlock(ModBlocks.ROSE_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.CHERRY_STAIRS)));

    public static final Block ROSE_SLAB = registerBlock("rose_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_SLAB)));

    public static final Block ROSE_BUTTON = registerBlock("rose_button",
            new ButtonBlock(BlockSetType.CHERRY, 2, AbstractBlock.Settings.copy(Blocks.CHERRY_BUTTON)));

    public static final Block ROSE_PRESSURE_PLATE = registerBlock("rose_pressure_plate",
            new PressurePlateBlock(BlockSetType.CHERRY, AbstractBlock.Settings.copy(Blocks.CHERRY_PRESSURE_PLATE)));

    public static final Block ROSE_FENCE = registerBlock("rose_fence",
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_FENCE)));

    public static final Block ROSE_GATE = registerBlock("rose_gate",
            new FenceGateBlock(WoodType.CHERRY, AbstractBlock.Settings.copy(Blocks.CHERRY_FENCE)));

    public static final Block ROSE_DOOR = registerBlock("rose_door",
            new DoorBlock(BlockSetType.CHERRY, AbstractBlock.Settings.copy(Blocks.CHERRY_DOOR)));

    public static final Block ROSE_TRAPDOOR = registerBlock("rose_trapdoor",
            new TrapdoorBlock(BlockSetType.CHERRY, AbstractBlock.Settings.copy(Blocks.CHERRY_TRAPDOOR)));

    /*
    public static final Block ROSE_SIGN = registerBlock("rose_sign",
            new SignBlock(WoodType.OAK, AbstractBlock.Settings.copy(Blocks.CHERRY_SIGN)));
    */

    public static final Block BLACKROCK_PILLAR = registerBlock("blackrock_pillar",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_PILLAR)));


    public static final Block GOOP_FLUID_BLOCK = registerBlockWithoutItem("goop_fluid_block",
            new FluidBlock(ModFluids.GOOP_STILL, AbstractBlock.Settings.copy(Blocks.WATER)));

    public static final Block PYSCORIA = registerBlock("pyscoria",
            new PillarBlock(AbstractBlock.Settings.create()
                    .strength(5f, 8)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE)
            )
    );








    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Terrium.MOD_ID, name), block);
    }

    private static Block registerBlockWithoutItem(String name, Block block) {
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
            entries.add(DUSKWEED);

            entries.add(ModBlocks.BLUBBER);

            entries.add(ModBlocks.MURKROOT);
        });
    }


}
