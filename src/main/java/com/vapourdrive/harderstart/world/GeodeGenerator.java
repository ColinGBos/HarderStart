package com.vapourdrive.harderstart.world;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.vapourdrive.harderstart.blocks.HS_Blocks;
import com.vapourdrive.harderstart.items.GemRef;

public class GeodeGenerator extends WorldGenerator
{

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		int size = world.rand.nextInt(2) + 1;
		generateShell(world, rand, x, y, z, size);
		return false;
	}

	public void generateShell(World world, Random rand, int x, int y, int z, int size)
	{
		int radius = (5 + size);
		int sqrInner = (((1 + size) * (size)) * 3);
		int sqrOuter = (((2 + size) * (2 + size)) * 3);
		int yThresh = (2 + size);

		for (int i = -radius; i < radius; i++)
		{
			for (int j = -(radius - 1); j < (radius - 1); j++)
			{
				for (int k = -radius; k < radius; k++)
				{
					int distVal = (i * i + j * j + k * k);

					if (distVal > sqrInner && distVal < sqrOuter && (j >= -yThresh && j <= yThresh))
					{
						world.setBlock(x + i, y + j, z + k, Blocks.obsidian, 0, 1);
					}
					else if (distVal < sqrOuter && (j == -yThresh || j == yThresh || j == -yThresh - 1 || j == yThresh + 1))
					{
						world.setBlock(x + i, y + j, z + k, Blocks.obsidian, 0, 1);
					}
					else if (distVal < sqrInner && distVal > 9)
					{
						placeGenBlock(world, rand, x + i, y + j, z + k, distVal);
					}
				}
			}
		}
		return;
	}

	public void placeGenBlock(World world, Random rand, int x, int y, int z, int distVal)
	{
		if (rand.nextInt(distVal) > 7)
		{
			int meta = rand.nextInt(GemRef.gemNames.length);
			world.setBlock(x, y, z, HS_Blocks.GemRock, meta, 1);
		}
	}

}
