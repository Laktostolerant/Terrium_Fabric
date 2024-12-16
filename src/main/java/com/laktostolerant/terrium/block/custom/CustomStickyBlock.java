package com.laktostolerant.terrium.block.custom;

import net.minecraft.block.SlimeBlock;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;

public class CustomStickyBlock extends SlimeBlock {
    public CustomStickyBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onEntityLand(BlockView world, Entity entity) {
        if (entity.bypassesLandingEffects()) {
            super.onEntityLand(world, entity);
        }
    }
}
