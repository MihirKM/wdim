package com.mihir.wdim.events;

import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.mihir.wdim.WdimMod;
import com.mihir.wdim.entity.ModEntityTypes;
import com.mihir.wdim.entity.custom.MiniCreeperEntity;
import com.mihir.wdim.entity.custom.StrangeWitherEntity;
import com.mihir.wdim.item.custom.ModSpawnEggItem;

@Mod.EventBusSubscriber(modid = WdimMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
	@SubscribeEvent
	public static void addEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(ModEntityTypes.MINI_CREEPER.get(), MiniCreeperEntity.setCustomAttributes().create());
		event.put(ModEntityTypes.STRANGE_WITHER.get(), StrangeWitherEntity.setCustomAttributes().create());
	}
	
	@SubscribeEvent
	public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
		ModSpawnEggItem.initSpawnEggs();
	}
}
