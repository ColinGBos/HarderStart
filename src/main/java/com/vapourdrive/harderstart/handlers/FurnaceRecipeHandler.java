package com.vapourdrive.harderstart.handlers;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class FurnaceRecipeHandler
{
	public static void init()
	{
		recipeCopyer();
		recipeRemover();

		return;
	}

	/**
	 * will eventually be used to fill my own recipe list with food recipes
	 */
	public static void recipeCopyer()
	{

		return;
	}

	/**
	 * Iterates through furnace recipes removes those I don't want TODO use for
	 * something worthwhile
	 */
	public static void recipeRemover()
	{
		Map<Integer, ItemStack> recipes = FurnaceRecipes.smelting().getSmeltingList();

		Iterator current = recipes.entrySet().iterator();

		while (current.hasNext())
		{
			Map.Entry<Integer, ItemStack> entry = (Entry<Integer, ItemStack>) current.next();
			ItemStack stack = (ItemStack) entry.getValue();
			if (ItemFood.class.isAssignableFrom(stack.getItem().getClass()))
			{
				current.remove();
			}
			else if (stack.getItem() == Items.iron_ingot)
			{
				current.remove();
			}
		}

		return;
	}
}
