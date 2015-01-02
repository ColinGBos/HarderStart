package com.vapourdrive.harderstart.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemAxe;

import com.vapourdrive.harderstart.HS_ModInfo;
import com.vapourdrive.harderstart.HarderStart;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FlintHatchet extends ItemAxe
{

	public FlintHatchet(ToolMaterial material)
	{
		super(material);
		setCreativeTab(HarderStart.tabharderstart);
		setUnlocalizedName(HS_ItemInfo.FlintHatchetName);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		itemIcon = register.registerIcon(HS_ModInfo.RESOURSE_LOCATION + HS_ItemInfo.FlintHatchetName);
	}

}
