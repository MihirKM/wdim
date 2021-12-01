package com.mihir.wdim;

import com.mihir.wdim.item.ModItems;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemsGroup {
	public static final ItemGroup WDIM_GROUP = new ItemGroup("wdimTab") {
		public ItemStack createIcon()
		{
			return new ItemStack(ModItems.SPACE_DUST.get());
		}
	};
}
