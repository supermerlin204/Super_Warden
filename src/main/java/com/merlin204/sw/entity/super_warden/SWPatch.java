package com.merlin204.sw.entity.super_warden;

import com.merlin204.sw.entity.super_warden.ai.SWCombatBehaviors;
import com.merlin204.sw.epicfight.gameassets.animations.SuperWardenAnimation;
import com.merlin204.sw.main.SWMOD;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
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
import net.minecraft.world.entity.ai.behavior.warden.SonicBoom;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import yesman.epicfight.api.animation.*;
import yesman.epicfight.api.animation.types.DynamicAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.utils.math.MathUtils;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.Vec3f;
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
import java.util.Map;
import java.util.Set;



public class SWPatch extends MobPatch<Warden> {
    public SWPatch() {
    }

    @Override
    public void onJoinWorld(Warden warden, EntityJoinLevelEvent event) {
        super.onJoinWorld(warden,event);

    }

    @Override
    protected void initAI() {
        super.initAI();

        BrainRecomposer.replaceBehavior(this.original.getBrain(), Activity.FIGHT, 15,new AnimatedCombatBehavior<>(this,SWCombatBehaviors.TYPE1.build(this)),OneShot.class);

        BrainRecomposer.removeBehavior(this.original.getBrain(), Activity.FIGHT, 14, SonicBoom.class);

    }

    @Override
    protected void selectGoalToRemove(Set<Goal> toRemove) {

    }

    @Override
    public void tick(LivingEvent.LivingTickEvent event) {
        super.tick(event);

        if (original.roarAnimationState.isStarted()){
            original.roarAnimationState.stop();
        }

        if (original.emergeAnimationState.isStarted() && !getEntityState().inaction() ){
            this.playAnimationSynchronized(SuperWardenAnimation.SW_EMERGE,0.1F);
            original.emergeAnimationState.stop();
        }

        if (original.diggingAnimationState.isStarted() && !getEntityState().inaction()){
            this.playAnimationSynchronized(SuperWardenAnimation.SW_DIGGING,0.1F);
            original.diggingAnimationState.stop();
        }

        if (original.sniffAnimationState.isStarted() && !getEntityState().inaction() ){
            if (getTarget() == null){
                this.playAnimationSynchronized(SuperWardenAnimation.SW_SNIFF,0.1F);
            }
            original.sniffAnimationState.stop();
        }

    }

    @Override
    public void poseTick(DynamicAnimation animation, Pose pose, float elapsedTime, float partialTicks) {
        if (pose.hasTransform("head") && this.armature.hasJoint("head") && animation.doesHeadRotFollowEntityHead()) {
            float headRotO = this.getOriginal().yBodyRotO - (this.original).yHeadRotO;
            float headRot = this.getOriginal().yBodyRot - (this.original).yHeadRot;
            float partialHeadRot = Mth.wrapDegrees(MathUtils.lerpBetween(headRotO, headRot, partialTicks));
            float xRot = -(this.original).getXRot();
            partialHeadRot = Mth.clamp(partialHeadRot, -60F, 60F);
            xRot = Mth.clamp(xRot, -30F, 30F);
            OpenMatrix4f toOriginalRotation = this.armature.getBindedTransformFor(pose, this.armature.searchJointByName("head")).removeScale().removeTranslation().invert();
            Vec3f xAxis = OpenMatrix4f.transform3v(toOriginalRotation, Vec3f.X_AXIS, (Vec3f)null);
            Vec3f yAxis = OpenMatrix4f.transform3v(toOriginalRotation, Vec3f.Y_AXIS, (Vec3f)null);
            OpenMatrix4f headRotation = OpenMatrix4f.createRotatorDeg(partialHeadRot, yAxis).rotateDeg(xRot, xAxis);
            pose.orElseEmpty("head").frontResult(JointTransform.fromMatrix(headRotation), OpenMatrix4f::mul);
        }
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
        animator.addLivingAnimation(LivingMotions.WALK, SuperWardenAnimation.SW_WALK);
        animator.addLivingAnimation(LivingMotions.CHASE, SuperWardenAnimation.SW_CHASE);
        animator.addLivingAnimation(LivingMotions.DEATH, SuperWardenAnimation.SW_DEATH);
        animator.addLivingAnimation(LivingMotions.FALL, SuperWardenAnimation.SW_FALL);
    }

    public void updateMotion(boolean considerInaction) {
        super.commonAggressiveMobUpdateMotion(considerInaction);
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
