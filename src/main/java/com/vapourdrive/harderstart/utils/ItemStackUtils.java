package com.vapourdrive.harderstart.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemStackUtils
{
	public static Item getItemFromString(String ModId, String ItemName)
	{
		Item item = GameRegistry.findItem(ModId, ItemName);
		return item;
	}

	public static Block getBlockFromString(String ModId, String BlockName)
	{
		Block block = GameRegistry.findBlock(ModId, BlockName);
		return block;
	}

	public static ItemStack getItemStackFromString(String ModId, String ItemStackName, int StackSize)
	{
		ItemStack stack = GameRegistry.findItemStack(ModId, ItemStackName, StackSize);
		return stack;
	}
}
