package com.vapourdrive.harderstart.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabHS extends CreativeTabs
{

	public CreativeTabHS(int id, String icon)
	{
		super(id, icon);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTabLabel()
	{
		return "HarderStart";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "Harder Start";
	}

	@Override
	public Item getTabIconItem()
	{
		return Item.getItemFromBlock(Blocks.stone);
	}

}
