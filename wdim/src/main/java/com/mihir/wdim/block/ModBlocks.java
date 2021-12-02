package com.mihir.wdim.block;

import java.util.function.Supplier;

import com.mihir.wdim.ModItemsGroup;
import com.mihir.wdim.WdimMod;
import com.mihir.wdim.block.custom.TeleporterBlock;
import com.mihir.wdim.item.ModItems;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
	public static final DeferredRegister<Block> BLOCKS
		= DeferredRegister.create(ForgeRegistries.BLOCKS, WdimMod.MOD_ID);
	
	public static final RegistryObject<Block> MYSTERY_BLOCK = registerBlock("mystery_block",
			() -> new Block(AbstractBlock.Properties.create(Material.IRON)
					.harvestLevel(1)
					.harvestTool(ToolType.PICKAXE)
					.hardnessAndResistance(5f)
					.sound(SoundType.METAL)));
	public static final RegistryObject<Block> SPACE_ORE = registerBlock("space_ore",
			() -> new Block(AbstractBlock.Properties.create(Material.ROCK)
					.harvestLevel(1)
					.harvestTool(ToolType.PICKAXE)
					.hardnessAndResistance(5f)
					.sound(SoundType.STONE)));
	public static final RegistryObject<Block> TELEPORTER = registerBlock("teleporter",
			() -> new TeleporterBlock(AbstractBlock.Properties.create(Material.IRON)
					.harvestLevel(1)
					.harvestTool(ToolType.PICKAXE)
					.hardnessAndResistance(6f)
					.sound(SoundType.METAL)));
	
	private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block)
	{
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn);
		return toReturn;
	}
	
	private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block)
	{
		ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
				new Item.Properties().group(ModItemsGroup.WDIM_GROUP)));
	}
	
	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}
}
