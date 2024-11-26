package com.laktostolerant.terrium;

import com.laktostolerant.terrium.block.ModBlocks;
import com.laktostolerant.terrium.item.ModItemGroups;
import com.laktostolerant.terrium.item.ModItems;
import com.laktostolerant.terrium.world.gen.ModOreGeneration;
import com.laktostolerant.terrium.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	}
}