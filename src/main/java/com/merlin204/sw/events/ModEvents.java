package com.merlin204.sw.events;


import com.merlin204.sw.entity.super_warden.SWPatch;
import com.merlin204.sw.epicfight.gameassets.armature.SWArmatures;
import com.merlin204.sw.main.SWMOD;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import yesman.epicfight.api.forgeevent.EntityPatchRegistryEvent;

@Mod.EventBusSubscriber(modid = SWMOD.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {


    @SubscribeEvent
    public static void setPatch(EntityPatchRegistryEvent event) {
        event.getTypeEntry().put(EntityType.WARDEN,(entity -> SWPatch::new));

    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(SWArmatures::registerArmatures);

    }






}