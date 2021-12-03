package com.mihir.wdim.world.biome;

import com.mihir.wdim.WdimMod;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomes {
	public static final DeferredRegister<Biome> BIOMES
		= DeferredRegister.create(ForgeRegistries.BIOMES, WdimMod.MOD_ID);
	
	public static final RegistryObject<Biome> HARDLAND_BIOME = BIOMES.register("hard_land",
			() -> makeHardLandBiome(0.125f, 0.05f));
	
	// Copied from net.minecraft.world.biome.BiomeMaker;
	private static int getSkyColorWithTemperatureModifier(float temperature) {
	      float lvt_1_1_ = temperature / 3.0F;
	      lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
	      return MathHelper.hsvToRGB(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
	   }
	// Also copied
	public static Biome makeHardLandBiome(float depth, float scale) {
	      MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();
	      mobspawninfo$builder.withSpawner(EntityClassification.MONSTER,
	    		  new MobSpawnInfo.Spawners(EntityType.EVOKER, 100, 10, 20));
	      BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(ModConfiguredSurfaceBuilders.HARDLAND);
	      biomegenerationsettings$builder.withStructure(StructureFeatures.NETHER_FOSSIL);
	      biomegenerationsettings$builder.withStructure(StructureFeatures.DESERT_PYRAMID);
	      
	      return (new Biome.Builder()).precipitation(Biome.RainType.NONE).category(Biome.Category.DESERT).depth(depth).scale(scale).temperature(2.0F).downfall(0.0F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(getSkyColorWithTemperatureModifier(2.0F)).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(mobspawninfo$builder.build()).withGenerationSettings(biomegenerationsettings$builder.build()).build();
	   }
	
	public static void register(IEventBus eventBus) {
		BIOMES.register(eventBus);
	}
}