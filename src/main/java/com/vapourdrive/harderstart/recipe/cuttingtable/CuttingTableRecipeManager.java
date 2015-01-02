package com.vapourdrive.harderstart.recipe.cuttingtable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.vapourdrive.harderstart.HarderStart;

public class CuttingTableRecipeManager
{
	private static final CuttingTableRecipeManager instance = new CuttingTableRecipeManager();
	public List recipes = new ArrayList();

	public static CuttingTableRecipeManager getInstance()
	{
		return instance;
	}

	public CuttingTableRecipeManager()
	{
		// this.addItemCut(Items.beef, new ItemStack[]
		// {
		// new ItemStack(Items.bone, 1), new ItemStack(Items.blaze_rod, 1), new
		// ItemStack(Items.diamond_axe, 3)
		// });
		// this.addItemCut(Items.bed, new ItemStack[]
		// {
		// new ItemStack(Items.stick, 1)
		// });
		// this.addItemCut(Items.boat, new ItemStack[]
		// {
		// new ItemStack(Items.stick, 1), new ItemStack(Blocks.planks, 2, 1)
		// });
		// this.addBlockCut(Blocks.anvil, new ItemStack[]
		// {
		// new ItemStack(Items.bone, 1), new ItemStack(Items.blaze_rod, 1)
		// });
		// this.addBlockCut(Blocks.log, new ItemStack[]
		// {
		// new ItemStack(Items.stick, 1)
		// });
		// this.addItemCut(Items.egg, new ItemStack[]
		// {
		// new ItemStack(Items.stick, 1), new ItemStack(Blocks.planks, 2, 1)
		// });
	}

	public void addBlockCut(Class tool, Block block, ItemStack[] stack)
	{
		this.addItemCut(tool, Item.getItemFromBlock(block), stack);
	}

	public void addItemCut(Class tool, Item item, ItemStack[] stack)
	{
		this.addCutRecipe(tool, new ItemStack(item, 1, 0), stack);
	}

	public CuttingTableRecipe addCutRecipe(Class tool, ItemStack cut, ItemStack[] results)
	{
		CuttingTableRecipe cuttingrecipe = new CuttingTableRecipe(tool, cut, results);

		this.recipes.add(cuttingrecipe);
		return cuttingrecipe;
	}

	public ItemStack[] getValidRecipe(ItemStack stack, ItemStack toolStack)
	{
		List<ICuttingTableRecipe> recipes = getRecipeList();

		Iterator<ICuttingTableRecipe> iterator = recipes.iterator();

		while (iterator.hasNext())
		{
			ICuttingTableRecipe recipe = iterator.next();
			ItemStack currentIngredient = recipe.getInput();
			Class currentTool = recipe.getTool();

			if (currentIngredient != null && currentTool != null)
			{
				// if (currentIngredient.getItem() == stack.getItem() &&
				// currentTool.isAssignableFrom(toolStack.getItem().getClass()))
				if (currentIngredient.getItem() == stack.getItem() && currentTool.isAssignableFrom(toolStack.getItem().getClass()))
				{
					if (currentIngredient.getItemDamage() == stack.getItemDamage())
					{
						if (recipe.getRecipeOutput() != null)
						{
							HarderStart.log.log(org.apache.logging.log4j.Level.INFO, "getValidRecipe: " + recipe.getRecipeOutput()[0]);
							return recipe.getRecipeOutput();
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * returns the List<> of all recipes
	 */
	public List getRecipeList()
	{
		return this.recipes;
	}

}
