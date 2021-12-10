package com.mihir.wdim.world.gen;

import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.EntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class ModEntityGeneration {
	public static void onEntitySpawn(final BiomeLoadingEvent event) {
		// Ultimate Bepis
	}
	@SuppressWarnings("unused")
	private static void addEntityToAllBiomesExceptThese(BiomeLoadingEvent event, EntityType<?> type, 
			int weight, int minCount, int maxCount, RegistryKey<Biome>... biomes) {
				// Goes through each entry in the biomes and sees if it matches the current biome we are loading
			boolean isBiomeSelected = Arrays.stream(biomes).map(RegistryKey::getLocation)
					.map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));

			if(!isBiomeSelected) {
			addEntityToAllBiomes(event.getSpawns(), type, weight, minCount, maxCount);
			}
	}
	@SuppressWarnings("unused")
	@SafeVarargs
	private static void addEntityToSpecificBiomes(BiomeLoadingEvent event, EntityType<?> type,
			int weight, int minCount, int maxCount, RegistryKey<Biome>... biomes) {
		// Goes through each entry in the biomes and sees if it matches the current biome we are loading
		boolean isBiomeSelected = Arrays.stream(biomes).map(RegistryKey::getLocation)
				.map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));
		
		if(isBiomeSelected) {
			addEntityToAllBiomes(event.getSpawns(), type, weight, minCount, maxCount);
			}
		}
	private static void addEntityToAllBiomes(MobSpawnInfoBuilder spawns, EntityType<?> type,
			int weight, int minCount, int maxCount) {
		List<MobSpawnInfo.Spawners> base = spawns.getSpawner(type.getClassification());
		base.add(new MobSpawnInfo.Spawners(type,weight, minCount, maxCount));
		}
}