package com.laktostolerant.terrium.world.biome.surface;

import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.world.biome.ModBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

import static net.minecraft.world.gen.surfacebuilder.MaterialRules.stoneDepth;

public class ModMaterialRules {
    private static final MaterialRules.MaterialRule DIRT = makeStateRule(Blocks.DIRT);
    private static final MaterialRules.MaterialRule GRASS = makeStateRule(Blocks.GRASS_BLOCK);
    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final MaterialRules.MaterialRule PURSHALE = makeStateRule(ModBlocks.PURSHALE);
    private static final MaterialRules.MaterialRule TUFF = makeStateRule(Blocks.TUFF);

    private  static final MaterialRules.MaterialRule WOOL_1 = makeStateRule(Blocks.RED_WOOL);
    private  static final MaterialRules.MaterialRule WOOL_2 = makeStateRule(Blocks.BLUE_WOOL);

    private static final MaterialRules.MaterialCondition STONE_CEILING = stoneDepth(0, true, 5, VerticalSurfaceType.CEILING);
    private static final MaterialRules.MaterialCondition STONE_FLOOR = stoneDepth(0, true, 10, VerticalSurfaceType.FLOOR);

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);

        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);


        return MaterialRules.sequence(
                MaterialRules.condition(MaterialRules.biome(ModBiomes.DEEP_JUNGLE),
                        MaterialRules.sequence(
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, GRASS),
                                MaterialRules.condition(STONE_FLOOR, DIRT),
                                MaterialRules.block(ModBlocks.PYSCORIA.getDefaultState())
                        )
                ),

                MaterialRules.condition(MaterialRules.biome(ModBiomes.ABYSS_BIOME),
                        MaterialRules.sequence(
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6, PURSHALE),
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING_WITH_SURFACE_DEPTH, PURSHALE),
                                MaterialRules.block(ModBlocks.PURSHALE.getDefaultState())
                        )
                )
        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}
