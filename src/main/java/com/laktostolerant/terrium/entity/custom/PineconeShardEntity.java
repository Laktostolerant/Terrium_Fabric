package com.laktostolerant.terrium.entity.custom;

import com.laktostolerant.terrium.entity.ModEntities;
import com.laktostolerant.terrium.item.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.Vector2f;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.EntityEffectParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.Potion;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class PineconeShardEntity extends PersistentProjectileEntity {
    private float rotation;

    public PineconeShardEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public PineconeShardEntity(World world, PlayerEntity player) {
        super(ModEntities.PINECONE_SHARD, player, world, new ItemStack(ModItems.PINECONE), null);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return null;
    }

    public boolean isGrounded() {
        return inGround;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        //super.onEntityHit(entityHitResult);

        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 2);
    }

    protected void onCollision(HitResult hitResult) {
        if(hitResult.getType() == HitResult.Type.MISS)
            return;

        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().addParticle(ParticleTypes.CRIT,
                    hitResult.getPos().getX(), hitResult.getPos().getY() + 1F, hitResult.getPos().getZ(),
                    0.0, 0.0, 0.0); // Add velocity to particles if needed

            this.getWorld().sendEntityStatus(this, (byte)3);
            this.discard();
        }
    }
}
