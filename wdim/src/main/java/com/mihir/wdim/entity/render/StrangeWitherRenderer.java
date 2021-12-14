package com.mihir.wdim.entity.render;

import com.mihir.wdim.WdimMod;
import com.mihir.wdim.entity.custom.StrangeWitherEntity;
import com.mihir.wdim.entity.model.StrangeWitherModel;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StrangeWitherRenderer extends MobRenderer<StrangeWitherEntity, StrangeWitherModel<StrangeWitherEntity>> {
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(WdimMod.MOD_ID, "textures/entity/strange_wither.png");
    
    public StrangeWitherRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new StrangeWitherModel<>(0.0F), 0.7F);
    }
    
    @Override
    public ResourceLocation getEntityTexture(StrangeWitherEntity entity) {
        return TEXTURE;
    }
}
