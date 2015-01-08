package com.vapourdrive.harderstart.recipe;

import com.vapourdrive.harderstart.recipe.cuttingtable.CuttingTableRecipeFiller;
import com.vapourdrive.harderstart.recipe.cuttingtable.CuttingTableRecipeManager;

public class RecipeRegister
{
	/**
	 * sets up the recipe registries I implement, currently only the cutting
	 * table
	 */
	public static void init()
	{
		new CuttingTableRecipeManager();

		CuttingTableRecipeFiller.init();
		return;
	}
}
