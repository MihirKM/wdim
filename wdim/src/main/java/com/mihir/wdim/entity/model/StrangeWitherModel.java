package com.mihir.wdim.entity.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.mihir.wdim.entity.custom.StrangeWitherEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

//Made with Blockbench 4.0.5
//Exported for Minecraft version 1.15 - 1.16 with MCP mappings
//Paste this class into your mod and generate all required imports


public class StrangeWitherModel <T extends StrangeWitherEntity> extends EntityModel<T> {
	private final ModelRenderer upperBodyPart1;
	private final ModelRenderer upperBodyPart2;
	private final ModelRenderer upperBodyPart3;
	private final ModelRenderer head1;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ImmutableList<ModelRenderer> parts;

	public StrangeWitherModel(float pipis) {
		textureWidth = 64;
		textureHeight = 64;

		upperBodyPart1 = new ModelRenderer(this);
		upperBodyPart1.setRotationPoint(0.0F, 24.0F, 0.0F);
		upperBodyPart1.setTextureOffset(0, 16).addBox(-9.5F, -18.1F, 1.5F, 20.0F, 1.0F, 1.0F, pipis, false);

		upperBodyPart2 = new ModelRenderer(this);
		upperBodyPart2.setRotationPoint(-2.0F, -17.1F, -0.5F);
		upperBodyPart1.addChild(upperBodyPart2);
		upperBodyPart2.setTextureOffset(0, 53).addBox(1.5F, 0.0F, 2.0F, 1.0F, 10.0F, 1.0F, pipis, false);
		upperBodyPart2.setTextureOffset(24, 53).addBox(-3.5F, 2.5F, 1.75F, 11.0F, 1.0F, 1.0F, pipis, false);
		upperBodyPart2.setTextureOffset(24, 53).addBox(-3.5F, 5.0F, 1.75F, 11.0F, 1.0F, 1.0F, pipis, false);
		upperBodyPart2.setTextureOffset(24, 53).addBox(-3.5F, 7.5F, 1.75F, 11.0F, 1.0F, 1.0F, pipis, false);

		upperBodyPart3 = new ModelRenderer(this);
		upperBodyPart3.setRotationPoint(1.5F, 10.85F, 2.75F);
		upperBodyPart2.addChild(upperBodyPart3);
		setRotationAngle(upperBodyPart3, 0.7854F, 0.0F, 0.0F);
		upperBodyPart3.setTextureOffset(12, 53).addBox(0.0F, -1.0F, 0.0F, 1.0F, 5.0F, 1.0F, pipis, false);

		head1 = new ModelRenderer(this);
		head1.setRotationPoint(0.0F, -18.0F, 2.0F);
		upperBodyPart1.addChild(head1);
		head1.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, pipis, false);

		head2 = new ModelRenderer(this);
		head2.setRotationPoint(-9.5F, -17.5F, 2.0F);
		upperBodyPart1.addChild(head2);
		head2.setTextureOffset(32, 0).addBox(-6.0F, -2.5F, -2.4F, 6.0F, 6.0F, 6.0F, pipis, true);

		head3 = new ModelRenderer(this);
		head3.setRotationPoint(10.5F, -17.5F, 2.0F);
		upperBodyPart1.addChild(head3);
		head3.setTextureOffset(32, 0).addBox(0.0F, -2.5F, -2.5F, 6.0F, 6.0F, 6.0F, pipis, false);
		Builder<ModelRenderer> builder = ImmutableList.builder();
		this.parts = builder.build();
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	public ImmutableList<ModelRenderer> getParts() {
		return this.parts;
	}
	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = MathHelper.cos(ageInTicks * 0.1F);
		this.upperBodyPart2.rotateAngleX = (0.065F + 0.05F * f) * (float)Math.PI;
		this.upperBodyPart3.setRotationPoint(-2.0F, 6.9F + MathHelper.cos(this.upperBodyPart2.rotateAngleX) * 10.0F, -0.5F + MathHelper.sin(this.upperBodyPart1.rotateAngleX) * 10.0F);
		this.upperBodyPart3.rotateAngleX = (0.265F + 0.1F * f) * (float)Math.PI;
		this.head1.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.head1.rotateAngleX = headPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		upperBodyPart1.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
		
	}
}