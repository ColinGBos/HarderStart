package com.vapourdrive.harderstart.itemblocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HS_BaseItemBlock extends ItemBlock
{

	public HS_BaseItemBlock(Block block)
	{
		super(block);
	}

	/**
	 * Override child functions, not this
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
		{
			addImportant(stack, player, list, useExtraInformation);
			addDetails(stack, player, list, useExtraInformation);
		}
		else
		{
			addImportant(stack, player, list, useExtraInformation);
		}
	}

	/**
	 * 
	 * @param stack
	 * @param player
	 * @param list
	 * @param useExtraInformation
	 */
	public void addImportant(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		return;
	}

	/**
	 * @param stack
	 * @param player
	 * @param list
	 * @param useExtraInformation
	 */
	public void addDetails(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		return;
	}

}
