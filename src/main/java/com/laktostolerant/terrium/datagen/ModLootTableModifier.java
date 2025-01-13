package com.laktostolerant.terrium.datagen;

import com.laktostolerant.terrium.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.mixin.loot.LootTableAccessor;
import net.minecraft.block.VaultBlock;
import net.minecraft.block.enums.ChestType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.SurvivesExplosionLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.collection.Weight;

public class ModLootTableModifier {

    private static final RegistryKey<LootTable> OMINOUS_VAULT = LootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_UNIQUE_CHEST;

    public ModLootTableModifier()
    {
        throw new AssertionError();
    }

    public static void register()
    {
        LootTableEvents.MODIFY.register((registryKey, builder, lootTableSource, wrapperLookup) -> {
            if (OMINOUS_VAULT == registryKey && lootTableSource.isBuiltin()) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1)) // Ensures only one roll
                        .conditionally(RandomChanceLootCondition.builder(0.15f)) // 15% chance
                        .with(ItemEntry.builder(ModItems.POTION_OF_RECALL)
                                .weight(1));

                builder.pool(pool);
            }
        });

    }
}
