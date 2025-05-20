package com.merlin204.sw.epicfight.gameassets.mesh;

import com.merlin204.sw.entity.super_warden.client.SWMesh;
import com.merlin204.sw.main.SWMOD;
import yesman.epicfight.api.client.model.Meshes;

public class SWMeshes {

    public static final Meshes.MeshAccessor<SWMesh> SUPER_WARDEN_MESH = Meshes.MeshAccessor.create(SWMOD.MOD_ID, "entity/super_warden", (jsonModelLoader) -> jsonModelLoader.loadSkinnedMesh(SWMesh::new));

}
