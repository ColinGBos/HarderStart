package com.vapourdrive.harderstart.itemblocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class FireClayItemBlock extends HS_BaseItemBlock
{

	public FireClayItemBlock(Block block)
	{
		super(block);
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
		list.add("Light on fire to create hardened fire clay");
		return;
	}

}
