package com.mihir.wdim.entity.model;

import com.google.common.collect.ImmutableList;
import com.mihir.wdim.entity.custom.MiniCreeperEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class MiniCreeperModel <T extends MiniCreeperEntity> extends EntityModel<T> {
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;

	public MiniCreeperModel() {
		textureWidth = 32;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(0, 12).addBox(-3.0F, -10.0F, -1.0F, 6.0F, 7.0F, 3.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(head);
		head.setTextureOffset(0, 0).addBox(-3.0F, -16.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-2.0F, -3.0F, 3.0F);
		body.addChild(leg1);
		leg1.setTextureOffset(24, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(2.0F, -3.0F, 3.0F);
		body.addChild(leg2);
		leg2.setTextureOffset(24, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(-2.0F, -3.0F, -2.0F);
		body.addChild(leg3);
		leg3.setTextureOffset(24, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		leg4 = new ModelRenderer(this);
		leg4.setRotationPoint(2.0F, -3.0F, -2.0F);
		body.addChild(leg4);
		leg4.setTextureOffset(24, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
	}
	public Iterable<ModelRenderer> getParts() {
	      return ImmutableList.of(this.head, this.body, this.leg1, this.leg2, this.leg3, this.leg4);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	      this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
	      this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
	      this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	      this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	      this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	      this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}
	
	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
