package com.merlin204.sw.entity.super_warden;

import com.merlin204.sw.entity.super_warden.ai.SWCombatBehaviors;
import com.merlin204.sw.epicfight.gameassets.animations.SuperWardenAnimation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import yesman.epicfight.api.animation.*;
import yesman.epicfight.api.animation.types.DynamicAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.entitypatch.Factions;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;
import yesman.epicfight.world.damagesource.StunType;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;
import yesman.epicfight.world.entity.ai.goal.AnimatedAttackGoal;
import yesman.epicfight.world.entity.ai.goal.TargetChasingGoal;

import java.util.Iterator;
import java.util.Set;

public class SWPatch extends MobPatch<PathfinderMob> {
    public SWPatch() {

    }

    protected void initAI() {
        super.initAI();
        this.original.goalSelector.addGoal(0, new AnimatedAttackGoal<>(this, SWCombatBehaviors.TYPE1.build(this)));

        (this.original).goalSelector.addGoal(1, new TargetChasingGoal(this, this.original, 1.0, false));
    }

    protected void selectGoalToRemove(Set<Goal> toRemove) {
        super.selectGoalToRemove(toRemove);
        Iterator var2 = (this.original).goalSelector.getAvailableGoals().iterator();

        while(var2.hasNext()) {
            WrappedGoal wrappedGoal = (WrappedGoal)var2.next();
            Goal goal = wrappedGoal.getGoal();
            if (goal instanceof MoveTowardsTargetGoal) {
                toRemove.add(goal);
            }
        }

    }


    @Override
    public void poseTick(DynamicAnimation animation, Pose pose, float elapsedTime, float partialTicks) {
    }

    public static void initAttributes(EntityAttributeModificationEvent event) {
        event.add(EntityType.WARDEN,  EpicFightAttributes.MAX_STRIKES.get(), 10);
        event.add(EntityType.WARDEN, EpicFightAttributes.IMPACT.get(), 200);
        event.add(EntityType.WARDEN, EpicFightAttributes.WEIGHT.get(), 800);
    }

    public void initAnimator(Animator animator) {
        super.initAnimator(animator);
        animator.addLivingAnimation(LivingMotions.IDLE, SuperWardenAnimation.SG_IDLE);
        animator.addLivingAnimation(LivingMotions.WALK, SuperWardenAnimation.SG_WALK);
        animator.addLivingAnimation(LivingMotions.CHASE, SuperWardenAnimation.SG_RUN);
        animator.addLivingAnimation(LivingMotions.DEATH, SuperWardenAnimation.SG_DEATH);
        animator.addLivingAnimation(LivingMotions.FALL, SuperWardenAnimation.SG_DEATH);
    }

    public void updateMotion(boolean considerInaction) {
        super.commonMobUpdateMotion(considerInaction);
    }

    public SoundEvent getWeaponHitSound(InteractionHand hand) {
        return  EpicFightSounds.BLUNT_HIT_HARD.get();
    }

    public SoundEvent getSwingSound(InteractionHand hand) {
        return EpicFightSounds.WHOOSH_BIG.get();
    }

    public AnimationManager.AnimationAccessor<? extends StaticAnimation> getHitAnimation(StunType stunType) {
        return null;
    }
}
