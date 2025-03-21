package com.laktostolerant.terrium.world.gen;

import com.laktostolerant.terrium.entity.ModEntities;
import com.laktostolerant.terrium.world.biome.ModBiomes;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;

public class ModEntitySpawns {
    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(ModBiomes.ABYSS_BIOME),
                SpawnGroup.CREATURE, ModEntities.VOID_FISH, 1, 2, 5);

        SpawnRestriction.register(ModEntities.VOID_FISH, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FlyingEntity::canMobSpawn);
    }
}
