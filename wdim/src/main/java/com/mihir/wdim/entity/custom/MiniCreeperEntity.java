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
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("unused")
public class MiniCreeperEntity extends CreeperEntity {
	private int timeSinceIgnited;
	private int fuseTime = 40;
	private int explosionRadius = 1;
	private int lastActiveTime;

	public MiniCreeperEntity(EntityType<? extends CreeperEntity> type, World worldIn) {
		super(type, worldIn);
	}
	public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
	      return MobEntity.func_233666_p_()
	    		  .createMutableAttribute(Attributes.MAX_HEALTH, 10.0D)
	    		  .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4D)
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
		return SoundEvents.BLOCK_GLASS_BREAK;
	}
	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn)
	{
		this.playSound(SoundEvents.BLOCK_GRASS_BREAK, 0.5F, 0.5F);
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		if (!super.attackEntityAsMob(entityIn)) {
			return false;
		}
		return true;	
	}
	// Tick
	// Crash workaround
	/*
	@Override
	public void tick() {
	      if (this.isAlive()) {
	         if (this.hasIgnited()) {
	            this.setCreeperState(1);
	         }

	         int i = this.getCreeperState();
	         if (i > 0 && this.timeSinceIgnited == 0) {
	            this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
	         }

	         this.timeSinceIgnited += i;
	         if (this.timeSinceIgnited < 0) {
	            this.timeSinceIgnited = 0;
	         }

	         if (this.timeSinceIgnited >= this.fuseTime) {
	            this.timeSinceIgnited = this.fuseTime;
	            this.explode();
	         }
	      }
	      super.tick();
	}
	// Flint and Steel
	@Override
	protected ActionResultType getEntityInteractionResult(PlayerEntity playerIn, Hand hand) {
	      ItemStack itemstack = playerIn.getHeldItem(hand);
	      if (itemstack.getItem() == Items.FLINT_AND_STEEL) {
	         this.world.playSound(playerIn, this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ITEM_FLINTANDSTEEL_USE, this.getSoundCategory(), 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
	         if (!this.world.isRemote) {
	            this.ignite();
	            itemstack.damageItem(1, playerIn, (player) -> {
	               player.sendBreakAnimation(hand);
	            });
	         }

	         return ActionResultType.func_233537_a_(this.world.isRemote);
	      } else {
	         return super.getEntityInteractionResult(playerIn, hand);
	      }
	   }
	// Explosion
	private void explode() {
	      if (!this.world.isRemote) {
	         Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
	         float f = this.isCharged() ? 2.0F : 1.0F;
	         this.dead = true;
	         this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), (float)this.explosionRadius * f, explosion$mode);
	         this.remove();
	      }

	   }
	
	@Override
	public boolean hasIgnited() {
		return this.dataManager.get(IGNITED);
	}
	@Override
	public void ignite() {
		this.dataManager.set(IGNITED, true);
	}
	// COPIED FROM MC CODE
	   @OnlyIn(Dist.CLIENT)
	   public float getCreeperFlashIntensity(float partialTicks) {
	      return MathHelper.lerp(partialTicks, (float)this.lastActiveTime, (float)this.timeSinceIgnited) / (float)(this.fuseTime - 2);
	   }
	      return this.dataManager.get(STATE);
	   }
	   public void setCreeperState(int state) {
	      this.dataManager.set(STATE, state);
	   }
	   */
}
