package com.laktostolerant.terrium;

import com.laktostolerant.terrium.datagen.*;
import com.laktostolerant.terrium.world.ModConfiguredFeatures;
import com.laktostolerant.terrium.world.ModCustomFeatures;
import com.laktostolerant.terrium.world.ModPlacedFeatures;
import com.laktostolerant.terrium.world.biome.ModBiomes;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class TerriumDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModWorldGenerator::new);
		pack.addProvider(ModFluidTagProvider::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {

		Terrium.LOGGER.info("Building registries for Terrium");
		ModCustomFeatures.bootstrap();
		//registryBuilder.addRegistry(RegistryKeys.FEATURE, ModCustomFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::boostrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::boostrap);
		registryBuilder.addRegistry(RegistryKeys.BIOME, ModBiomes::boostrap);
	}
}
