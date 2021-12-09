package com.mihir.wdim.events;

import com.mihir.wdim.WdimMod;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = WdimMod.MOD_ID)
public class ModEvents {
	/*
	@SubscribeEvent
	public static void onCommandsRegister(RegisterCommandsEvent event) {
		new SetHomeCommand(event.getDispatcher());
		new ReturnHomeCommand(event.getDispatcher());
		
		ConfigCommand.register(event.getDispatcher());
	}
	*/
}
