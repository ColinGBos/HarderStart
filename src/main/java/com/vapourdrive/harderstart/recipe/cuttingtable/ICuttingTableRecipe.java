package com.vapourdrive.harderstart.recipe.cuttingtable;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ICuttingTableRecipe
{
	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	boolean matches(InventoryCrafting inventory, World world);

	/**
	 * Returns an Item that is the result of this recipe
	 */
	ItemStack[] getCraftingResult(InventoryCrafting inventory);

	/**
	 * Gets Array of Itemstacks - output
	 * 
	 * @return ItemStack[]
	 */
	ItemStack[] getRecipeOutput();

	ItemStack getInput();
	
	Class getTool();
}
