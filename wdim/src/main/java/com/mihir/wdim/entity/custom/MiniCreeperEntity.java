package com.mihir.wdim.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.CreeperSwellGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MiniCreeperEntity extends CreeperEntity {

	public MiniCreeperEntity(EntityType<? extends CreeperEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
	      return MobEntity.func_233666_p_()
	    		  .createMutableAttribute(Attributes.MAX_HEALTH, 16.0D)
	    		  .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D)
	    		  .createMutableAttribute(Attributes.ATTACK_DAMAGE, 0.0D)
	    		  .createMutableAttribute(Attributes.FOLLOW_RANGE, 50.0D);
	   }
	@Override
	protected void registerGoals() {
	      this.goalSelector.addGoal(1, new SwimGoal(this));
	      this.goalSelector.addGoal(2, new CreeperSwellGoal(this));
	      this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, OcelotEntity.class, 6.0F, 1.0D, 1.2D));
	      this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, CatEntity.class, 6.0F, 1.0D, 1.2D));
	      this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
	      this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
	      this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
	      this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
	      this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	      this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	   }
	@Override
	protected int getExperiencePoints(PlayerEntity player) {
		return 3 + this.world.rand.nextInt(5);
	}
	
	// Placeholder sounds.
	// TODO: Replace
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_ENDERMAN_DEATH;
	}
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_GHAST_SCREAM;
	}
	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn)
	{
		this.playSound(SoundEvents.BLOCK_ANVIL_PLACE, 1, 0.5F);
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		if (!super.attackEntityAsMob(entityIn)) {
			return false;
		}
		return true;	
	}
}
