package com.laktostolerant.terrium.entity;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.entity.custom.CrawlerEntity;
import com.laktostolerant.terrium.entity.custom.DynamiteEntity;
import com.laktostolerant.terrium.entity.custom.PineconeShardEntity;
import com.laktostolerant.terrium.entity.custom.VoidFishEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<VoidFishEntity> VOID_FISH = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Terrium.MOD_ID, "void_fish"),
            EntityType.Builder.create(VoidFishEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.3F, 0.3F)

                    .build()
    );

    public static final EntityType<CrawlerEntity> CRAWLER = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Terrium.MOD_ID, "crawler"),
            EntityType.Builder.create(CrawlerEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1F, 1F)

                    .build()
    );

    public static final EntityType<PineconeShardEntity> PINECONE_SHARD = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Terrium.MOD_ID, "pinecone_shard"),
            EntityType.Builder.<PineconeShardEntity>create(PineconeShardEntity::new, SpawnGroup.MISC)
                    .dimensions(0.2f, 0.2f)

                    .build());

    public static final EntityType<DynamiteEntity> DYNAMITE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Terrium.MOD_ID, "dynamite"),
            EntityType.Builder.<DynamiteEntity>create(DynamiteEntity::new, SpawnGroup.MISC)
                    .dimensions(0.2f, 0.2f)

                    .build());

    public static void registerModEntities() {
        Terrium.LOGGER.info("Registering Mod Entities for " + Terrium.MOD_ID);
    }
}
