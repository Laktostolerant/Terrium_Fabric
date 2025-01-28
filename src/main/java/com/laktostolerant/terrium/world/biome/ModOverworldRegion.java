package com.laktostolerant.terrium.world.biome;

import net.minecraft.util.Identifier;
import terrablender.api.Region;
import terrablender.api.RegionType;

public class ModOverworldRegion extends Region {

    public ModOverworldRegion(Identifier name, RegionType type, int weight) {
        super(name, type, weight);
    }
}
