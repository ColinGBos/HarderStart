package com.vapourdrive.harderstart.handlers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class CraftingRecipeRemover
{
	/**
	 * Iterates through the crafting recipe list and removes recipes for
	 * specified stacks TODO actually use with point
	 */
	public static void init()
	{
		List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();

		Iterator<IRecipe> iterator = recipes.iterator();

		while (iterator.hasNext())
		{
			ItemStack current = iterator.next().getRecipeOutput();

			if (current != null && removeItem(current.getItem()))
			{
				iterator.remove();
			}
		}

		return;
	}

	public static boolean removeItem(Item item)
	{
		Item[] itemList =
		{
				Items.wooden_axe, Items.wooden_pickaxe, Items.wooden_sword, Items.wooden_shovel, Items.stone_axe, Items.stone_hoe,
				Items.stone_pickaxe, Items.stone_shovel, Items.stone_sword
		};

		if (Arrays.asList(itemList).contains(item))
		{
			return true;
		}

		return false;
	}
}
