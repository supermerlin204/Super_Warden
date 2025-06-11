package com.merlin204.sw.events;


import com.merlin204.sw.entity.super_warden.client.PatchedSWRenderer;
import com.merlin204.sw.entity.super_warden.client.SWRenderer;
import com.merlin204.sw.main.SWMOD;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import yesman.epicfight.api.client.forgeevent.PatchedRenderersEvent;

@Mod.EventBusSubscriber(modid = SWMOD.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(EntityType.WARDEN, SWRenderer::new);


    }

    @SubscribeEvent
    public static void onPatchedRenderer(PatchedRenderersEvent.Add event){
        event.addPatchedEntityRenderer(EntityType.WARDEN,entityType -> new PatchedSWRenderer(event.getContext(),entityType).initLayerLast(event.getContext(),entityType));
    }







}