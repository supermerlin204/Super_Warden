package com.merlin204.sw.epicfight.gameassets.armature;

import com.merlin204.sw.entity.super_warden.SWArmature;
import com.merlin204.sw.main.SWMOD;
import net.minecraft.world.entity.EntityType;
import yesman.epicfight.gameasset.Armatures;

public class SWArmatures {
    public static Armatures.ArmatureAccessor<SWArmature> SUPER_WARDEN_ARMATURE = Armatures.ArmatureAccessor.create(SWMOD.MOD_ID, "entity/super_warden", SWArmature::new);


    public static void registerArmatures(){
        Armatures.registerEntityTypeArmature(EntityType.WARDEN, SUPER_WARDEN_ARMATURE);


    }

}