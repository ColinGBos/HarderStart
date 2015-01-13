package com.vapourdrive.harderstart.itemblocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.vapourdrive.harderstart.HarderStart;
import com.vapourdrive.harderstart.blocks.HS_BlockInfo;

public class FireBrickItemBlock extends HS_BaseItemBlock
{

	public FireBrickItemBlock(Block block)
	{
		super(block);
		hasSubtypes = true;
		setCreativeTab(HarderStart.tabharderstart);
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
		if (meta < 0 || meta >= HS_BlockInfo.FireBrickNames.length)
		{
			meta = 0;
		}
		return super.getUnlocalizedName() + "." + HS_BlockInfo.FireBrickNames[meta];
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
