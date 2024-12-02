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
    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final MaterialRules.MaterialRule LOAMSTONE = makeStateRule(ModBlocks.LOAMSTONE);
    private static final MaterialRules.MaterialRule PURSHALE = makeStateRule(ModBlocks.PURSHALE);

    private static final MaterialRules.MaterialCondition STONE_CEILING = stoneDepth(0, true, 5, VerticalSurfaceType.CEILING);
    private static final MaterialRules.MaterialCondition STONE_FLOOR = stoneDepth(0, true, 10, VerticalSurfaceType.FLOOR);

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);

        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);

        return MaterialRules.sequence(
                MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(ModBiomes.DEEP_JUNGLE),
                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, LOAMSTONE)),
                MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING_WITH_SURFACE_DEPTH, LOAMSTONE)),

                MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(ModBiomes.ABYSS_BIOME),
                        MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, PURSHALE)),
                        MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING_WITH_SURFACE_DEPTH, PURSHALE)),

                // Default to a grass and dirt surface
                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, grassSurface)
        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}
