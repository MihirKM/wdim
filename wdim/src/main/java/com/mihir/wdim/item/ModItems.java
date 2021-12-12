package com.mihir.wdim.item;

import com.mihir.wdim.ModItemsGroup;
import com.mihir.wdim.WdimMod;
import com.mihir.wdim.entity.ModEntityTypes;
import com.mihir.wdim.item.custom.ModSpawnEggItem;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
	public static final DeferredRegister<Item> ITEMS = 
			DeferredRegister.create(ForgeRegistries.ITEMS, WdimMod.MOD_ID);

	public static final RegistryObject<Item> SPACE_DUST = ITEMS.register("space_dust",
			() -> new Item(new Item.Properties().group(ModItemsGroup.WDIM_GROUP)));
	public static final RegistryObject<Item> MYSTERIOUS_INGOT = ITEMS.register("mysterious_ingot",
			() -> new Item(new Item.Properties().group(ModItemsGroup.WDIM_GROUP)));
	public static final RegistryObject<Item> MINI_CREEPER_SPAWN_EGG = ITEMS.register("mini_creeper_spawn_egg",
			() -> new ModSpawnEggItem(ModEntityTypes.MINI_CREEPER, 0xFFFFFF, 0x00FF00,
					new Item.Properties().maxStackSize(64).group(ModItemsGroup.WDIM_GROUP)));
	
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
