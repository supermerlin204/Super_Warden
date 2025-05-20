package com.merlin204.sw.entity.super_warden.client;

import com.merlin204.avalon.entity.client.model.EmptyEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class SWRenderer extends MobRenderer<PathfinderMob, EmptyEntityModel<PathfinderMob>> {

    public SWRenderer(EntityRendererProvider.Context context) {
        super(context, new EmptyEntityModel<>(), 0);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull PathfinderMob Entity) {
        return ResourceLocation.fromNamespaceAndPath("minecraft","textures/entity/iron_golem/iron_golem.png");
    }

}