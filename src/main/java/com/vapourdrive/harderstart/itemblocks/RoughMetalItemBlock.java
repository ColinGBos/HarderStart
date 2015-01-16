package com.vapourdrive.harderstart.itemblocks;

import java.util.List;

import com.vapourdrive.harderstart.HarderStart;
import com.vapourdrive.harderstart.blocks.HS_BlockInfo;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class RoughMetalItemBlock extends HS_BaseItemBlock
{

	public RoughMetalItemBlock(Block block)
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
		if (meta < 0 || meta >= HS_BlockInfo.RoughMetalNames.length)
		{
			meta = 0;
		}
		return super.getUnlocalizedName() + "." + HS_BlockInfo.RoughMetalNames[meta];
	}
	
	@Override
	public void addDetails(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		list.add("Refined into pure metal blocks");
		return;
	}

}
