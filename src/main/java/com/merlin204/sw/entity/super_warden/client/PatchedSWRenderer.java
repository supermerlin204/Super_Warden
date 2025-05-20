package com.merlin204.sw.entity.super_warden.client;


import com.merlin204.avalon.entity.client.model.EmptyEntityModel;
import com.merlin204.sw.entity.super_warden.SWPatch;
import com.merlin204.sw.epicfight.gameassets.mesh.SWMeshes;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.api.model.Armature;
import yesman.epicfight.client.renderer.patched.entity.PatchedLivingEntityRenderer;
import yesman.epicfight.mixin.MixinLivingEntityRenderer;

@OnlyIn(Dist.CLIENT)

public class PatchedSWRenderer extends PatchedLivingEntityRenderer<PathfinderMob, SWPatch, EmptyEntityModel<PathfinderMob>, SWRenderer, SWMesh> {
    public PatchedSWRenderer(EntityRendererProvider.Context context, EntityType<?> entityType) {
        super(context, entityType);
    }


    @Override
    public void render(PathfinderMob entity, SWPatch entitypatch, SWRenderer renderer, MultiBufferSource buffer, PoseStack poseStack, int packedLight, float partialTicks) {
        Minecraft mc = Minecraft.getInstance();
        MixinLivingEntityRenderer livingEntityRendererAccessor = (MixinLivingEntityRenderer)renderer;
        boolean isVisible = true;
        boolean isVisibleToPlayer = !isVisible && !entity.isInvisibleTo(mc.player);
        boolean isGlowing = mc.shouldEntityAppearGlowing(entity);
        RenderType renderType = livingEntityRendererAccessor.invokeGetRenderType(entity, isVisible, isVisibleToPlayer, isGlowing);
        SWMesh mesh = this.getMeshProvider(entitypatch).get();
        Armature armature = entitypatch.getArmature();

        poseStack.pushPose();
        this.mulPoseStack(poseStack, armature, entity, entitypatch, partialTicks);
        this.setArmaturePose(entitypatch, armature, partialTicks);

        if (renderType != null) {

            mesh.draw(poseStack, buffer, RenderType.entityCutoutNoCull(renderer.getTextureLocation(entity)), packedLight, 1.0F, 1.0F, 1.0F, 0.8F, OverlayTexture.NO_OVERLAY, entitypatch.getArmature(), armature.getPoseMatrices());


                if (entity.getHealth() <= entity.getMaxHealth() * 0.25F){
                    mesh.draw(poseStack, buffer, RenderType.entityCutoutNoCull(ResourceLocation.withDefaultNamespace("textures/entity/iron_golem/iron_golem_crackiness_high.png")), packedLight, 1.0F, 1.0F, 1.0F, 0.8F, OverlayTexture.NO_OVERLAY, entitypatch.getArmature(), armature.getPoseMatrices());
                }else if (entity.getHealth() <= entity.getMaxHealth() * 0.5F){
                    mesh.draw(poseStack, buffer, RenderType.entityCutoutNoCull(ResourceLocation.withDefaultNamespace("textures/entity/iron_golem/iron_golem_crackiness_medium.png")), packedLight, 1.0F, 1.0F, 1.0F, 0.8F, OverlayTexture.NO_OVERLAY, entitypatch.getArmature(), armature.getPoseMatrices());
                }else if (entity.getHealth() <= entity.getMaxHealth() * 0.75F){
                    mesh.draw(poseStack, buffer, RenderType.entityCutoutNoCull(ResourceLocation.withDefaultNamespace("textures/entity/iron_golem/iron_golem_crackiness_low.png")), packedLight, 1.0F, 1.0F, 1.0F, 0.8F, OverlayTexture.NO_OVERLAY, entitypatch.getArmature(), armature.getPoseMatrices());
                }

            this.renderLayer(renderer, entitypatch, entity, armature.getPoseMatrices(), buffer, poseStack, packedLight, partialTicks);

            if (Minecraft.getInstance().getEntityRenderDispatcher().shouldRenderHitBoxes()) {
                entitypatch.getClientAnimator().renderDebuggingInfoForAllLayers(poseStack, buffer, partialTicks);
            }
        }

        poseStack.popPose();
    }




    public AssetAccessor<SWMesh> getDefaultMesh() {
        return SWMeshes.SUPER_WARDEN_MESH;
    }
}