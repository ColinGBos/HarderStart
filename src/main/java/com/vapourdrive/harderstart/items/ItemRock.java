package com.vapourdrive.harderstart.items;

import com.vapourdrive.harderstart.HS_ModInfo;
import com.vapourdrive.harderstart.HarderStart;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemRock extends Item
{
	/**
	 * TODO implement rocks thrown like snowballs
	 */
	public ItemRock()
	{
		super();
		this.setCreativeTab(HarderStart.tabharderstart);
		this.setUnlocalizedName(HS_ItemInfo.RockName);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		itemIcon = register.registerIcon(HS_ModInfo.RESOURSE_LOCATION + HS_ItemInfo.RockName);
	}
}
