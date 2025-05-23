package com.merlin204.sw.entity.super_warden;

import com.merlin204.sw.entity.super_warden.ai.SWCombatBehaviors;
import com.merlin204.sw.epicfight.gameassets.animations.SuperWardenAnimation;
import com.merlin204.sw.main.SWMOD;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.MeleeAttack;
import net.minecraft.world.entity.ai.behavior.MoveToTargetSink;
import net.minecraft.world.entity.ai.behavior.OneShot;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import yesman.epicfight.api.animation.*;
import yesman.epicfight.api.animation.types.DynamicAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;
import yesman.epicfight.world.damagesource.StunType;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;
import yesman.epicfight.world.entity.ai.behavior.AnimatedCombatBehavior;
import yesman.epicfight.world.entity.ai.behavior.MoveToTargetSinkStopInaction;
import yesman.epicfight.world.entity.ai.brain.BrainRecomposer;
import yesman.epicfight.world.entity.ai.goal.AnimatedAttackGoal;
import yesman.epicfight.world.entity.ai.goal.TargetChasingGoal;

import java.util.Iterator;
import java.util.Set;



public class SWPatch extends MobPatch<Warden> {
    public SWPatch() {
    }

    @Override
    public void onJoinWorld(Warden warden, EntityJoinLevelEvent event) {
        super.onJoinWorld(warden,event);
        BrainRecomposer.removeBehavior(this.original.getBrain(), Activity.FIGHT, 10, MeleeAttack.class);
        BrainRecomposer.removeBehavior(this.original.getBrain(), Activity.FIGHT, 11, MeleeAttack.class);
        BrainRecomposer.removeBehavior(this.original.getBrain(), Activity.FIGHT, 12, MeleeAttack.class);
    }

    @Override
    protected void initAI() {
        super.initAI();
        BrainRecomposer.removeBehavior(this.original.getBrain(), Activity.FIGHT, 10, MeleeAttack.class);
        BrainRecomposer.removeBehavior(this.original.getBrain(), Activity.FIGHT, 11, MeleeAttack.class);
        BrainRecomposer.removeBehavior(this.original.getBrain(), Activity.FIGHT, 12, MeleeAttack.class);

    }





    @Override
    public void poseTick(DynamicAnimation animation, Pose pose, float elapsedTime, float partialTicks) {

    }

    public static void initAttributes(EntityAttributeModificationEvent event) {
        event.add(EntityType.WARDEN, EpicFightAttributes.MAX_STRIKES.get(), 10);
        event.add(EntityType.WARDEN, EpicFightAttributes.IMPACT.get(), 200);
        event.add(EntityType.WARDEN, EpicFightAttributes.WEIGHT.get(), 800);
        event.add(EntityType.WARDEN, EpicFightAttributes.ARMOR_NEGATION.get(), 30);
    }

    public void initAnimator(Animator animator) {
        super.initAnimator(animator);
        animator.addLivingAnimation(LivingMotions.IDLE, SuperWardenAnimation.SW_IDLE);
        animator.addLivingAnimation(LivingMotions.WALK, SuperWardenAnimation.SW_IDLE);
        animator.addLivingAnimation(LivingMotions.CHASE, SuperWardenAnimation.SW_IDLE);
        animator.addLivingAnimation(LivingMotions.DEATH, SuperWardenAnimation.SW_IDLE);
        animator.addLivingAnimation(LivingMotions.FALL, SuperWardenAnimation.SW_IDLE);
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
