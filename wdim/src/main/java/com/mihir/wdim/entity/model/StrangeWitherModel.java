package com.mihir.wdim.entity.model;

import java.util.Arrays;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.mihir.wdim.entity.custom.StrangeWitherEntity;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StrangeWitherModel<T extends StrangeWitherEntity> extends SegmentedModel<T> {
	private final ModelRenderer[] upperBodyParts;
	private final ModelRenderer[] heads;
	private final ImmutableList<ModelRenderer> parts;

	public StrangeWitherModel(float something) {
		textureWidth = 64;
		textureHeight = 64;
		
		this.upperBodyParts = new ModelRenderer[3];

		this.upperBodyParts[0] = new ModelRenderer(this);
		this.upperBodyParts[0].setRotationPoint(0.0F, 24.0F, 0.0F);
		this.upperBodyParts[0].setTextureOffset(0, 16).addBox(-9.5F, -18.1F, 1.5F, 20.0F, 1.0F, 1.0F, something, false);

		this.upperBodyParts[1] = new ModelRenderer(this);
		this.upperBodyParts[1].setRotationPoint(-2.0F, -17.1F, -0.5F);
		this.upperBodyParts[0].addChild(this.upperBodyParts[1]);
		this.upperBodyParts[1].setTextureOffset(0, 53).addBox(1.5F, 0.0F, 2.0F, 1.0F, 10.0F, 1.0F, something, false);
		this.upperBodyParts[1].setTextureOffset(24, 53).addBox(-3.5F, 2.5F, 1.75F, 11.0F, 1.0F, 1.0F, something, false);
		this.upperBodyParts[1].setTextureOffset(24, 53).addBox(-3.5F, 5.0F, 1.75F, 11.0F, 1.0F, 1.0F, something, false);
		this.upperBodyParts[1].setTextureOffset(24, 53).addBox(-3.5F, 7.5F, 1.75F, 11.0F, 1.0F, 1.0F, something, false);

		this.upperBodyParts[2] = new ModelRenderer(this);
		this.upperBodyParts[2].setRotationPoint(1.5F, 10.85F, 2.75F);
		this.upperBodyParts[1].addChild(this.upperBodyParts[2]);
		setRotationAngle(this.upperBodyParts[2], 0.7854F, 0.0F, 0.0F);
		this.upperBodyParts[2].setTextureOffset(12, 53).addBox(0.0F, -1.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);

		this.heads = new ModelRenderer[3];
		this.heads[0].setRotationPoint(0.0F, -18.0F, 2.0F);
		this.upperBodyParts[0].addChild(this.heads[0]);
		this.heads[0].setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, something, false);

		this.heads[1] = new ModelRenderer(this);
		this.heads[1].setRotationPoint(-9.5F, -17.5F, 2.0F);
		this.upperBodyParts[0].addChild(this.heads[1]);
		this.heads[1].setTextureOffset(32, 0).addBox(-6.0F, -2.5F, -2.4F, 6.0F, 6.0F, 6.0F, something, true);

		this.heads[2] = new ModelRenderer(this);
		this.heads[2].setRotationPoint(10.5F, -17.5F, 2.0F);
		this.upperBodyParts[0].addChild(this.heads[2]);
		this.heads[2].setTextureOffset(32, 0).addBox(0.0F, -2.5F, -2.5F, 6.0F, 6.0F, 6.0F, something, false);
		Builder<ModelRenderer> builder = ImmutableList.builder();
	    builder.addAll(Arrays.asList(this.heads));
	    builder.addAll(Arrays.asList(this.upperBodyParts));
		this.parts = builder.build();
	}
	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	      float f = MathHelper.cos(ageInTicks * 0.1F);
	      this.upperBodyParts[0].rotateAngleX = (0.065F + 0.05F * f) * (float)Math.PI;
	      this.upperBodyParts[1].setRotationPoint(-2.0F, 6.9F + MathHelper.cos(this.upperBodyParts[0].rotateAngleX) * 10.0F, -0.5F + MathHelper.sin(this.upperBodyParts[0].rotateAngleX) * 10.0F);
	      this.upperBodyParts[2].rotateAngleX = (0.265F + 0.1F * f) * (float)Math.PI;
	      this.heads[0].rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
	      this.heads[0].rotateAngleX = headPitch * ((float)Math.PI / 180F);
	   }
	/*
	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		this.upperBodyParts[0].render(matrixStack, buffer, packedLight, packedOverlay);
	}
	*/
	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
	      for(int i = 1; i < 3; ++i) {
	         this.heads[i].rotateAngleY = (entityIn.getHeadYRotation(i - 1) - entityIn.renderYawOffset) * ((float)Math.PI / 180F);
	         this.heads[i].rotateAngleX = entityIn.getHeadXRotation(i - 1) * ((float)Math.PI / 180F);
	      }

	   }
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	@Override
	public ImmutableList<ModelRenderer> getParts() {
	      return this.parts;
	   }
}