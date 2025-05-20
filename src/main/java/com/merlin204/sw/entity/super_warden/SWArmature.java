package com.merlin204.sw.entity.super_warden;

import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.model.Armature;

import java.util.Map;

public class SWArmature extends Armature {
    public final Joint arm_1_L;
    public final Joint arm_3_L;
    public final Joint arm_5_L;
    public final Joint arm_7_L;

    public final Joint arm_1_R;
    public final Joint arm_3_R;
    public final Joint arm_5_R;
    public final Joint arm_7_R;



    public SWArmature(String name, int jointNumber, Joint rootJoint, Map<String, Joint> jointMap) {
        super(name, jointNumber, rootJoint, jointMap);
        arm_1_L = getOrLogException(jointMap, "arm_1_L");
        arm_3_L = getOrLogException(jointMap, "arm_3_L");
        arm_5_L = getOrLogException(jointMap, "arm_5_L");
        arm_7_L = getOrLogException(jointMap, "arm_7_L");

        arm_1_R = getOrLogException(jointMap, "arm_1_R");
        arm_3_R = getOrLogException(jointMap, "arm_3_R");
        arm_5_R = getOrLogException(jointMap, "arm_5_R");
        arm_7_R = getOrLogException(jointMap, "arm_7_R");

    }

}
