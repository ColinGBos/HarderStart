package com.vapourdrive.harderstart.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.vapourdrive.harderstart.HS_ModInfo;
import com.vapourdrive.harderstart.HarderStart;
import com.vapourdrive.harderstart.tileentities.CuttingTable_TE;
import com.vapourdrive.harderstart.utils.RandomUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CuttingTable extends BlockContainer
{
	private IIcon blockTop;
	private IIcon blockSide;
	private IIcon blockBottom;

	protected CuttingTable()
	{
		super(Material.wood);
		float min = 0.0F;
		setBlockBounds(min, min, min, 1.0F, 0.75F, 1.0F);
		this.setBlockName(HS_BlockInfo.CuttingTableName);
		this.setCreativeTab(HarderStart.tabharderstart);
		this.setHardness(1.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		blockTop = register.registerIcon(HS_ModInfo.RESOURSE_LOCATION + HS_BlockInfo.CuttingTableTop);
		blockBottom = register.registerIcon(HS_ModInfo.RESOURSE_LOCATION + HS_BlockInfo.CuttingTableBottom);
		blockSide = register.registerIcon(HS_ModInfo.RESOURSE_LOCATION + HS_BlockInfo.CuttingTableSide);
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (side == 1)
		{
			return blockTop;
		}

		if (side == 0)
		{
			return blockBottom;
		}
		else
		{
			return blockSide;
		}
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
	public TileEntity createNewTileEntity(World world, int id)
	{
		return new CuttingTable_TE();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz)
	{
		if (!world.isRemote)
		{
			player.openGui(HarderStart.instance, 0, world, x, y, z);
		}

		return true;
	}

	/**
	 * Loops through inventory and spawns each itemstack
	 */
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof IInventory)
		{
			IInventory inv = (IInventory) te;
			for (int i = 0; i < inv.getSizeInventory(); i++)
			{
				ItemStack stack = inv.getStackInSlotOnClosing(i);

				if (stack != null)
				{
					RandomUtils.spawnItem(world, x, y, z, stack, 0.7F);
				}
			}
		}

		super.breakBlock(world, x, y, z, block, meta);
	}

}
