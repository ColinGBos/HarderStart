package com.vapourdrive.harderstart.itemblocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.vapourdrive.harderstart.HarderStart;
import com.vapourdrive.harderstart.blocks.HS_BlockInfo;

public class CarbonItemBlock extends HS_BaseItemBlock
{

	public CarbonItemBlock(Block block)
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
		if (meta < 0 || meta >= HS_BlockInfo.CarbonBlockNames.length)
		{
			meta = 0;
		}
		return super.getUnlocalizedName() + "." + HS_BlockInfo.CarbonBlockNames[meta];
	}
	
	@Override
	public void addDetails(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		if(stack.getItemDamage() == 0)
		{
			list.add("Can be converted into coke");
		}
		else if(stack.getItemDamage() == 1)
		{
			list.add("High temperature fuel");
		}
		return;
	}

}
