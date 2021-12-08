package com.mihir.wdim.entity.render;

import com.mihir.wdim.WdimMod;
import com.mihir.wdim.entity.custom.MiniCreeperEntity;
import com.mihir.wdim.entity.model.MiniCreeperModel;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MiniCreeperRenderer extends MobRenderer<MiniCreeperEntity, MiniCreeperModel<MiniCreeperEntity>>
{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(WdimMod.MOD_ID, "textures/entity/mini_creeper.png");

    public MiniCreeperRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MiniCreeperModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(MiniCreeperEntity entity) {
        return TEXTURE;
    }
}