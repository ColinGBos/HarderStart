package com.vapourdrive.harderstart.items;

import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import com.vapourdrive.harderstart.HS_ModInfo;
import com.vapourdrive.harderstart.HarderStart;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemEnchantmentGem extends Item
{
	String Name;
	Enchantment Enchantment;
	EnumChatFormatting Colour;

	public ItemEnchantmentGem(EnumChatFormatting colour, String name, Enchantment enchantment)
	{
		super();
		this.Name = name;
		this.Enchantment = enchantment;
		this.Colour = colour;
		this.setUnlocalizedName(Name);
		this.setCreativeTab(HarderStart.tabGem);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		itemIcon = register.registerIcon(HS_ModInfo.RESOURSE_LOCATION + Name);
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		return false;
	}

	/**
	 * Checks isDamagable and if it cannot be stacked
	 */
	@Override
	public boolean isItemTool(ItemStack stack)
	{
		return false;
	}

	public NBTTagList getEnchantlist(ItemStack stack)
	{
		if (stack.stackTagCompound != null && stack.stackTagCompound.hasKey("StoredEnchantments", 9))
		{
			return (NBTTagList) stack.stackTagCompound.getTag("StoredEnchantments");
		}
		else
		{
			return new NBTTagList();
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack)
	{
		return (this.Colour + StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name").trim());
	}

	/**
	 * Adds the enchantment info for the stack to the tooltip
	 * suppressed warning is irritating, meh, same as vanilla's implementation
	 */
	@SuppressWarnings("static-access")
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advancedInfo)
	{
		super.addInformation(stack, player, list, advancedInfo);
		NBTTagList nbttaglist = this.getEnchantlist(stack);

		if (nbttaglist != null)
		{
			for (int i = 0; i < nbttaglist.tagCount(); ++i)
			{
				short shortID = nbttaglist.getCompoundTagAt(i).getShort("id");
				short shortLevel = nbttaglist.getCompoundTagAt(i).getShort("lvl");

                if (Enchantment.enchantmentsList[shortID] != null)
                {
                    list.add(Enchantment.enchantmentsList[shortID].getTranslatedName(shortLevel));
                }
			}
		}
	}

	/**
	 * Adds a stored enchantment to the stack
	 */
	public void addEnchantment(ItemStack stack, EnchantmentData enchData)
	{
		NBTTagList tagList = this.getEnchantlist(stack);
		boolean notEnchantedWith = true;

		for (int i = 0; i < tagList.tagCount(); ++i)
		{
			NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);

			if (tagCompound.getShort("id") == enchData.enchantmentobj.effectId)
			{
				if (tagCompound.getShort("lvl") < enchData.enchantmentLevel)
				{
					tagCompound.setShort("lvl", (short) enchData.enchantmentLevel);
				}

				notEnchantedWith = false;
				break;
			}
		}

		if (notEnchantedWith)
		{
			NBTTagCompound tag = new NBTTagCompound();
			tag.setShort("id", (short) enchData.enchantmentobj.effectId);
			tag.setShort("lvl", (short) enchData.enchantmentLevel);
			tagList.appendTag(tag);
		}

		if (!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}

		stack.getTagCompound().setTag("StoredEnchantments", tagList);
	}

	/**
	 * Returns the ItemStack of an enchanted version of this item.
	 */
	public ItemStack getEnchantedItemStack(Random rand)
	{
		ItemStack itemstack = new ItemStack(this);
		int value = rand.nextInt(Enchantment.getMaxLevel() + 1);

		this.addEnchantment(itemstack, new EnchantmentData(Enchantment, value));
		return itemstack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		ItemStack stack = new ItemStack(item, 1);
		list.add(stack);

		for (int i = 1; i <= this.Enchantment.getMaxLevel(); i++)
		{
			stack = new ItemStack(item, 1);
			stack.addEnchantment(Enchantment, i);
			list.add(stack);
		}
	}
	
	public Enchantment getGemEnchantment()
	{
		return this.Enchantment;
	}
}
