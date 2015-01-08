package com.vapourdrive.harderstart.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;

import com.vapourdrive.harderstart.HS_ModInfo;
import com.vapourdrive.harderstart.HarderStart;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HS_ItemFood extends ItemFood
{
	String Name;

	public HS_ItemFood(int value, float saturation, boolean isWolfFood, String name)
	{
		super(value, saturation, isWolfFood);
		setCreativeTab(HarderStart.tabharderstart);
		Name = name;
		setUnlocalizedName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		itemIcon = register.registerIcon(HS_ModInfo.RESOURSE_LOCATION + Name);
	}

}
