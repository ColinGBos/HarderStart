package com.vapourdrive.harderstart.itemblocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import com.vapourdrive.harderstart.HarderStart;
import com.vapourdrive.harderstart.items.HS_ItemInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FoodBucketItemBlock extends HS_BaseItemBlock
{

	public FoodBucketItemBlock(Block block)
	{
		super(block);
		this.setCreativeTab(HarderStart.tabharderstart);
		this.setMaxStackSize(1);
		this.setMaxDamage(512);
		this.setUnlocalizedName(HS_ItemInfo.FoodBucketName);
	}

	@Override
	public void addDetails(ItemStack stack, EntityPlayer player, List list, boolean bool)
	{
		list.add("");
		list.add("Sneak click to place");
		list.add("Stores food to eat, click with food to fill");
		list.add("Food needs to have over 0.6 saturation level");
	}

	@Override
	public void addImportant(ItemStack stack, EntityPlayer player, List list, boolean bool)
	{
		int max = stack.getMaxDamage();
		list.add(EnumChatFormatting.GREEN + "FoodValue " + (max - stack.getItemDamage()) + "/" + max);
		list.add(EnumChatFormatting.GREEN + "Hold Shift");
	}

	/**
	 * Overwritten to keep barrel from being placed all the time, sneak shift to
	 * place
	 */
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitx, float hity,
			float hitz)
	{
		Block block = world.getBlock(x, y, z);
		if (player.isSneaking())
		{
			if (block == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 7) < 1)
			{
				side = 1;
			}
			else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush && !block.isReplaceable(world, x, y, z))
			{
				if (side == 0)
				{
					--y;
				}

				if (side == 1)
				{
					++y;
				}

				if (side == 2)
				{
					--z;
				}

				if (side == 3)
				{
					++z;
				}

				if (side == 4)
				{
					--x;
				}

				if (side == 5)
				{
					++x;
				}
			}

			if (stack.stackSize == 0)
			{
				return false;
			}
			else if (!player.canPlayerEdit(x, y, z, side, stack))
			{
				return false;
			}
			else if (y == 255 && this.field_150939_a.getMaterial().isSolid())
			{
				return false;
			}
			else if (world.canPlaceEntityOnSide(this.field_150939_a, x, y, z, false, side, player, stack))
			{
				if (placeBlockAt(stack, player, world, x, y, z, side, hitx, hity, hitz, 0))
				{
					world.playSoundEffect((double) ((float) x + 0.5F), (double) ((float) y + 0.5F), (double) ((float) z + 0.5F),
							this.field_150939_a.stepSound.func_150496_b(), (this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F,
							this.field_150939_a.stepSound.getPitch() * 0.8F);
					--stack.stackSize;
				}

				return true;
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Player gets food eaten effect and the barrel "takes damage"
	 */
	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
	{
		int damage = stack.getItemDamage();
		int maxdamage = stack.getMaxDamage();
		int replenishment = maxdamage - damage;
		if (replenishment > 4)
		{
			replenishment = 4;
		}
		if (replenishment > 0)
		{
			player.getFoodStats().func_151686_a(new ItemFood(replenishment, 0.6F, false), stack);
			world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		}

		return new ItemStack(stack.getItem(), 1, damage + replenishment);
	}

	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack p_77626_1_)
	{
		return 32;
	}

	/**
	 * returns the action that specifies what animation to play when the items
	 * is being used
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_)
	{
		return EnumAction.eat;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer player starts eating food
	 * if the barrel isn't empty
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (player.canEat(false) && stack.getItemDamage() != stack.getMaxDamage())
		{
			player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		}

		return stack;
	}

	/**
	 * adds both and empty barrel and a full one to the creative tab
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, this.getMaxDamage()));
	}

}
