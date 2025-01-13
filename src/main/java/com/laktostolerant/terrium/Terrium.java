package com.laktostolerant.terrium;

import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.datagen.ModLootTableModifier;
import com.laktostolerant.terrium.entity.ModEntities;
import com.laktostolerant.terrium.entity.custom.CrawlerEntity;
import com.laktostolerant.terrium.entity.custom.VoidFishEntity;
import com.laktostolerant.terrium.fluid.ModFluids;
import com.laktostolerant.terrium.item.ModItemGroups;
import com.laktostolerant.terrium.item.ModItems;
import com.laktostolerant.terrium.world.ModCustomFeatures;
import com.laktostolerant.terrium.world.ModPlacedFeatures;
import com.laktostolerant.terrium.world.biome.ModBiomeModifiers;
import com.laktostolerant.terrium.world.biome.ModBiomes;
import com.laktostolerant.terrium.world.biome.ModOverworldRegion;
import com.laktostolerant.terrium.world.biome.surface.ModMaterialRules;
import com.laktostolerant.terrium.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.FireBlock;
import net.minecraft.item.MiningToolItem;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import terrablender.api.RegionType;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class Terrium implements ModInitializer, TerraBlenderApi {
	public static final String MOD_ID = "terrium";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModFluids.registerModFluids();
		ModItemGroups.registerItemGroups();
		ModEntities.registerModEntities();

		ModWorldGeneration.generateModWorldGen();

		StrippableBlockRegistry.register(ModBlocks.ROSE_LOG, ModBlocks.STRIPPED_ROSE_LOG);
		StrippableBlockRegistry.register(ModBlocks.ROSE_WOOD, ModBlocks.STRIPPED_ROSE_WOOD);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.ROSE_LOG, 2, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.ROSE_WOOD, 2, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_ROSE_LOG, 2, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_ROSE_WOOD, 2, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.ROSE_PLANKS, 3, 10);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.ROSE_LEAVES, 5, 15);

		ModCustomFeatures.bootstrap();

		FabricDefaultAttributeRegistry.register(ModEntities.VOID_FISH, VoidFishEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CRAWLER, CrawlerEntity.createCrawlerAttributes());

		ModLootTableModifier.register();
	}

	@Override
	public void onTerraBlenderInitialized()
	{
		Regions.register(new ModOverworldRegion(Identifier.of(Terrium.MOD_ID, "overworld"), RegionType.OVERWORLD, 4));

		SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Terrium.MOD_ID, ModMaterialRules.makeRules());

		ModBiomeModifiers.modifyBiomes();
	}
}