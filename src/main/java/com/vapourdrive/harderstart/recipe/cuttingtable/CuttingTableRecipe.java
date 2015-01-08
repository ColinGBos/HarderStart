package com.vapourdrive.harderstart.recipe.cuttingtable;

import java.util.Iterator;
import java.util.List;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CuttingTableRecipe implements ICuttingTableRecipe
{
	/** Is a array of ItemStack that composes the recipe. */
	public final Class toolItem;
	public final ItemStack recipeItem;
	/** Is array of ItemStack that you get when craft the recipe. */
	public final ItemStack[] recipeOutput;

	public CuttingTableRecipe(Class tool, ItemStack stack, ItemStack[] result)
	{
		this.toolItem = tool;
		this.recipeItem = stack;
		this.recipeOutput = result;
	}

	@Override
	public boolean matches(InventoryCrafting inventory, World world)
	{
		ItemStack invStack = inventory.getStackInRowAndColumn(0, 1);
		ItemStack toolStack = inventory.getStackInRowAndColumn(0, 0);
		ItemStack recipeStack = this.recipeItem;
		Class toolItem = this.toolItem;

		if (invStack != null && recipeStack != null && toolStack != null)
		{
			if (toolItem.isAssignableFrom(toolStack.getItem().getClass()))
			{
				if (invStack == recipeStack)
				{
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public ItemStack[] getCraftingResult(InventoryCrafting inventory)
	{
		ItemStack[] result = this.getRecipeOutput();

		return result;
	}

	@Override
	public ItemStack[] getRecipeOutput()
	{
		return this.recipeOutput;
	}

	@Override
	public ItemStack getInput()
	{
		return this.recipeItem;
	}

	@Override
	public Class getTool()
	{
		return this.toolItem;
	}

	public boolean hasOutput(ItemStack result)
	{
		List<ICuttingTableRecipe> recipes = CuttingTableRecipeManager.getInstance().getRecipeList();

		Iterator<ICuttingTableRecipe> iterator = recipes.iterator();

		while (iterator.hasNext())
		{
			ICuttingTableRecipe recipe = iterator.next();
			ItemStack[] current = recipe.getRecipeOutput();

			if (current != null)
			{
				for (int i = 0; i < current.length; i++)
				{
					if (current[i] != null)
					{
						if (current[i].getItem() == result.getItem())
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}

}
