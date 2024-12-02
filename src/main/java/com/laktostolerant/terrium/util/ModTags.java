package com.laktostolerant.terrium.util;

import com.laktostolerant.terrium.Terrium;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_NETHERITE_TOOL = createTag("needs_netherite_tool");

        public static final TagKey<Block> NEEDS_HELLITE_TOOL = createTag("needs_hellite_tool");
        public static final TagKey<Block> INCORRECT_FOR_HELLITE_TOOL = createTag("incorrect_for_hellite_tool");

        public static final TagKey<Block> ABYSS_GROWABLES = createTag("abyss_growables");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Terrium.MOD_ID, name));
        }

        public static final TagKey<Block> PURSHALE_ORE_REPLACEABLES = createTag("purshale_ore_replaceables");
    }
;
    public static class Items {


        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Terrium.MOD_ID, name));
        }
    }
}
