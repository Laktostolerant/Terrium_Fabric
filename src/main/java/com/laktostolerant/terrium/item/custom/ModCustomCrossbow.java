package com.laktostolerant.terrium.item.custom;

import com.laktostolerant.terrium.Terrium;
import com.laktostolerant.terrium.item.ModItems;
import com.mojang.brigadier.Message;
import net.minecraft.client.MinecraftClient;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ModCustomCrossbow extends CrossbowItem {
    public static final int MAX_AMMO = 5;
    private static int AMMO_LEFT = MAX_AMMO;
    boolean charged = false;
    boolean loaded = false;
    private static final LoadingSounds DEFAULT_LOADING_SOUNDS;

    static List<ItemStack> projectiles = new ArrayList<>();

    public ModCustomCrossbow(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        MinecraftClient mc = MinecraftClient.getInstance();

        mc.inGameHud.getChatHud().addMessage(Text.literal("TRYING TO USE"));

        ItemStack itemStack = user.getStackInHand(hand);
        ChargedProjectilesComponent chargedProjectilesComponent = (ChargedProjectilesComponent)itemStack.get(DataComponentTypes.CHARGED_PROJECTILES);
        if(projectiles.isEmpty())
        {

            if(!user.getProjectileType(itemStack).isEmpty())
                return TypedActionResult.fail(itemStack);

            if (user instanceof PlayerEntity player) {
                player.sendMessage(Text.literal("trying to reload").formatted(Formatting.RED), true);
            }

            this.charged = false;
            this.loaded = false;
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
        else
        {
            if (user instanceof PlayerEntity player) {
                player.sendMessage(Text.literal("SHOT!").formatted(Formatting.RED), true);
            }

            projectiles.removeLast();
            user.setCurrentHand(hand);
            this.shootAll(world, user, hand, itemStack, 4, 1.0F, (LivingEntity)null);
            return TypedActionResult.success(itemStack);
        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        MinecraftClient mc = MinecraftClient.getInstance();

        mc.inGameHud.getChatHud().addMessage(Text.literal("LET GO"));

        int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
        float f = getPullProgress(i, stack, user);
        if (f >= 1.0F && !isCharged(stack) && loadProjectiles(user, stack)) {

            if (user instanceof PlayerEntity player) {
                player.sendMessage(Text.literal("Your crossbow is fully loaded with pinecones!").formatted(Formatting.GREEN), true);
            }

            LoadingSounds loadingSounds = this.getLoadingSounds(stack);
            loadingSounds.end().ifPresent((sound) -> {
                world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), (SoundEvent)sound.value(), user.getSoundCategory(), 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
            });
        }
        else
        {
            if (user instanceof PlayerEntity player) {
                player.sendMessage(Text.literal("The crossbow did not finish charging.").formatted(Formatting.RED), true);
            }
        }
    }

    private static float getPullProgress(int useTicks, ItemStack stack, LivingEntity user) {
        float f = (float)useTicks / (float)getPullTime(stack, user);
        if (f > 1.0F) {
            f = 1.0F;

            if (user instanceof PlayerEntity player) {
                player.sendMessage(Text.literal("CHARGED AND READY, LET GO").formatted(Formatting.RED), true);
            }
        }

        return f;
    }


    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.CROSSBOW;
    }


    @Override
    public Predicate<ItemStack> getProjectiles() {
        // Only allow "terrium:pinecone" as valid projectile
        return stack -> stack.getItem().equals(ModItems.PINECONE); // Replace `ModItems.PINECONE` with your pinecone item reference
    }

    @Override
    public Predicate<ItemStack> getHeldProjectiles() {
        // This ensures the player can hold and load "terrium:pinecone"
        return this.getProjectiles();
    }

    protected void shoot(LivingEntity shooter, ProjectileEntity projectile, int index, float speed, float divergence, float yaw, @Nullable LivingEntity target) {
        Vector3f vector3f;
        if (target != null) {
            double d = target.getX() - shooter.getX();
            double e = target.getZ() - shooter.getZ();
            double f = Math.sqrt(d * d + e * e);
            double g = target.getBodyY(0.3333333333333333) - projectile.getY() + f * 0.20000000298023224;
            vector3f = calcVelocity(shooter, new Vec3d(d, g, e), yaw);
        } else {
            Vec3d vec3d = shooter.getOppositeRotationVector(1.0F);
            Quaternionf quaternionf = (new Quaternionf()).setAngleAxis((double)(yaw * 0.017453292F), vec3d.x, vec3d.y, vec3d.z);
            Vec3d vec3d2 = shooter.getRotationVec(1.0F);
            vector3f = vec3d2.toVector3f().rotate(quaternionf);
        }

        projectile.setVelocity((double)vector3f.x(), (double)vector3f.y(), (double)vector3f.z(), speed, divergence);
        float h = getSoundPitch(shooter.getRandom(), index);
        shooter.getWorld().playSound((PlayerEntity)null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.ITEM_CROSSBOW_SHOOT, shooter.getSoundCategory(), 1.0F, h);
    }

    private static Vector3f calcVelocity(LivingEntity shooter, Vec3d direction, float yaw) {
        Vector3f vector3f = direction.toVector3f().normalize();
        Vector3f vector3f2 = (new Vector3f(vector3f)).cross(new Vector3f(0.0F, 1.0F, 0.0F));
        if ((double)vector3f2.lengthSquared() <= 1.0E-7) {
            Vec3d vec3d = shooter.getOppositeRotationVector(1.0F);
            vector3f2 = (new Vector3f(vector3f)).cross(vec3d.toVector3f());
        }

        Vector3f vector3f3 = (new Vector3f(vector3f)).rotateAxis(1.5707964F, vector3f2.x, vector3f2.y, vector3f2.z);
        return (new Vector3f(vector3f)).rotateAxis(yaw * 0.017453292F, vector3f3.x, vector3f3.y, vector3f3.z);
    }

    protected ProjectileEntity createArrowEntity(World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
        if (projectileStack.isOf(Items.FIREWORK_ROCKET)) {
            return new FireworkRocketEntity(world, projectileStack, shooter, shooter.getX(), shooter.getEyeY() - 0.15000000596046448, shooter.getZ(), true);
        } else {
            ProjectileEntity projectileEntity = super.createArrowEntity(world, shooter, weaponStack, projectileStack, critical);
            if (projectileEntity instanceof PersistentProjectileEntity) {
                PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity)projectileEntity;
                persistentProjectileEntity.setSound(SoundEvents.ITEM_CROSSBOW_HIT);
            }

            return projectileEntity;
        }
    }

    private boolean loadProjectiles(LivingEntity shooter, ItemStack crossbow) {
        final int MAX_AMMO = 5;



        MinecraftClient mc = MinecraftClient.getInstance();

        mc.inGameHud.getChatHud().addMessage(Text.literal("TRYING TO LOAD AMMO"));
        // Clear the current projectiles list
        projectiles.clear();

        // Collect up to MAX_AMMO pinecones from the inventory
        for (int i = 0; i < MAX_AMMO; i++) {
            ItemStack projectile = ModItems.PINECONE.getDefaultStack(); // Find pinecones in inventory
            if (!projectile.isEmpty()) {
                projectiles.add(projectile.copyWithCount(1)); // Add one pinecone to the projectiles list
                projectile.decrement(1); // Remove the pinecone from the inventory
            } else {
                break; // Stop if no more pinecones are available
            }
        }

        // Update the ChargedProjectilesComponent
        if (!projectiles.isEmpty()) {
            crossbow.set(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.of(projectiles));
            return true;
        }

        return false; // No projectiles loaded
    }

    private static float getSoundPitch(Random random, int index) {
        return index == 0 ? 1.0F : getSoundPitch((index & 1) == 1, random);
    }

    private static float getSoundPitch(boolean flag, Random random) {
        float f = flag ? 0.63F : 0.43F;
        return 1.0F / (random.nextFloat() * 0.5F + 1.8F) + f;
    }

    LoadingSounds getLoadingSounds(ItemStack stack) {
        return (LoadingSounds) EnchantmentHelper.getEffect(stack, EnchantmentEffectComponentTypes.CROSSBOW_CHARGING_SOUNDS).orElse(DEFAULT_LOADING_SOUNDS);
    }

    static {
        DEFAULT_LOADING_SOUNDS = new LoadingSounds(Optional.of(SoundEvents.ITEM_CROSSBOW_LOADING_START), Optional.of(SoundEvents.ITEM_CROSSBOW_LOADING_MIDDLE), Optional.of(SoundEvents.ITEM_CROSSBOW_LOADING_END));
    }
}
