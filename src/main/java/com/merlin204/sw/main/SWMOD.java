package com.merlin204.sw.main;


import com.merlin204.sw.entity.SWEntities;

import com.merlin204.sw.events.ForgeModEvents;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(SWMOD.MOD_ID)
public class SWMOD {

    public static final String MOD_ID = "super_warden";
    public static final Logger LOGGER = LogUtils.getLogger();



    public SWMOD(FMLJavaModLoadingContext context) {
        IEventBus bus = context.getModEventBus();
        bus.addListener(this::client);
        SWEntities.ENTITIES.register(bus);



    }



    private void client(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new ForgeModEvents());
    };




}
