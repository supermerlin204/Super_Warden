package com.merlin204.sw.epicfight.gameassets.animations;


import com.merlin204.sw.main.SWMOD;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.AnimationManager;


@Mod.EventBusSubscriber(modid = SWMOD.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SWAnimations {

    @SubscribeEvent
    public static void registerAnimations(AnimationManager.AnimationRegistryEvent event) {
        event.newBuilder(SWMOD.MOD_ID, (builder)->{
            SuperWardenAnimation.buildVFXAnimations(builder);



        });
    }


}