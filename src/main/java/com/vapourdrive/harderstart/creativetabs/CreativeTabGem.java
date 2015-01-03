package com.vapourdrive.harderstart.creativetabs;

import com.vapourdrive.harderstart.items.HS_Items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabGem extends CreativeTabs
{

	public CreativeTabGem(int ID, String lable)
	{
		super(ID, lable);
		setBackgroundImageName("enchantedGems.png");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTabLabel()
	{
		return "EnchantingGems";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "Enchanted Gems";
	}
	
	@Override
	public Item getTabIconItem()
	{
		return HS_Items.gem_Alexandrite;
	}
	
	@Override
	public boolean hasSearchBar()
	{
		return true;
	}
	
	@Override
	public int getSearchbarWidth()
	{
		return 70;
	}

}
