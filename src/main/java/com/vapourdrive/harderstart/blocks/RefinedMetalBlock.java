package com.vapourdrive.harderstart.blocks;

import java.util.List;

import com.vapourdrive.harderstart.HS_ModInfo;
import com.vapourdrive.harderstart.HarderStart;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class RefinedMetalBlock extends Block
{
	
	@SideOnly(Side.CLIENT)
	private IIcon[] blockTexture;

	public RefinedMetalBlock()
	{
		super(Material.iron);
		setHardness(1.0F);
		setBlockName(HS_BlockInfo.RougMetalBlock);
		setCreativeTab(HarderStart.tabharderstart);
		setStepSound(soundTypeMetal);
	}
	
	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < HS_BlockInfo.RefinedMetalNames.length; ++i)
		{
			list.add(new ItemStack(block, 1, i));
		}
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
		blockTexture = new IIcon[HS_BlockInfo.RefinedMetalNames.length];
		for (int i = 0; i < HS_BlockInfo.RefinedMetalNames.length; i++)
		{
			blockTexture[i] = register.registerIcon(HS_ModInfo.RESOURSE_LOCATION + HS_BlockInfo.RefinedMetalNames[i] + "_block");
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		return blockTexture[meta];
	}

}
