package com.mihir.wdim.world.dimension;

import com.mihir.wdim.WdimMod;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ModDimensions {
	public static RegistryKey<World> WDim = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, 
			new ResourceLocation(WdimMod.MOD_ID, "wdim"));
}
