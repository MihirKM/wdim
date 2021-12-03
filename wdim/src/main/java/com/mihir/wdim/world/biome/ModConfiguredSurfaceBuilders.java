package com.mihir.wdim.world.biome;

import com.mihir.wdim.WdimMod;

import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class ModConfiguredSurfaceBuilders {
	
	public static ConfiguredSurfaceBuilder<?> HARDLAND = register("hard_land",
			SurfaceBuilder.DEFAULT.func_242929_a(new SurfaceBuilderConfig(
					Blocks.COBWEB.getDefaultState(),
					Blocks.OBSIDIAN.getDefaultState(),
					Blocks.RED_SAND.getDefaultState()
					)));
	
	private static <SC extends ISurfaceBuilderConfig>ConfiguredSurfaceBuilder<SC> register(String name,
			ConfiguredSurfaceBuilder<SC> csb) {
		
		return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER,
				new ResourceLocation(WdimMod.MOD_ID, name), csb);
	}
	
}
