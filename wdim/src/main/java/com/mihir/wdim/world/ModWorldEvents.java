package com.mihir.wdim.world;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.mihir.wdim.WdimMod;
import com.mihir.wdim.world.gen.ModOreGeneration;

@Mod.EventBusSubscriber(modid = WdimMod.MOD_ID)
public class ModWorldEvents {
	
	@SubscribeEvent
	public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
		ModOreGeneration.generateOres(event);
	}
}
