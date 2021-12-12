package com.mihir.wdim.entity.render;

import com.mihir.wdim.WdimMod;
import com.mihir.wdim.entity.custom.StrangeWitherEntity;
import com.mihir.wdim.entity.model.StrangeWitherModel;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class StrangeWitherRenderer extends MobRenderer<StrangeWitherEntity, StrangeWitherModel<StrangeWitherEntity>>
{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(WdimMod.MOD_ID, "textures/entity/strange_wither.png");

    public StrangeWitherRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new StrangeWitherModel<>(), 1.0F);
    }

    @Override
    public ResourceLocation getEntityTexture(StrangeWitherEntity entity) {
        return TEXTURE;
    }
    protected void preRenderCallback(StrangeWitherEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        float f = 2.0F;
        int i = entitylivingbaseIn.getInvulTime();
        if (i > 0) {
           f -= ((float)i - partialTickTime) / 220.0F * 0.5F;
        }

        matrixStackIn.scale(f, f, f);
     }
}