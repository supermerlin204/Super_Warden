package com.merlin204.sw.entity.super_warden;

import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.model.Armature;

import java.util.Map;

public class SWArmature extends Armature {
    public final Joint arm_down_L;
    public final Joint arm_down_R;
    public final Joint head;




    public SWArmature(String name, int jointNumber, Joint rootJoint, Map<String, Joint> jointMap) {
        super(name, jointNumber, rootJoint, jointMap);
        arm_down_L = getOrLogException(jointMap, "arm_down_L");
        arm_down_R = getOrLogException(jointMap, "arm_down_R");
        head = getOrLogException(jointMap, "head");


    }

}
