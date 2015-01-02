package com.vapourdrive.harderstart.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import com.vapourdrive.harderstart.HS_ModInfo;
import com.vapourdrive.harderstart.HarderStart;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Knife extends CuttingKnifeBase
{
	String name;

	public Knife(ToolMaterial material, String Name)
	{
		super(material);
		setCreativeTab(HarderStart.tabharderstart);
		setUnlocalizedName(Name);
		name = Name;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		itemIcon = register.registerIcon(HS_ModInfo.RESOURSE_LOCATION + name);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) 
	{
		int max = stack.getMaxDamage();
		list.add(EnumChatFormatting.GREEN + "Durability: " + (max - stack.getItemDamage()) + "/" + max);
	}

}
