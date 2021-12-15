package com.mihir.wdim.entity.custom;

import java.util.EnumSet;
import java.util.function.Predicate;

import com.mihir.wdim.entity.ai.goal.WitherSwellGoal;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class StrangeWitherEntity extends WitherEntity {
	// Copied from source code.
	private static final Predicate<LivingEntity> NOT_UNDEAD = (p_213797_0_) -> {
	      return p_213797_0_.getCreatureAttribute() != CreatureAttribute.UNDEAD && p_213797_0_.attackable();
	   };
	public StrangeWitherEntity(EntityType<? extends WitherEntity> wither, World worldIn) {
		super(wither, worldIn);
	}
	public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
	      return MobEntity.func_233666_p_()
	    		  .createMutableAttribute(Attributes.MAX_HEALTH, 10.0D)
	    		  .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.5D)
	    		  .createMutableAttribute(Attributes.ATTACK_DAMAGE, -10.0D)
	    		  .createMutableAttribute(Attributes.FOLLOW_RANGE, 75.0D);
	   }
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new WitherSwellGoal(this));
	    this.goalSelector.addGoal(1, new StrangeWitherEntity.DoNothingGoal());
	    this.goalSelector.addGoal(3, new RangedAttackGoal(this, 1.0D, 40, 20.0F));
	    this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
	    this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 8.0F));
	    this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
	    this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	    this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MobEntity.class, 0, false, false, NOT_UNDEAD));
	    }
	
	class DoNothingGoal extends Goal {
	      public DoNothingGoal() {
	         this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
	      }

		@Override
		public boolean shouldExecute() {
			// TODO Auto-generated method stub
			return StrangeWitherEntity.this.getInvulTime() > 0;
		}
	}
}
