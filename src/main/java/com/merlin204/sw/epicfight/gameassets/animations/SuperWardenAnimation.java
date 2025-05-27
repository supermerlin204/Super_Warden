package com.merlin204.sw.epicfight.gameassets.animations;

import com.merlin204.avalon.epicfight.animations.AvalonAttackAnimation;
import com.merlin204.avalon.epicfight.animations.AvalonMovementAnimation;
import com.merlin204.avalon.util.AvalonEventUtils;
import com.merlin204.sw.entity.super_warden.SWArmature;
import com.merlin204.sw.epicfight.gameassets.armature.SWArmatures;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.JointTransform;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.ActionAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.api.collider.MultiOBBCollider;
import yesman.epicfight.api.utils.math.MathUtils;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.QuaternionUtils;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

import static com.merlin204.avalon.util.AvalonAnimationUtils.createSimplePhase;

public class SuperWardenAnimation {

    public static final Collider WARDEN_ARM = new MultiOBBCollider(8, 0.8, 0.8, 0.8, 0, 0.3F, 0);

    public static AnimationManager.AnimationAccessor<StaticAnimation> SW_IDLE;
    public static AnimationManager.AnimationAccessor<StaticAnimation> SW_FALL;
    public static AnimationManager.AnimationAccessor<StaticAnimation> SW_DEATH;
    public static AnimationManager.AnimationAccessor<ActionAnimation> SW_SNIFF;
    public static AnimationManager.AnimationAccessor<ActionAnimation> SW_EMERGE;
    public static AnimationManager.AnimationAccessor<ActionAnimation> SW_DIGGING;
    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SW_SONICBOOM;
    public static AnimationManager.AnimationAccessor<AvalonMovementAnimation> SW_WALK;
    public static AnimationManager.AnimationAccessor<AvalonMovementAnimation> SW_CHASE;

    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SW_ATK_1_1;
    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SW_ATK_1_2;

    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SW_ATK_2_1;
    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SW_ATK_2_2;
    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SW_ATK_2_3;

    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SW_ATK_3_1;
    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SW_ATK_3_2;
    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SW_ATK_3_3;

    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SW_ATK_4_1;
    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SW_ATK_4_2;

    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SW_ATK_SKILL_1;
    public static AnimationManager.AnimationAccessor<AvalonAttackAnimation> SW_ATK_SKILL_2;



    public static void buildAnimations(AnimationManager.AnimationBuilder builder) {
        Armatures.ArmatureAccessor<SWArmature> armature = SWArmatures.SUPER_WARDEN_ARMATURE;

        SW_IDLE = builder.nextAccessor("super_warden_idle", accessor -> new StaticAnimation(0.15F,true, accessor, armature));
        SW_FALL = builder.nextAccessor("super_warden_fall", accessor -> new StaticAnimation(0.15F,true, accessor, armature));
        SW_DEATH = builder.nextAccessor("super_warden_death", accessor -> new StaticAnimation(0.15F,false, accessor, armature));
        SW_SNIFF = builder.nextAccessor("super_warden_sniff", accessor -> new ActionAnimation(0.15F, accessor, armature));
        SW_EMERGE = builder.nextAccessor("super_warden_emerge", accessor -> new ActionAnimation(0.15F, accessor, armature));
        SW_DIGGING = builder.nextAccessor("super_warden_digging", accessor -> new ActionAnimation(0.15F, accessor, armature));

        SW_WALK = builder.nextAccessor("super_warden_walk", accessor -> new AvalonMovementAnimation(0.15F,true, accessor, armature,2.0F));
        SW_CHASE = builder.nextAccessor("super_warden_chase", accessor -> new AvalonMovementAnimation(0.15F,true, accessor, armature,1.8F));

        SW_ATK_1_1 = builder.nextAccessor("super_warden_atk_1_1", accessor -> new AvalonAttackAnimation(0.1F, accessor, armature,0.8F,
                createSimplePhase(30,45,60, InteractionHand.MAIN_HAND,armature.get().arm_down_R,WARDEN_ARM)));

        SW_ATK_1_2 = builder.nextAccessor("super_warden_atk_1_2", accessor -> new AvalonAttackAnimation(0.15F, accessor, armature,0.8F,
                createSimplePhase(35,40,60, InteractionHand.MAIN_HAND,armature.get().arm_down_R,WARDEN_ARM),
                createSimplePhase(35,40,60, InteractionHand.OFF_HAND,armature.get().arm_down_L,WARDEN_ARM))
                .addEvents(AvalonEventUtils.simpleGroundSplit(40,2.5,0,0,0,3,false)));

        SW_ATK_2_1 = builder.nextAccessor("super_warden_atk_2_1", accessor -> new AvalonAttackAnimation(0.1F, accessor, armature,0.8F,
                createSimplePhase(30,45,65, InteractionHand.OFF_HAND,armature.get().arm_down_L,WARDEN_ARM),
                createSimplePhase(35,50,65, InteractionHand.MAIN_HAND,armature.get().arm_down_R,WARDEN_ARM)));

        SW_ATK_2_2 = builder.nextAccessor("super_warden_atk_2_2", accessor -> new AvalonAttackAnimation(0.1F, accessor, armature,0.8F,
                createSimplePhase(35,40,55, InteractionHand.OFF_HAND,armature.get().arm_down_L,WARDEN_ARM),
                createSimplePhase(30,40,55, InteractionHand.MAIN_HAND,armature.get().arm_down_R,WARDEN_ARM)));

        SW_ATK_2_3 = builder.nextAccessor("super_warden_atk_2_3", accessor -> new AvalonAttackAnimation(0.1F, accessor, armature,0.8F,
                createSimplePhase(50,55,70, InteractionHand.OFF_HAND,armature.get().arm_down_L,WARDEN_ARM),
                createSimplePhase(50,55,70, InteractionHand.MAIN_HAND,armature.get().arm_down_R,WARDEN_ARM))
                .addEvents(AvalonEventUtils.simpleGroundSplit(50,0,0,0,0,4,false)));

        SW_ATK_3_1 = builder.nextAccessor("super_warden_atk_3_1", accessor -> new AvalonAttackAnimation(0.1F, accessor, armature,0.8F,
                createSimplePhase(30,40,50, InteractionHand.MAIN_HAND,armature.get().arm_down_R,WARDEN_ARM)));

        SW_ATK_3_2 = builder.nextAccessor("super_warden_atk_3_2", accessor -> new AvalonAttackAnimation(0.1F, accessor, armature,0.8F,
                createSimplePhase(25,35,45, InteractionHand.OFF_HAND,armature.get().arm_down_L,WARDEN_ARM)));

        SW_ATK_3_3 = builder.nextAccessor("super_warden_atk_3_3", accessor -> new AvalonAttackAnimation(0.1F, accessor, armature,0.8F,
                createSimplePhase(43,46,60, InteractionHand.OFF_HAND,armature.get().arm_down_L,WARDEN_ARM),
                createSimplePhase(43,46,60, InteractionHand.MAIN_HAND,armature.get().arm_down_R,WARDEN_ARM))
                .addEvents(AvalonEventUtils.simpleGroundSplit(46,2.1,0,0,0,4,false)));

        SW_ATK_4_1 = builder.nextAccessor("super_warden_atk_4_1", accessor -> new AvalonAttackAnimation(0.1F, accessor, armature,0.8F,
                createSimplePhase(35,45,60, InteractionHand.OFF_HAND,armature.get().arm_down_L,WARDEN_ARM),
                createSimplePhase(35,45,60, InteractionHand.MAIN_HAND,armature.get().arm_down_R,WARDEN_ARM)));

        SW_ATK_4_2 = builder.nextAccessor("super_warden_atk_4_2", accessor -> new AvalonAttackAnimation(0.1F, accessor, armature,0.8F,
                createSimplePhase(25,30,45, InteractionHand.OFF_HAND,armature.get().arm_down_L,WARDEN_ARM),
                createSimplePhase(25,30,45, InteractionHand.MAIN_HAND,armature.get().arm_down_R,WARDEN_ARM))
                .addEvents(AvalonEventUtils.simpleGroundSplit(30,2.5,0,0,0,4,false)));

        SW_ATK_SKILL_1 = builder.nextAccessor("super_warden_skill_1", accessor -> new AvalonAttackAnimation(0.1F, accessor, armature,0.8F,
                createSimplePhase(51,55,65, InteractionHand.OFF_HAND,armature.get().arm_down_L,WARDEN_ARM),
                createSimplePhase(51,55,65, InteractionHand.MAIN_HAND,armature.get().arm_down_R,WARDEN_ARM))
                .addEvents(AvalonEventUtils.simpleGroundSplit(55,1,0,0,0,5,false)));

        SW_ATK_SKILL_2 = builder.nextAccessor("super_warden_skill_2", accessor -> new AvalonAttackAnimation(0.15F, accessor, armature,0.4F,
                createSimplePhase(65,70,85, InteractionHand.OFF_HAND,armature.get().arm_down_L,WARDEN_ARM),
                createSimplePhase(65,70,85, InteractionHand.MAIN_HAND,armature.get().arm_down_R,WARDEN_ARM))
                .addProperty(AnimationProperty.StaticAnimationProperty.POSE_MODIFIER, ATTACK_DIRECTION_MODIFIER)
                .addEvents(AvalonEventUtils.simpleSonicBoom(70,armature.get().head,15)));

        SW_SONICBOOM = builder.nextAccessor("super_warden_sonicboom", accessor -> new AvalonAttackAnimation(0.15F, accessor, armature,0.7F,0.4F,
                        createSimplePhase(40,50,65, InteractionHand.OFF_HAND,armature.get().arm_down_L,WARDEN_ARM),
                        createSimplePhase(40,50,65, InteractionHand.MAIN_HAND,armature.get().arm_down_R,WARDEN_ARM))
                .addProperty(AnimationProperty.StaticAnimationProperty.POSE_MODIFIER, ATTACK_DIRECTION_MODIFIER)
                .addEvents(AvalonEventUtils.simpleSonicBoom(45,armature.get().head,15)));



























    }


    public static final AnimationProperty.PoseModifier ATTACK_DIRECTION_MODIFIER = (self, pose, entitypatch, time, partialTicks) -> {

        float pitch = entitypatch.getAttackDirectionPitch();
        JointTransform chest = pose.orElseEmpty("body");
        chest.frontResult(JointTransform.rotation(QuaternionUtils.XP.rotationDegrees(-pitch)), OpenMatrix4f::mulAsOriginInverse);



    };






}
