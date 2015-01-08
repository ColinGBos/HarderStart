package com.vapourdrive.harderstart.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.vapourdrive.harderstart.blocks.HS_Blocks;

public class FlintRockGenerator extends WorldGenerator
{

	/**
	 * "flint" rocks generate in the world, first tools material woot TODO make
	 * modular for use with more surface gen blocks
	 */
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		int height = world.getTopSolidOrLiquidBlock(x, z);
		Block block = world.getBlock(x, height - 1, z);
		Material material = block.getMaterial();

		if (material == Material.ground || material == Material.grass || material == Material.rock)
		{
			world.setBlock(x, height, z, HS_Blocks.BlockFlint, 0, 2);
		}
		return true;
	}

}
