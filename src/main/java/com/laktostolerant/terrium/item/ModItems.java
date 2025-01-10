package com.laktostolerant.terrium.item;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.fluid.ModFluids;
import com.laktostolerant.terrium.item.custom.ModCustomCrossbow;
import com.laktostolerant.terrium.item.custom.ModDrinkItem;
import com.laktostolerant.terrium.item.custom.ModCustomCannon;
import com.laktostolerant.terrium.item.custom.RecallItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.UseAction;

public class ModItems {

    public static final Item HELLITE_INGOT = registerItem("hellite_ingot", new Item(new Item.Settings()));
    public static final Item RAW_HELLITE = registerItem("raw_hellite", new Item(new Item.Settings()));
    public static final Item HELLITE_NUGGET = registerItem("hellite_nugget", new Item(new Item.Settings()));

    public static final Item ASTRALITE_DUST = registerItem("astralite_dust", new Item(new Item.Settings().rarity(Rarity.UNCOMMON).component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)));
    public static final Item ASTRALITE_SHARD = registerItem("astralite_shard", new Item(new Item.Settings().rarity(Rarity.UNCOMMON).component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)));

    public static final Item PINECONE = registerItem("pinecone", new Item(new Item.Settings()));
    public static final Item PINECONE_JAM = registerItem("pinecone_jam", new ModDrinkItem(new Item.Settings().food(ModFoodComponents.PINECONE_JAM)));
    public static final Item POTION_OF_RECALL = registerItem("potion_of_recall", new RecallItem(new Item.Settings().food(ModFoodComponents.PINECONE_JAM)));

    public static final Item HELLITE_SWORD = registerItem("hellite_sword", new SwordItem(ModToolMaterials.HELLITE, new Item.Settings()
            .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.HELLITE, 3, -2.4f))));
    public static final Item HELLITE_PICKAXE= registerItem("hellite_pickaxe", new PickaxeItem(ModToolMaterials.HELLITE, new Item.Settings()
            .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.HELLITE, 1, -2.8f))));
    public static final Item HELLITE_AXE = registerItem("hellite_axe", new AxeItem(ModToolMaterials.HELLITE, new Item.Settings()
            .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.HELLITE, 5f, -3.2f))));
    public static final Item HELLITE_SHOVEL = registerItem("hellite_shovel", new ShovelItem(ModToolMaterials.HELLITE, new Item.Settings()
            .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.HELLITE, 1.5f, -3.0f))));
    public static final Item HELLITE_HOE = registerItem("hellite_hoe", new HoeItem(ModToolMaterials.HELLITE, new Item.Settings()
            .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.HELLITE, -5, -3f))));

    public static final Item HELLITE_HELMET = registerItem("hellite_helmet", new ArmorItem(ModArmorMaterials.HELLITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
            .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(45))));
    public static final Item HELLITE_CHESTPLATE = registerItem("hellite_chestplate", new ArmorItem(ModArmorMaterials.HELLITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
            .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(45))));
    public static final Item HELLITE_LEGGINGS = registerItem("hellite_leggings", new ArmorItem(ModArmorMaterials.HELLITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
            .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(45))));
    public static final Item HELLITE_BOOTS = registerItem("hellite_boots", new ArmorItem(ModArmorMaterials.HELLITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
            .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(45))));

    public static final Item ASTRALITE_HELMET = registerItem("astralite_helmet", new ArmorItem(ModArmorMaterials.ASTRALITE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
            .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(60))));
    public static final Item ASTRALITE_CHESTPLATE = registerItem("astralite_chestplate", new ArmorItem(ModArmorMaterials.ASTRALITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
            .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(60))));
    public static final Item ASTRALITE_LEGGINGS = registerItem("astralite_leggings", new ArmorItem(ModArmorMaterials.ASTRALITE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
            .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(60))));
    public static final Item ASTRALITE_BOOTS = registerItem("astralite_boots", new ArmorItem(ModArmorMaterials.ASTRALITE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
            .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(60))));

    public static final Item ASTRALITE_SWORD = registerItem("astralite_sword", new SwordItem(ModToolMaterials.ASTRALITE, new Item.Settings()
            .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.ASTRALITE, 4, -2.4f))));
    public static final Item ASTRALITE_PICKAXE = registerItem("astralite_pickaxe", new PickaxeItem(ModToolMaterials.ASTRALITE, new Item.Settings()
            .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.ASTRALITE, 5, -2.8f))));
    public static final Item ASTRALITE_AXE = registerItem("astralite_axe", new AxeItem(ModToolMaterials.ASTRALITE, new Item.Settings()
            .attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.ASTRALITE, 6f, -3.2f))));
    public static final Item ASTRALITE_SHOVEL = registerItem("astralite_shovel", new ShovelItem(ModToolMaterials.ASTRALITE, new Item.Settings()
            .attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.ASTRALITE, 2.5f, -3.0f))));
    public static final Item ASTRALITE_HOE = registerItem("astralite_hoe", new HoeItem(ModToolMaterials.ASTRALITE, new Item.Settings()
            .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.ASTRALITE, -4, -3f))));


    public static final Item PINECANNON = registerItem("pinecannon", new ModCustomCannon(new Item.Settings()
            .maxCount(1),


            50,
            10,
            6
            )
    );

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    public static final Item PINECANNON_CROSSBOW = registerItem("pinecannon_crossbow", new ModCustomCrossbow(new Item.Settings()
            .maxCount(1)
            .maxDamage(1)
    ));

    public static final Item DARKELP = registerItem("darkelp", new AliasedBlockItem(ModBlocks.DARKELP, new Item.Settings()));
    public static final Item DUSKWEED = registerItem("duskweed", new AliasedBlockItem(ModBlocks.DUSKWEED, new Item.Settings()));
    public static final Item MURKROOT = registerItem("murkroot", new AliasedBlockItem(ModBlocks.MURKROOT, new Item.Settings()));
    public static final Item SNOWY_GRASS = registerItem("snowy_grass", new AliasedBlockItem(ModBlocks.SNOWY_GRASS, new Item.Settings()));

    public static final Item GOOP_BUCKET = registerItem("goop_bucket",
            new BucketItem(ModFluids.GOOP_STILL, new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Terrium.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Terrium.LOGGER.info("Registering Mod Items for " + Terrium.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(HELLITE_INGOT);
            entries.add(RAW_HELLITE);
            entries.add(HELLITE_NUGGET);

            entries.add(ASTRALITE_SHARD);

            entries.add(PINECONE);
            entries.add(PINECANNON);
            entries.add(PINECONE_JAM);

            entries.add(HELLITE_SWORD);
            entries.add(HELLITE_PICKAXE);
            entries.add(HELLITE_AXE);
            entries.add(HELLITE_SHOVEL);
            entries.add(HELLITE_HOE);

            entries.add(HELLITE_HELMET);
            entries.add(HELLITE_CHESTPLATE);
            entries.add(HELLITE_LEGGINGS);
            entries.add(HELLITE_BOOTS);

            entries.add(DARKELP);
            entries.add(DUSKWEED);
            entries.add(MURKROOT);

            entries.add(GOOP_BUCKET);
        });
    }
}
