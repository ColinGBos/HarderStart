package com.vapourdrive.harderstart.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.vapourdrive.harderstart.HS_ModInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFlint extends HS_BaseBlock
{

	public BlockFlint()
	{
		super(Material.rock);
		setBlockName(HS_BlockInfo.FlintName);
		setBlockBounds(0.375F, 0.0F, 0.3125F, 0.625F, 0.0625F, 0.625F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		blockIcon = register.registerIcon(HS_ModInfo.RESOURSE_LOCATION + HS_BlockInfo.FlintName);
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
	{
		return true;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune)
	{
		return Items.flint;
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return 1;
	}

	/**
	 * You can "harvest" this without the need to punch it, just pick it up off the ground
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fx, float fy, float fz)
	{
		if (!player.inventory.addItemStackToInventory(new ItemStack(Items.flint, 1)))
		{
			this.dropBlockAsItem(world, x, y, z, 0, 1);
		}
		world.setBlockToAir(x, y, z);

		return true;
	}

}
