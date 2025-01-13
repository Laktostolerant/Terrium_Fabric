package com.laktostolerant.terrium.entity.custom;

import com.laktostolerant.terrium.entity.ModEntities;
import com.laktostolerant.terrium.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class DynamiteEntity extends ThrownItemEntity {
    public DynamiteEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public int k = 120;

    @Override
    protected Item getDefaultItem() {
        return ModItems.DYNAMITE;
    }

    public DynamiteEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.DYNAMITE, livingEntity, world);
    }


    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        this.Explode();
        entity.damage(this.getDamageSources().explosion(null,
                null), 12);
        super.onEntityHit(entityHitResult);
    }


    private void Explode() {
        if (!this.getWorld().isClient) {
            this.getWorld().createExplosion(this, this.getX(), this.getY(),
                    this.getZ(), 2, World.ExplosionSourceType.TNT);
            this.discard();
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.Explode();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getWorld().isClient) {
            this.getWorld().addParticle(ParticleTypes.SMALL_FLAME, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            if(k == 0) {
                this.Explode();
            } else {
                k --;
            }
        }
    }

    @Override
    public boolean isPushedByFluids() {
        return false;
    }
}
