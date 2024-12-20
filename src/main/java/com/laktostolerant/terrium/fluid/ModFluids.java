package com.laktostolerant.terrium.fluid;

import com.laktostolerant.terrium.Terrium;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModFluids {
    public  static  final FlowableFluid GOOP_STILL = register("goop_still", new GoopFluid.Still());
    public  static  final FlowableFluid GOOP_FLOWING = register("goop_flowing", new GoopFluid.Flowing());

    private static FlowableFluid register(String name, FlowableFluid fluid) {
        return Registry.register(Registries.FLUID, Identifier.of(Terrium.MOD_ID, name), fluid);
    }

    public static void registerModFluids() {
        Terrium.LOGGER.info("Registering Mod Fluids for " + Terrium.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(ModFluids.GOOP_STILL.getBucketItem());
        });
    }

}
