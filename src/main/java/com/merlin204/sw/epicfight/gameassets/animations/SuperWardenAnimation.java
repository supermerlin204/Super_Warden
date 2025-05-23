package com.merlin204.sw.epicfight.gameassets.animations;

import com.merlin204.sw.entity.super_warden.SWArmature;
import com.merlin204.sw.epicfight.gameassets.armature.SWArmatures;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.api.collider.MultiOBBCollider;
import yesman.epicfight.gameasset.Armatures;

public class SuperWardenAnimation {

    public static final Collider WARDEN_ARM = new MultiOBBCollider(8, 0.5, 0.5, 0.5, 0.0, 0, 0);

    public static AnimationManager.AnimationAccessor<StaticAnimation> SW_IDLE;



    public static void buildAnimations(AnimationManager.AnimationBuilder builder) {
        Armatures.ArmatureAccessor<SWArmature> armature = SWArmatures.SUPER_WARDEN_ARMATURE;

        SW_IDLE = builder.nextAccessor("super_warden_idle", accessor -> new StaticAnimation(0.15F,true, accessor, armature));























    }






}
