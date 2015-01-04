package com.vapourdrive.harderstart.itemblocks;

import java.util.List;

import com.vapourdrive.harderstart.HarderStart;
import com.vapourdrive.harderstart.items.GemRef;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class GemItemBlock extends HS_BaseItemBlock
{

	public GemItemBlock(Block block)
	{
		super(block);
		this.setCreativeTab(HarderStart.tabGem);
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
		if (meta < 0 || meta >= GemRef.gemNames.length)
		{
			meta = 0;
		}
		return super.getUnlocalizedName() + "." + GemRef.gemNames[meta];
	}

	/**
	 * @param stack
	 * @param player
	 * @param list
	 * @param useExtraInformation
	 */
	public void addDetails(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation)
	{
		list.add("Drops various gems:");
		for(int i = 0; i < GemRef.gem_colour[stack.getItemDamage()].length; i++)
		{
			list.add(StatCollector.translateToLocal(GemRef.gem_colour[stack.getItemDamage()][i].getUnlocalizedName()+".name"));
		}
		return;
	}

}
