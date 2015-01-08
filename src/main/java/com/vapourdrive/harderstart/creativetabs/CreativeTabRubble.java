package com.vapourdrive.harderstart.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.vapourdrive.harderstart.items.HS_Items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabRubble extends CreativeTabs
{

	public CreativeTabRubble(int ID, String lable)
	{
		super(ID, lable);
		setBackgroundImageName("item_search.png");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTabLabel()
	{
		return "Rubble";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "Rubble";
	}

	@Override
	public Item getTabIconItem()
	{
		return HS_Items.stone_rubble;
	}

	@Override
	public boolean hasSearchBar()
	{
		return true;
	}

}
