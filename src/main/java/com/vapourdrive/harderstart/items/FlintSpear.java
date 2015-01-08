package com.vapourdrive.harderstart.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.vapourdrive.harderstart.HS_ModInfo;
import com.vapourdrive.harderstart.HarderStart;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FlintSpear extends Item
{
	private final Item.ToolMaterial effect;

	/**
	 * TODO - implement spear throwing
	 * 
	 * @param material
	 */
	FlintSpear(Item.ToolMaterial material)
	{
		effect = material;
		maxStackSize = 1;
		setCreativeTab(HarderStart.tabharderstart);
		setUnlocalizedName(HS_ItemInfo.FlintSpearName);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		itemIcon = register.registerIcon(HS_ModInfo.RESOURSE_LOCATION + HS_ItemInfo.FlintSpearName);
	}

	public float func_150931_i()
	{
		return this.effect.getDamageVsEntity();
	}

	@Override
	public float func_150893_a(ItemStack stack, Block block)
	{
		if (block == Blocks.web)
		{
			return 8.0F;
		}
		else
		{
			Material material = block.getMaterial();
			return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves
					&& material != Material.gourd ? 1.0F : 1.5F;
		}
	}

	/**
	 * Current implementations of this method in child classes do not use the
	 * entry argument beside ev. They just raise the damage on the stack.
	 */
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase entity1, EntityLivingBase entity2)
	{
		stack.damageItem(1, entity2);
		return true;
	}

	/**
	 * Returns True is the item is renderer in full 3D when hold.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	/**
	 * returns the action that specifies what animation to play when the items
	 * is being used
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.bow;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 72000;
	}

	@Override
	public boolean func_150897_b(Block block)
	{
		return block == Blocks.web;
	}

	/**
	 * Return the name for this tool's material.
	 */
	public String getToolMaterialName()
	{
		return this.effect.toString();
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int duration)
	{
		int j = this.getMaxItemUseDuration(stack) - duration;

		float f = (float) j / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;

		if ((double) f < 0.1D)
		{
			return;
		}

		if (f > 1.0F)
		{
			f = 1.0F;
		}

		// EntityFlintSpear entityspear = new EntityFlintSpear(world, player, f
		// * 2.0F);
		//
		// if (f == 1.0F)
		// {
		// entityspear.setIsCritical(true);
		// }
		//
		// stack.damageItem(1, player);
		// world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F /
		// (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
		//
		// if (!world.isRemote)
		// {
		// world.spawnEntityInWorld(entityspear);
		// }
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World p_77654_2_, EntityPlayer p_77654_3_)
	{
		return stack;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		// player.setItemInUse(stack, this.getMaxItemUseDuration(stack));

		return stack;
	}
}
