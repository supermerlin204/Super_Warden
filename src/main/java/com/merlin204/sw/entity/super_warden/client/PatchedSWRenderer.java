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
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.api.model.Armature;
import yesman.epicfight.client.renderer.patched.entity.PatchedLivingEntityRenderer;
import yesman.epicfight.mixin.client.MixinLivingEntityRenderer;

@OnlyIn(Dist.CLIENT)

public class PatchedSWRenderer extends PatchedLivingEntityRenderer<Warden, SWPatch, EmptyEntityModel<Warden>, SWRenderer, SWMesh> {
    public PatchedSWRenderer(EntityRendererProvider.Context context, EntityType<?> entityType) {
        super(context, entityType);
    }

    private static final ResourceLocation BIOLUMINESCENT_LAYER_TEXTURE = ResourceLocation.withDefaultNamespace("textures/entity/warden/warden_bioluminescent_layer.png");
    private static final ResourceLocation HEART_TEXTURE = ResourceLocation.withDefaultNamespace("textures/entity/warden/warden_heart.png");
    private static final ResourceLocation PULSATING_SPOTS_TEXTURE_1 = ResourceLocation.withDefaultNamespace("textures/entity/warden/warden_pulsating_spots_1.png");
    private static final ResourceLocation PULSATING_SPOTS_TEXTURE_2 = ResourceLocation.withDefaultNamespace("textures/entity/warden/warden_pulsating_spots_2.png");


    @Override
    public void render(Warden entity, SWPatch entitypatch, SWRenderer renderer, MultiBufferSource buffer, PoseStack poseStack, int packedLight, float partialTicks) {
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

            mesh.draw(poseStack, buffer, RenderType.entityTranslucentEmissive(BIOLUMINESCENT_LAYER_TEXTURE), packedLight, 1.0F, 1.0F, 1.0F,
                    1.0F, OverlayTexture.NO_OVERLAY, entitypatch.getArmature(), armature.getPoseMatrices());

            mesh.draw(poseStack, buffer, RenderType.entityTranslucentEmissive(PULSATING_SPOTS_TEXTURE_1), packedLight, 1.0F, 1.0F, 1.0F,
                    Math.max(0.0F, Mth.cos(entity.tickCount * 0.045F) * 0.25F), OverlayTexture.NO_OVERLAY, entitypatch.getArmature(), armature.getPoseMatrices());
            mesh.draw(poseStack, buffer, RenderType.entityTranslucentEmissive(PULSATING_SPOTS_TEXTURE_1), packedLight, 1.0F, 1.0F, 1.0F,
                    Math.max(0.0F, Mth.cos(entity.tickCount * 0.045F + 3.1415927F) * 0.25F), OverlayTexture.NO_OVERLAY, entitypatch.getArmature(), armature.getPoseMatrices());

            mesh.draw(poseStack, buffer, renderType, packedLight, 1.0F, 1.0F, 1.0F, isVisibleToPlayer ? 0.15F : 1.0F, this.getOverlayCoord(entity, entitypatch, partialTicks), armature, armature.getPoseMatrices());
            mesh.draw(poseStack, buffer, RenderType.entityTranslucentEmissive(HEART_TEXTURE), packedLight, 1.0F, 1.0F, 1.0F,
                    entity.getHeartAnimation(partialTicks), OverlayTexture.NO_OVERLAY, entitypatch.getArmature(), armature.getPoseMatrices());

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