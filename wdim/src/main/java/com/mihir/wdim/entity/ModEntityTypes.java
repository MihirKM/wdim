package com.mihir.wdim.entity;

import com.mihir.wdim.WdimMod;
import com.mihir.wdim.entity.custom.MiniCreeperEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
	public static DeferredRegister<EntityType<?>> ENTITY_TYPES
		= DeferredRegister.create(ForgeRegistries.ENTITIES, WdimMod.MOD_ID);
	
	public static final RegistryObject<EntityType<MiniCreeperEntity>> MINI_CREEPER =
			ENTITY_TYPES.register("mini_creeper", 
					() -> EntityType.Builder.create(MiniCreeperEntity::new, EntityClassification.MONSTER)
					.size(1f, 1f)
					.build(new ResourceLocation(WdimMod.MOD_ID, "mini_creeper").toString()));
	
	public static void register(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
	}
}
