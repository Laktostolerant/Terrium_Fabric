package com.laktostolerant.terrium.item.custom;

import com.laktostolerant.terrium.entity.custom.PineconeShardEntity;
import com.laktostolerant.terrium.item.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ModCustomCannon extends RangedWeaponItem {

    public static int RANGE;
    public static float DRAW_SPEED;

    private static final float CHARGE_PROGRESS = 0.1F;
    private static final float LOAD_PROGRESS = 0.3F;
    private static final float DEFAULT_SPEED = 10F;

    private static final CrossbowItem.LoadingSounds DEFAULT_LOADING_SOUNDS;
    static {
        DEFAULT_LOADING_SOUNDS = new CrossbowItem.LoadingSounds(Optional.of(SoundEvents.ITEM_CROSSBOW_LOADING_START), Optional.of(SoundEvents.ITEM_CROSSBOW_LOADING_MIDDLE), Optional.of(SoundEvents.ITEM_CROSSBOW_LOADING_END));
    }


    private int maxAmmo = 6;
    private int remainingShards = 0;
    private boolean isLoaded = false;

    public ModCustomCannon(Settings settings, int range, float drawSpeed, int maxAmmo) {
        super(settings);

        RANGE = range;
        DRAW_SPEED = drawSpeed;
        this.maxAmmo = maxAmmo;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (!user.getProjectileType(itemStack).isEmpty() && world instanceof ServerWorld serverWorld) {
            user.setCurrentHand(hand);

            if (remainingShards > 0) {
                if (world instanceof ServerWorld) {
                    PineconeShardEntity shard = new PineconeShardEntity(world, user);
                    shard.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 4f, 0f);
                    world.spawnEntity(shard);
                }
                remainingShards--;

                return TypedActionResult.consume(itemStack); // Firing when there is ammo left
            } else {
                return TypedActionResult.consume(itemStack); // Out of ammo
            }
        } else {
            return TypedActionResult.fail(itemStack);
        }
    }


    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClient && remainingShards == 0 && !isLoaded) {
            CrossbowItem.LoadingSounds loadingSounds = this.getLoadingSounds(stack);
            float f = (float)(stack.getMaxUseTime(user) - remainingUseTicks) / (float)getPullTime(stack, user);

            if (f >= 0.2F && remainingShards < 1) {
                loadingSounds.start().ifPresent((sound) -> {
                    world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), (SoundEvent)sound.value(), SoundCategory.PLAYERS, 0.5F, 1.0F);
                });
            }

            if (f >= 0.9F && remainingShards < 1) {
                remainingShards = maxAmmo;
                loadingSounds.mid().ifPresent((sound) -> {
                    world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), (SoundEvent)sound.value(), SoundCategory.PLAYERS, 0.5F, 1.0F);
                });
            }
        }

    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
        float f = getPullProgress(i, stack, user);
        isLoaded = remainingShards > 0;

        if (f >= 1.0F && !isCharged(stack) && loadProjectiles(user, stack)) {
            CrossbowItem.LoadingSounds loadingSounds = this.getLoadingSounds(stack);
            loadingSounds.end().ifPresent((sound) -> {
                world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), (SoundEvent)sound.value(), user.getSoundCategory(), 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
            });
        }
    }


    public static boolean isCharged(ItemStack stack) {
        ChargedProjectilesComponent chargedProjectilesComponent = (ChargedProjectilesComponent)stack.getOrDefault(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT);
        return !chargedProjectilesComponent.isEmpty();
    }

    private static boolean loadProjectiles(LivingEntity shooter, ItemStack crossbow) {
        List<ItemStack> list = load(crossbow, shooter.getProjectileType(crossbow), shooter);
        if (!list.isEmpty()) {
            crossbow.set(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.of(list));
            return true;
        } else {
            return false;
        }
    }


    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return getPullTime(stack, user) + 3;
    }
    public static int getPullTime(ItemStack stack, LivingEntity user) {
        //float f = EnchantmentHelper.getCrossbowChargeTime(stack, user, 1.25F);
        return MathHelper.floor(10);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return isLoaded ? UseAction.NONE : UseAction.BOW;
    }

    CrossbowItem.LoadingSounds getLoadingSounds(ItemStack stack) {
        return (CrossbowItem.LoadingSounds)EnchantmentHelper.getEffect(stack, EnchantmentEffectComponentTypes.CROSSBOW_CHARGING_SOUNDS).orElse(DEFAULT_LOADING_SOUNDS);
    }

    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("Loaded Shards: " + this.remainingShards));
    }

    public boolean isUsedOnRelease(ItemStack stack) {
        return stack.isOf(this);
    }
    private static float getPullProgress(int useTicks, ItemStack stack, LivingEntity user) {
        float f = (float)useTicks / (float)getPullTime(stack, user);
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    private static float getSoundPitch(Random random, int index) {
        return index == 0 ? 1.0F : getSoundPitch((index & 1) == 1, random);
    }

    private static float getSoundPitch(boolean flag, Random random) {
        float f = flag ? 0.63F : 0.43F;
        return 1.0F / (random.nextFloat() * 0.5F + 1.8F) + f;
    }

    @Override
    public int getRange() {
        return 50;
    }

    @Override
    protected void shoot(LivingEntity shooter, ProjectileEntity projectile, int index, float speed, float divergence, float yaw, @Nullable LivingEntity target) {

    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return stack -> stack.isOf(ModItems.PINECONE);
    }

    protected ProjectileEntity createArrowEntity(World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
            ProjectileEntity projectileEntity = super.createArrowEntity(world, shooter, weaponStack, projectileStack, critical);
            if (projectileEntity instanceof PersistentProjectileEntity) {
                PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity)projectileEntity;
                persistentProjectileEntity.setSound(SoundEvents.ITEM_CROSSBOW_HIT);
            }

            return projectileEntity;
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
}
