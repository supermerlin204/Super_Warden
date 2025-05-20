package com.merlin204.sw.epicfight.gameassets.animations;

import com.merlin204.avalon.epicfight.animations.AvalonAttackAnimation;
import com.merlin204.avalon.epicfight.animations.AvalonMovementAnimation;
import com.merlin204.avalon.util.AvalonAnimationUtils;
import com.merlin204.avalon.util.AvalonEventUtils;
import com.merlin204.sw.entity.super_warden.SWArmature;
import com.merlin204.sw.epicfight.gameassets.armature.SWArmatures;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.api.collider.MultiOBBCollider;
import yesman.epicfight.gameasset.Armatures;

import java.util.List;
import java.util.function.Supplier;

public class SuperWardenAnimation {

    public static final Collider GOLEM_HAND= new MultiOBBCollider(8, 0.5, 0.5, 0.5, 0.0, 0, 0);

    public static AnimationManager.AnimationAccessor<StaticAnimation> SG_IDLE;
    public static AnimationManager.AnimationAccessor<StaticAnimation> SG_FALL;
    public static AnimationManager.AnimationAccessor<StaticAnimation> SG_DEATH;
    public static AnimationManager.AnimationAccessor<AvalonMovementAnimation> SG_WALK;
    public static AnimationManager.AnimationAccessor<AvalonMovementAnimation> SG_RUN;
    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SG_ATK_1_1;
    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SG_ATK_1_2;

    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SG_ATK_2_1;
    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SG_ATK_2_2;
    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SG_ATK_2_3;

    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SG_ATK_3_1;
    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SG_ATK_3_2;
    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SG_ATK_3_3;

    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SG_SKILL_1;
    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SG_SKILL_2;
    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SG_SKILL_3;




    public static void buildVFXAnimations(AnimationManager.AnimationBuilder builder) {
        Armatures.ArmatureAccessor<SWArmature> armature = SWArmatures.SUPER_WARDEN_ARMATURE;

        SG_IDLE = builder.nextAccessor("super_golem_idle", accessor -> new StaticAnimation(0.15F,true, accessor, armature));

        SG_FALL = builder.nextAccessor("super_golem_fall", accessor -> new StaticAnimation(0.15F,true, accessor, armature));

        SG_DEATH = builder.nextAccessor("super_golem_death", accessor -> new StaticAnimation(0.15F,false,accessor, armature));

        SG_WALK = builder.nextAccessor("super_golem_walk", accessor -> new AvalonMovementAnimation(0.15F,true, accessor, armature,1.8F));

        SG_RUN = builder.nextAccessor("super_golem_run", accessor -> new AvalonMovementAnimation(0.15F,true, accessor, armature,3F));

        SG_ATK_1_1 = builder.nextAccessor("super_golem_atk_1_1", accessor -> new AvalonAttackAnimation(0.1F, accessor, armature,1.5F,
                createSimplePhaseL(37,44,60),createSimplePhaseR(37,44,60)));

        SG_ATK_1_2 = builder.nextAccessor("super_golem_atk_1_2", accessor -> new AvalonAttackAnimation(0.15F, accessor, armature,2F,
                createSimplePhaseL(35,40,55),createSimplePhaseR(35,40,55))
                .addEvents(AvalonEventUtils.simpleGroundSplit(39,2.5,0,0,0,4,true)));

        SG_ATK_2_1 = builder.nextAccessor("super_golem_atk_2_1", accessor -> new AvalonAttackAnimation(0.1F, accessor, armature,1.2F,
                createSimplePhaseL(35,40,55)));

        SG_ATK_2_2 = builder.nextAccessor("super_golem_atk_2_2", accessor -> new AvalonAttackAnimation(0.1F, accessor, armature,1.5F,
                createSimplePhaseL(35,43,60)));

        SG_ATK_2_3 = builder.nextAccessor("super_golem_atk_2_3", accessor -> new AvalonAttackAnimation(0.1F, accessor, armature,1.3F,
                createSimplePhaseL(36,43,70))
                .addEvents(AvalonEventUtils.simpleGroundSplit(42,2.5,0,0,0,2,true)));

        SG_ATK_3_1 = builder.nextAccessor("super_golem_atk_3_1", accessor -> new AvalonAttackAnimation(0.1F, accessor, armature,1.3F,
                createSimplePhaseR(36,43,60)));

        SG_ATK_3_2 = builder.nextAccessor("super_golem_atk_3_2", accessor -> new AvalonAttackAnimation(0.1F, accessor, armature,1.4F,
                createSimplePhaseL(34,40,50)));

        SG_ATK_3_3 = builder.nextAccessor("super_golem_atk_3_3", accessor -> new AvalonAttackAnimation(0.1F, accessor, armature,1.8F,
                createSimplePhaseL(35,40,70),createSimplePhaseR(35,40,70))
                .addEvents(AvalonEventUtils.simpleGroundSplit(39,3,0,0,0,5,true)));

        SG_SKILL_1 = builder.nextAccessor("super_golem_skill_1", accessor -> new AvalonAttackAnimation(0.1F, accessor, armature,2.0F,
                createSimplePhaseL(40,44,65),createSimplePhaseR(40,44,65))
                .addEvents(AvalonEventUtils.simpleGroundSplit(40,0,0,0,0,6,true)));

        SG_SKILL_2 = builder.nextAccessor("super_golem_skill_2", accessor -> new AvalonAttackAnimation(0.1F, accessor, armature,1.8F,
                createSimplePhaseL(45,55,80),createSimplePhaseR(45,55,80))
                .addEvents(AvalonEventUtils.simpleGroundSplit(53,0.5,0,0,0,3,true)));

        SG_SKILL_3 = builder.nextAccessor("super_golem_skill_3", accessor -> new AvalonAttackAnimation(0.1F, accessor, armature,1.8F,
                createSimplePhaseL(65,70,90),createSimplePhaseR(65,70,90))
                .addEvents(AvalonEventUtils.simpleGroundSplit(70,2,0,0,0,3,true)));





















    }


    private static AvalonAttackAnimation.AvalonPhase  createSimplePhaseL(int startFrame, int endFrame,int waitFrame) {
        Supplier<AttackAnimation.JointColliderPair[]> supplierL = () -> {
            Armatures.ArmatureAccessor<SWArmature> armature = SWArmatures.SUPER_WARDEN_ARMATURE;
            List<AttackAnimation.JointColliderPair> atkJoints =
                    List.of(AttackAnimation.JointColliderPair.of(armature.get().arm_1_L,GOLEM_HAND),
                            AttackAnimation.JointColliderPair.of(armature.get().arm_3_L,GOLEM_HAND),
                            AttackAnimation.JointColliderPair.of(armature.get().arm_5_L,GOLEM_HAND),
                            AttackAnimation.JointColliderPair.of(armature.get().arm_7_L,GOLEM_HAND));
            return atkJoints.toArray(new AttackAnimation.JointColliderPair[0]);
        };
        return AvalonAnimationUtils.createSimplePhase(startFrame,endFrame,waitFrame,InteractionHand.OFF_HAND,supplierL.get());
    }
    private static AvalonAttackAnimation.AvalonPhase createSimplePhaseR(int startFrame, int endFrame,int waitFrame) {

        Supplier<AttackAnimation.JointColliderPair[]> supplierR = () -> {
            Armatures.ArmatureAccessor<SWArmature> armature = SWArmatures.SUPER_WARDEN_ARMATURE;
            List<AttackAnimation.JointColliderPair> atkJoints =
                    List.of(AttackAnimation.JointColliderPair.of(armature.get().arm_1_R,GOLEM_HAND),
                            AttackAnimation.JointColliderPair.of(armature.get().arm_3_R,GOLEM_HAND),
                            AttackAnimation.JointColliderPair.of(armature.get().arm_5_R,GOLEM_HAND),
                            AttackAnimation.JointColliderPair.of(armature.get().arm_7_R,GOLEM_HAND));
            return atkJoints.toArray(new AttackAnimation.JointColliderPair[0]);
        };
        return AvalonAnimationUtils.createSimplePhase(startFrame,endFrame,waitFrame,InteractionHand.MAIN_HAND,supplierR.get());
    }







}
