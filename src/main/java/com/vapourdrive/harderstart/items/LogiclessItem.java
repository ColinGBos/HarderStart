package com.vapourdrive.harderstart.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import com.vapourdrive.harderstart.HS_ModInfo;
import com.vapourdrive.harderstart.HarderStart;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LogiclessItem extends Item
{
	String name;

	public LogiclessItem(String Name)
	{
		super();
		this.setCreativeTab(HarderStart.tabharderstart);
		this.setUnlocalizedName(Name);
		name = Name;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		itemIcon = register.registerIcon(HS_ModInfo.RESOURSE_LOCATION + name);
	}
}
