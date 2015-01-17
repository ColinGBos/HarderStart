package com.vapourdrive.harderstart.blocks;

import java.util.Random;

import org.apache.logging.log4j.Level;

import com.vapourdrive.harderstart.HS_ModInfo;
import com.vapourdrive.harderstart.HarderStart;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

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
				if(meta < 0)
				{
					meta = 0;
				}
				{
					world.setBlock(x, y, z, this.result, this.resultmeta, 3);
				}
			}
		}
		else if(isInsulated(world, x, y, z))
		{
			if(meta <= stages/2)
			{
				meta ++;
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
		HarderStart.log.log(Level.INFO, insulatedLevel);
		return insulatedLevel >= 24;
	}

}
