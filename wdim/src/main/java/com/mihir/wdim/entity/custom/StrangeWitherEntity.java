package com.mihir.wdim.entity.custom;

import java.util.function.Predicate;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class StrangeWitherEntity extends WitherEntity {

	private static final Predicate<LivingEntity> NOT_UNDEAD = (p_213797_0_) -> {
	      return p_213797_0_.getCreatureAttribute() != CreatureAttribute.UNDEAD && p_213797_0_.attackable();
	   };
	   
	 
	public StrangeWitherEntity(EntityType<? extends WitherEntity> type, World worldIn) {
		super(type, worldIn);
		this.setHealth(this.getMaxHealth());
	    this.getNavigator().setCanSwim(true);
	    this.experienceValue = 50;
	}
	public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
	      return MobEntity.func_233666_p_()
	    		  .createMutableAttribute(Attributes.MAX_HEALTH, 150.0D)
	    		  .createMutableAttribute(Attributes.MOVEMENT_SPEED, (double)0.6F)
	    		  .createMutableAttribute(Attributes.ATTACK_DAMAGE, 0.0D)
	    		  .createMutableAttribute(Attributes.FOLLOW_RANGE, 20.0D);
	}
	@Override
	protected void registerGoals() {
	    this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 40, 20.0F));
	    this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
	    this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
	    this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
	    this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MobEntity.class, 0, false, false, NOT_UNDEAD));
	   }
	// Sounds.
	protected SoundEvent getAmbientSound() {
	      return SoundEvents.AMBIENT_CAVE;
	   }

	   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
	      return SoundEvents.ENTITY_ENDERMAN_HURT;
	   }

	   protected SoundEvent getDeathSound() {
	      return SoundEvents.ENTITY_VILLAGER_DEATH;
	   }
}
