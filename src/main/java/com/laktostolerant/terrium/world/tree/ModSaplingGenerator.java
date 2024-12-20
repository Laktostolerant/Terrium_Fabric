package com.laktostolerant.terrium.world.tree;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerator {
    public static final SaplingGenerator ROSE = new SaplingGenerator(Terrium.MOD_ID + ":rose",
            Optional.empty(), Optional.of(ModConfiguredFeatures.ROSE_TREE_KEY), Optional.empty());
}
