package com.laktostolerant.terrium.datagen;

import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        List<ItemConvertible> HELLITE_INGOT_SMELTABLES = List.of(ModItems.RAW_HELLITE, ModBlocks.HELLITE_ORE);

        offerSmelting(recipeExporter, HELLITE_INGOT_SMELTABLES, RecipeCategory.MISC,ModItems.HELLITE_INGOT, 0.4f, 300, "hellite");
        offerBlasting(recipeExporter, HELLITE_INGOT_SMELTABLES, RecipeCategory.MISC,ModItems.HELLITE_INGOT, 0.4f, 150, "hellite");

        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModItems.HELLITE_INGOT, RecipeCategory.MISC, ModItems.HELLITE_NUGGET);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PINECONE_JAM, 1)
                .input(ModItems.PINECONE)
                .input(Items.GLASS_BOTTLE)
                .input(Items.SWEET_BERRIES)
                .criterion(hasItem(ModItems.PINECONE), conditionsFromItem(ModItems.PINECONE))
                .offerTo(recipeExporter);
    }
}
