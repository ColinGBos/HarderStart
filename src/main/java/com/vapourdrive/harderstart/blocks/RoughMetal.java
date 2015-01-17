package com.vapourdrive.harderstart.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.vapourdrive.harderstart.HS_ModInfo;
import com.vapourdrive.harderstart.HarderStart;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RoughMetal extends Block
{
	String name;
	int stages;
	int templevel;
	Block result;
	int resultmeta;
	int smelttime;
	private IIcon[] blockTexture;

	public RoughMetal(String Name, int Stages, int tempLevel, Block Result, int resultMeta, int smeltTime)
	{
		super(Material.iron);
		blockHardness = 0.1f;
		setStepSound(soundTypeMetal);
		setCreativeTab(HarderStart.tabharderstart);
		setBlockName(Name);
		name = Name;
		stages = Stages;
		templevel = tempLevel;
		result = Result;
		resultmeta = resultMeta;
		smelttime = smeltTime;
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		blockTexture = new IIcon[stages];
		for (int i = 0; i < stages; i++)
		{
			blockTexture[i] = register.registerIcon(HS_ModInfo.RESOURSE_LOCATION + "metal/" + name + "_" + i);
		}
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return blockTexture[meta];
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		float f = 0.0625F;
		return AxisAlignedBB.getBoundingBox((double) ((float) x + f), (double) y, (double) ((float) z + f), (double) ((float) (x + 1) - f),
				(double) ((float) (y + 1) - f), (double) ((float) (z + 1) - f));
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		int meta = world.getBlockMetadata(x, y, z);
		int damage = 0;
		if (meta > 0)
		{
			damage++;
		}
		if (meta > 2 && meta < 8)
		{
			damage++;
		}
		if (meta > 4 && meta < 6)
		{
			damage++;
		}
		if (damage > 0)
		{
			entity.attackEntityFrom(DamageSource.onFire, (damage) * 1.0F);
		}
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		int light = 0;
		light = Math.abs(6 - world.getBlockMetadata(x, y, z));
		if(14 - (2 * light) < 0)
		{
			return 0;
		}
		this.setLightLevel(14 - (2 * light));
		return 14 - (2 * light);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		checkUpdate(world, x, y, z);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		checkUpdate(world, x, y, z);
	}

	public void checkUpdate(World world, int x, int y, int z)
	{
		Random rand = new Random();
		int delay = rand.nextInt(20);
		world.scheduleBlockUpdate(x, y, z, this, smelttime + delay);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (!isHeated(world, x, y, z))
		{
			if (meta >= stages / 2)
			{
				meta++;
				if (meta >= stages)
				{
					world.setBlock(x, y, z, this.result, this.resultmeta, 3);
				}
				else
				{
					world.setBlockMetadataWithNotify(x, y, z, meta, 3);
					checkUpdate(world, x, y, z);
				}
			}
			else
			{
				meta--;
				if (meta < 0)
				{
					meta = 0;
				}
				world.setBlockMetadataWithNotify(x, y, z, meta, 3);
			}
		}
		else if (isInsulated(world, x, y, z))
		{
			if (meta <= stages / 2)
			{
				meta++;
				{
					world.setBlockMetadataWithNotify(x, y, z, meta, 2);
					checkUpdate(world, x, y, z);
				}
			}
		}
	}

	public boolean isHeated(World world, int x, int y, int z)
	{
		if (world.getBlock(x, y - 1, z) == HS_Blocks.CarbonBlock && world.getBlockMetadata(x, y - 1, z) == 1)
		{
			return true;
		}
		return false;
	}

	public boolean isInsulated(World world, int x, int y, int z)
	{
		int insulatedLevel = 0;

		for (int i = -1; i <= 1; i++)
		{
			for (int j = -1; j <= 1; j++)
			{
				for (int k = -1; k <= 1; k++)
				{
					if (world.getBlock(x + i, y + j, z + k) == HS_Blocks.HardenedFireClay
							&& world.getBlockMetadata(x + i, y + j, z + k) == 1)
					{
						insulatedLevel++;
					}
				}
			}
		}
		return insulatedLevel >= 24;
	}

}
