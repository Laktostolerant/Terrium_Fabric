package com.laktostolerant.terrium.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RecallItem extends ModDrinkItem {
    public RecallItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && user instanceof ServerPlayerEntity player) {
            var spawnPoint = player.getSpawnPointPosition();
            var dimension = player.getSpawnPointDimension();

            // Check if the spawn point is set, otherwise teleport to the world spawn
            if (spawnPoint != null && dimension != null) {
                player.teleport(player.getServer().getWorld(dimension),
                        spawnPoint.getX() + 0.5,
                        spawnPoint.getY(),
                        spawnPoint.getZ() + 0.5,
                        player.getYaw(),
                        player.getPitch());
            } else {
                var worldSpawn = player.getWorld().getSpawnPos();
                player.teleport(player.getServerWorld(),
                        worldSpawn.getX() + 0.5,
                        worldSpawn.getY(),
                        worldSpawn.getZ() + 0.5,
                        player.getYaw(),
                        player.getPitch());
            }
        }

        // Reduce the stack size or remove the item if it's not stackable
        stack.decrement(1);
        return stack.isEmpty() ? ItemStack.EMPTY : stack;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand); // Prepare to drink
        return TypedActionResult.consume(user.getStackInHand(hand));
    }
}
