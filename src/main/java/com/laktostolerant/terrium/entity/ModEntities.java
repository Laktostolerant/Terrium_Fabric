package com.laktostolerant.terrium.entity;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.entity.custom.PineconeShardEntity;
import com.laktostolerant.terrium.entity.custom.VoidFishEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<VoidFishEntity> VOID_FISH = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Terrium.MOD_ID, "void_fish"),
            EntityType.Builder.create(VoidFishEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.3F, 0.3F).build()
    );

    /*
    public static final EntityType<PineconeShardEntity> PINECONE_SHARD = Registry.register(
            Identifier.of(Terrium.MOD_ID, "pinecone_shard"),
            EntityType.Builder.create(PineconeShardEntity::new, SpawnGroup.MISC)
                    .setDimensions(0.5F, 0.5F) // Adjust size as needed
                    .build()
    );

     */

    public static void registerModEntities() {
        Terrium.LOGGER.info("Registering Mod Entities for " + Terrium.MOD_ID);
    }
}
