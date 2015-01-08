package com.vapourdrive.harderstart.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.vapourdrive.harderstart.HS_ModInfo;
import com.vapourdrive.harderstart.HarderStart;
import com.vapourdrive.harderstart.items.GemRef;
import com.vapourdrive.harderstart.items.ItemEnchantmentGem;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GemRock extends Block
{
	private IIcon[] blockTexture;

	protected GemRock()
	{
		super(Material.glass);
		this.setBlockName(HS_BlockInfo.GemBlockName);
		this.setCreativeTab(HarderStart.tabGem);
		this.setHardness(0.4F);
		this.setStepSound(soundTypeStone);
		this.setLightLevel(1.0F);
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < GemRef.gemNames.length; ++i)
		{
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		blockTexture = new IIcon[GemRef.gemNames.length];
		for (int i = 0; i < GemRef.gemNames.length; i++)
		{
			blockTexture[i] = register.registerIcon(HS_ModInfo.RESOURSE_LOCATION + GemRef.gemNames[i]);
		}
	}

	@Override
	protected boolean canSilkHarvest()
	{
		return true;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False
	 * (examples: signs, buttons, stairs, etc)
	 */
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess access, int x, int y, int z, int side)
	{
		Block block = access.getBlock(x, y, z);

		if (block == this)
		{
			return false;
		}

		return true;
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return blockTexture[meta];
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this);
	}

	/**
	 * Spawns EntityItem in the world for the given ItemStack if the world is
	 * not remote.
	 */

	@Override
	public int quantityDropped(Random rand)
	{
		return 0;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		int optioncount = GemRef.gem_colour[metadata].length;
		int option = world.rand.nextInt(optioncount);
		ItemEnchantmentGem gem = (ItemEnchantmentGem) GemRef.gem_colour[metadata][option];
		Enchantment Enchantment = gem.getGemEnchantment();
		int maxLevels = Enchantment.getMaxLevel();

		ItemStack stack = new ItemStack(gem);

		float f = ((world.rand.nextFloat() + ((float) maxLevels) / (10.0f)) + ((float) fortune / (5.0f)));

		if (f > 0.8f)
		{
			f = (world.rand.nextFloat() + ((float) maxLevels) / (10.0f)) + ((float) fortune / (5.0f)) * maxLevels;

			int level = (int) f;

			if (level > maxLevels)
			{
				level = maxLevels;
			}
			if (level > 0)
			{
				stack.addEnchantment(Enchantment, level);
			}
		}

		if (stack != null)
		{
			ret.add(stack);
		}
		return ret;
	}

}
