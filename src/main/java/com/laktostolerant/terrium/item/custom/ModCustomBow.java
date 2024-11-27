package com.laktostolerant.terrium.item.custom;

import com.laktostolerant.terrium.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

public class ModCustomBow extends BowItem {
    private static final int CUSTOM_RANGE = 50; // Increased range
    private static final int CUSTOM_DRAW_SPEED = 10; // Faster draw time

    public ModCustomBow(Settings settings) {
        super(settings);
    }

    public static float getPullProgress(int useTicks) {
        float f = (float) useTicks / 10.0f;
        if ((f = (f * f + f * 2.0f) / 3.0f) > 0.55f) {
            f = 1f;
        }
        return f;
    }

    // 使用をやめたとき、つまりクリックを離したときの処理だ。
    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity playerEntity)) {
            return;
        }

        ItemStack itemStack = playerEntity.getProjectileType(stack);
        if (itemStack.isEmpty()) {
            return;
        }

        int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
        float f = getPullProgress(i);
        if ((double) f < 0.2) {
            return;
        }


        List<ItemStack> list = BowItem.load(stack, itemStack, playerEntity);
        if (world instanceof ServerWorld serverWorld) {
            if (!list.isEmpty()) {
                this.shootAll(serverWorld, playerEntity, playerEntity.getActiveHand(), stack, list, f * 1.6f, 1.0f, f == 1.0f, null);
            }
            if (f < 1) {
                world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0f, 1.0f / (world.getRandom().nextFloat() * 0.4f + 1.2f) + f * 0.5f);
            } else {
                world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0f, 0.8f / (world.getRandom().nextFloat() * 0.4f + 1.2f) + 0.9f);
            }
        }
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return stack -> stack.isOf(ModItems.PINECONE); // Default arrows
    }
}
