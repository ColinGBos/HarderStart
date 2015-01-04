package com.vapourdrive.harderstart.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class HS_WorldGenHandler implements IWorldGenerator
{
	private WorldGenerator FlintRockGenerator;
	private WorldGenerator GeodeGenerator;

	public HS_WorldGenHandler()
	{
		GameRegistry.registerWorldGenerator(this, 0);

		FlintRockGenerator = new FlintRockGenerator();
		GeodeGenerator = new GeodeGenerator();

	}

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		int xChunk = chunkX * 16 + rand.nextInt(16);
		int zChunk = chunkZ * 16 + rand.nextInt(16);
		int chx = xChunk + rand.nextInt(16);
		int chz = zChunk + rand.nextInt(16);

		BiomeGenBase Biome = world.getBiomeGenForCoords(chx, chz);

		if (rand.nextInt(20 - biomeMultiplyer(Biome)) == 0)
		{
			FlintRockGenerator.generate(world, rand, chx, 0, chz);
		}
		
		if(rand.nextInt(50) == 0)
		{
			GeodeGenerator.generate(world, rand, chx, 10, chz);
		}

	}

	/**
	 * returns different spawn rates for different biomes
	 * TODO find a cleaner method of implementation, poor case-by-case
	 * @param biome
	 * @return
	 */
	public int biomeMultiplyer(BiomeGenBase biome)
	{
		if (BiomeDictionary.isBiomeOfType(biome, Type.SWAMP))
		{
			return 16;
		}
		if (BiomeDictionary.isBiomeOfType(biome, Type.CONIFEROUS))
		{
			return 10;
		}
		if (BiomeDictionary.isBiomeOfType(biome, Type.PLAINS))
		{
			return 6;
		}
		else
		{
			return 0;
		}
	}

}
