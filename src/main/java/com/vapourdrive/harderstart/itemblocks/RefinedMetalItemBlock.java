package com.vapourdrive.harderstart.itemblocks;

import java.util.List;

import com.vapourdrive.harderstart.HarderStart;
import com.vapourdrive.harderstart.blocks.HS_BlockInfo;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class RefinedMetalItemBlock extends HS_BaseItemBlock
{

	public RefinedMetalItemBlock(Block block)
	{
		super(block);
		this.setCreativeTab(HarderStart.tabharderstart);
		this.hasSubtypes = true;
	}
	
	@Override
	public int getMetadata(int Meta)
	{
		return Meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		int meta = itemstack.getItemDamage();
		if (meta < 0 || meta >= HS_BlockInfo.RefinedMetalNames.length)
		{
			meta = 0;
		}
		return super.getUnlocalizedName() + "." + HS_BlockInfo.RefinedMetalNames[meta];
	}
	
	@Override
	public void addDetails(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		list.add("Refined metal blocks");
		return;
	}

}
