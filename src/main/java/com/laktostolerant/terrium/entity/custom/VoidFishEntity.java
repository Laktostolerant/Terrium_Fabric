package com.laktostolerant.terrium.entity.custom;

import net.minecraft.block.TorchBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.text.Text;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.EnumSet;

public class VoidFishEntity extends FlyingEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int swimmingAnimationTimeout = 0;

    public VoidFishEntity(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);

        this.moveControl = new VoidFishMoveControl(this);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new BreakTorchGoal(this));
        this.goalSelector.add(5, new FlyRandomlyGoal(this));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 3)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20 );
    }

    public void travel(Vec3d movementInput) {
        if (this.canMoveVoluntarily()) {
            this.updateVelocity(0.01F, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9));
            if (this.getTarget() == null) {
                this.setVelocity(this.getVelocity().add(0.0, -0.005, 0.0));
            }
        } else {
            super.travel(movementInput);
        }

    }

    private void setupAnimationStates() {
        if (this.swimmingAnimationTimeout <= 0) {
            this.swimmingAnimationTimeout = 40;
            this.idleAnimationState.start(this.age);
        } else {
            --this.swimmingAnimationTimeout;
        }
    }

    protected boolean hasSelfControl() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();

        if(this.getWorld().isClient()) {
            this.setupAnimationStates();
        }
    }

    static class VoidFishMoveControl extends MoveControl {
        private final VoidFishEntity fish;
        private int collisionCheckCooldown;


        public VoidFishMoveControl(VoidFishEntity fish) {
            super(fish);
            this.fish = fish;
        }

        public void tick() {
            MinecraftClient mc = MinecraftClient.getInstance();
            mc.inGameHud.getChatHud().addMessage(Text.literal("my state is " + this.state));

            if (this.state == State.MOVE_TO) {
                if (this.collisionCheckCooldown-- <= 0) {
                    this.collisionCheckCooldown += this.fish.getRandom().nextInt(5) + 2;
                    Vec3d vec3d = new Vec3d(this.targetX - this.fish.getX(), this.targetY - this.fish.getY(), this.targetZ - this.fish.getZ());
                    double d = vec3d.length();
                    vec3d = vec3d.normalize();

                    if (this.willCollide(vec3d, MathHelper.ceil(d))) {
                        this.fish.setVelocity(this.fish.getVelocity().add(vec3d.multiply(0.1)));

                        double yaw = MathHelper.atan2(vec3d.z, vec3d.x) * (180.0 / Math.PI) - 90.0;
                        this.fish.setYaw((float)yaw);
                    }
                    else {
                        this.state = State.WAIT;
                    }
                }
            }
            else if(this.state == State.WAIT) {
                this.fish.setVelocity(this.fish.getVelocity().add(0.0, 0.0001, 0.0));

                if (this.fish.getRandom().nextFloat() < 0.1) {
                    this.state = State.MOVE_TO;
                }
            }
        }


        private boolean willCollide(Vec3d direction, int steps) {
            Box box = this.fish.getBoundingBox();

            for(int i = 1; i < steps; ++i) {
                box = box.offset(direction);
                if (!this.fish.getWorld().isSpaceEmpty(this.fish, box)) {
                    return false;
                }
            }

            return true;
        }
    }

    private static class FlyRandomlyGoal extends Goal {
        private final VoidFishEntity fish;

        public FlyRandomlyGoal(VoidFishEntity fish) {
            this.fish = fish;
            this.setControls(EnumSet.of(Control.MOVE));
        }

        public boolean canStart() {
            MoveControl moveControl = this.fish.getMoveControl();
            if (!moveControl.isMoving()) {
                return true;
            } else {
                double d = moveControl.getTargetX() - this.fish.getX();
                double e = moveControl.getTargetY() - this.fish.getY();
                double f = moveControl.getTargetZ() - this.fish.getZ();
                double g = d * d + e * e + f * f;
                return g < 1.0 || g > 3600.0;
            }
        }

        public boolean shouldContinue() {
            return false;
        }

        public void start() {
            Random random = this.fish.getRandom();
            double d = this.fish.getX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double e = this.fish.getY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double f = this.fish.getZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.fish.getMoveControl().moveTo(d, e, f, 1.0);
        }
    }

    private static class BreakTorchGoal extends Goal {
        private final VoidFishEntity fish;
        private BlockPos targetTorch;

        private long startTime;
        float huntTimer = 0;

        public BreakTorchGoal(VoidFishEntity fish) {
            this.fish = fish;
            this.setControls(EnumSet.of(Control.MOVE));
        }

        @Override
        public boolean canStart() {
            // Random chance to start
            if (Random.create().nextFloat() < 0.01) { // 5% chance each tick
                this.targetTorch = this.findNearbyTorch();
                return this.targetTorch != null;
            }
            return false;
        }

        @Override
        public boolean shouldContinue() {
            return this.targetTorch != null
                    && this.fish.squaredDistanceTo(this.targetTorch.toCenterPos()) > 1;
        }

        @Override
        public void start() {
            if (this.targetTorch != null) {
                Vec3d targetPos = Vec3d.ofCenter(this.targetTorch);
                this.fish.getMoveControl().moveTo(targetPos.x, targetPos.y, targetPos.z, 1.0);
            }
        }

        @Override
        public void stop() {
            this.targetTorch = null;
        }

        @Override
        public void tick() {
            if (this.targetTorch != null) {
                double distance = this.fish.squaredDistanceTo(Vec3d.ofCenter(this.targetTorch));
                if (distance <= 1.5F) {
                    // Break the torch
                    this.fish.getWorld().breakBlock(this.targetTorch, true);
                    this.targetTorch = null;
                }
            }
        }

        private BlockPos findNearbyTorch() {
            BlockPos fishPos = this.fish.getBlockPos();
            int radius = 10; // Search radius

            for (BlockPos pos : BlockPos.iterate(fishPos.add(-radius, -radius, -radius), fishPos.add(radius, radius, radius))) {
                if (this.fish.getWorld().getBlockState(pos).getBlock() instanceof TorchBlock) {
                    return pos;
                }
            }
            return null;
        }

        private long getElapsedTime() {
            // Return the elapsed time in ticks since the hunt started
            return this.fish.getWorld().getTime() - this.startTime;
        }
    }
}
