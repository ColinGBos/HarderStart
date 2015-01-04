package com.vapourdrive.harderstart.world;

import java.util.Random;

import com.vapourdrive.harderstart.blocks.HS_Blocks;
import com.vapourdrive.harderstart.items.GemRef;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class GeodeGenerator extends WorldGenerator
{

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		int size = world.rand.nextInt(3);
		generateShell(world, rand, x, y, z, size);
		return false;
	}

	public void generateShell(World world, Random rand, int x, int y, int z, int size)
	{
		int radius = (5 + size);
		int m = (((1 + size) * (1 + size)) * 3);
		int o = (((2 + size) * (2 + size)) * 3);
		int p = (2 + size);

		for (int i = -radius; i < radius; i++)
		{
			for (int j = -(radius - 1); j < (radius - 1); j++)
			{
				for (int k = -radius; k < radius; k++)
				{
					int distVal = (i * i + j * j + k * k);

					if (distVal > m && distVal < o && (j >= -p && j <= p))
					{
						if (world.isAirBlock(x + i, y + j, z + k))
						{
							placeGenBlock(world, rand, x + i, y + j, z + k);
						}
					}
					else if (distVal < o && (j == -p || j == p))
					{
						if (world.isAirBlock(x + i, y + j, z + k))
						{
							placeGenBlock(world, rand, x + i, y + j, z + k);
						}
					}
				}
			}
		}
		return;
	}

	public void placeGenBlock(World world, Random rand, int x, int y, int z)
	{
		int meta = rand.nextInt(GemRef.gemNames.length);
		world.setBlock(x, y, z, HS_Blocks.GemRock, meta, 1);
	}

}
