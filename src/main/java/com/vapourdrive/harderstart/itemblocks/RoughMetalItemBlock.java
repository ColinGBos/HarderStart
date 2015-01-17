package com.vapourdrive.harderstart.itemblocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.vapourdrive.harderstart.HarderStart;

public class RoughMetalItemBlock extends HS_BaseItemBlock
{

	public RoughMetalItemBlock(Block block)
	{
		super(block);
		this.setCreativeTab(HarderStart.tabharderstart);
	}
	
	@Override
	public int getMetadata(int Meta)
	{
		return Meta;
	}
	
	@Override
	public void addDetails(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		list.add("Refined into pure metal form");
		return;
	}

}
