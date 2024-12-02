package com.laktostolerant.terrium;

import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.item.ModItemGroups;
import com.laktostolerant.terrium.item.ModItems;
import com.laktostolerant.terrium.world.biome.ModOverworldRegion;
import com.laktostolerant.terrium.world.biome.surface.ModMaterialRules;
import com.laktostolerant.terrium.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

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
		ModItemGroups.registerItemGroups();

		ModWorldGeneration.generateModWorldGen();
	}

	@Override
	public void onTerraBlenderInitialized()
	{
		Regions.register(new ModOverworldRegion(Identifier.of(Terrium.MOD_ID, "overworld"), RegionType.OVERWORLD, 4));

		SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Terrium.MOD_ID, ModMaterialRules.makeRules());
	}
}