package com.merlin204.sw.entity;


import com.merlin204.sw.main.SWMOD;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SWEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SWMOD.MOD_ID);
//    public static final RegistryObject<EntityType<BladeWaveEntity>> BLADEWAVE = register("blade_wave",
//            EntityType.Builder.<BladeWaveEntity>of(BladeWaveEntity::new, MobCategory.MISC).sized(0, 0).clientTrackingRange(64).updateInterval(1).noSave());
//

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String name, EntityType.Builder<T> entityTypeBuilder) {
        return ENTITIES.register(name, () -> entityTypeBuilder.build(ResourceLocation.fromNamespaceAndPath(SWMOD.MOD_ID, name).toString()));
    }

}
