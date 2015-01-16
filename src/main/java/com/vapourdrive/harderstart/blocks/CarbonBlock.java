package com.vapourdrive.harderstart.blocks;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.Level;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.vapourdrive.harderstart.HS_ModInfo;
import com.vapourdrive.harderstart.HarderStart;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CarbonBlock extends Block
{

	@SideOnly(Side.CLIENT)
	private IIcon[] blockTexture;

	public CarbonBlock()
	{
		super(Material.sand);
		setBlockName(HS_BlockInfo.CarbonBlock);
		setCreativeTab(HarderStart.tabharderstart);
		setStepSound(soundTypeGravel);
		this.setHardness(0.6F);
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < HS_BlockInfo.CarbonBlockNames.length; ++i)
		{
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
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
		blockTexture = new IIcon[HS_BlockInfo.CarbonBlockNames.length];
		for (int i = 0; i < HS_BlockInfo.CarbonBlockNames.length; i++)
		{
			blockTexture[i] = register.registerIcon(HS_ModInfo.RESOURSE_LOCATION + HS_BlockInfo.CarbonBlockNames[i]);
		}
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return blockTexture[meta];
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
		return 0;
		// return Blocks.fire.getEncouragement(this);
	}

	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
		return 0;
		// return Blocks.fire.getFlammability(this);
	}

	@Override
	public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
		if (world.getBlockMetadata(x, y, z) == 0)
		{
			return true;
		}
		return false;
		// return getFlammability(world, x, y, z, face) > 0;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		HarderStart.log.log(Level.INFO, "neighbor update");
		if (world.getBlock(x, y + 1, z) == Blocks.fire && world.getBlockMetadata(x, y, z) == 0)
		{
			HarderStart.log.log(Level.INFO, "burning");
			Random rand = new Random();

			int delay = rand.nextInt(40);
			world.scheduleBlockUpdate(x, y, z, this, 400 + delay);
		}
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

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if (isInsulated(world, x, y, z))
		{
			world.setBlockMetadataWithNotify(x, y, z, 1, 3);
		}
		else if (world.getBlock(x, y + 1, z) == Blocks.fire)
		{
			world.setBlockToAir(x, y + 1, z);
		}
	}

}
