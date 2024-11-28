package com.laktostolerant.terrium.item;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.item.custom.ModCustomCrossbow;
import com.laktostolerant.terrium.item.custom.ModDrinkItem;
import com.laktostolerant.terrium.item.custom.ModCustomBow;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item HELLITE_INGOT = registerItem("hellite_ingot", new Item(new Item.Settings()));
    public static final Item RAW_HELLITE = registerItem("raw_hellite", new Item(new Item.Settings()));
    public static final Item HELLITE_NUGGET = registerItem("hellite_nugget", new Item(new Item.Settings()));

    public static final Item PINECONE = registerItem("pinecone", new Item(new Item.Settings()));
    public static final Item PINECONE_JAM = registerItem("pinecone_jam", new ModDrinkItem(new Item.Settings().food(ModFoodComponents.PINECONE_JAM)));

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


    public static final Item PINECANNON = registerItem("pinecannon", new ModCustomBow(new Item.Settings()
            .maxDamage(1)
            .maxCount(1))
    );

    public static final Item PINECANNON_CROSSBOW = registerItem("pinecannon_crossbow", new ModCustomCrossbow(new Item.Settings()
            .maxCount(1)
            .maxDamage(1)
    ));



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Terrium.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Terrium.LOGGER.info("Registering Mod Items for " + Terrium.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(HELLITE_INGOT);
            entries.add(RAW_HELLITE);
            entries.add(HELLITE_NUGGET);

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
        });
    }
}
