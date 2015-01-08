package com.vapourdrive.harderstart.handlers.NEIIntegration;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.opengl.GL11;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler;

import com.vapourdrive.harderstart.interfaces.cuttingtable.GuiCuttingTable;
import com.vapourdrive.harderstart.items.HS_Items;
import com.vapourdrive.harderstart.recipe.cuttingtable.CuttingTableRecipe;
import com.vapourdrive.harderstart.recipe.cuttingtable.CuttingTableRecipeManager;

public class CuttingTableRecipeHandler extends TemplateRecipeHandler
{
	public class CTRecipe extends TemplateRecipeHandler.CachedRecipe
	{
		public Item[] sampleTools =
		{
				HS_Items.flint_knife, Items.iron_axe, Items.iron_shovel, Items.iron_pickaxe
		};
		public PositionedStack ingredient;
		public ArrayList<PositionedStack> tool;
		public ArrayList<PositionedStack> otherOutputs;
		public PositionedStack output;

		// public CTRecipe(ItemStack items, ItemStack[] out)
		// {
		// this(null, items, out);
		// }

		public CTRecipe(Class tool, ItemStack items, ItemStack[] out)
		{
			otherOutputs = new ArrayList<PositionedStack>();
			ingredient = new PositionedStack(items, 29, 31);
			output = new PositionedStack(out[0], 75, 22);

			for (int i = 0; i < sampleTools.length; i++)
			{
				if (tool.isAssignableFrom(sampleTools[i].getClass()))
				{
					otherOutputs.add(new PositionedStack(new ItemStack(sampleTools[i]), 29, 13));
				}
			}

			for (int j = 1; j < out.length; j++)
			{
				if (out[j] != null)
				{
					otherOutputs.add(new PositionedStack(out[j], 75 + j * 18, 22));
				}
			}
		}

		public CTRecipe(CuttingTableRecipe recipe)
		{
			this(recipe.getTool(), recipe.getInput(), recipe.getRecipeOutput());
		}

		@Override
		public PositionedStack getResult()
		{
			return output;
		}

		@Override
		public PositionedStack getIngredient()
		{
			return ingredient;
		}

		@Override
		public List<PositionedStack> getOtherStacks()
		{
			return otherOutputs;
		}
	}

	@Override
	public void drawBackground(int arg0)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiDraw.changeTexture(getGuiTexture());
		GuiDraw.drawTexturedModalRect(0, 0, 5, 11, 166, 65);

	}

	@Override
	public Class<? extends GuiContainer> getGuiClass()
	{
		return GuiCuttingTable.class;
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results)
	{
		if (outputId.equals("CuttingTable") && getClass() == CuttingTableRecipeHandler.class)
		{
			List<CuttingTableRecipe> recipes = CuttingTableRecipeManager.getInstance().getRecipeList();
			for (CuttingTableRecipe recipe : recipes)
			{
				CTRecipe res = new CTRecipe(recipe.getTool(), recipe.getInput(), recipe.getRecipeOutput());
				arecipes.add(res);
			}
		}
		else
		{
			super.loadCraftingRecipes(outputId, results);
		}
	}

	@Override
	public void loadCraftingRecipes(ItemStack result)
	{
		List<CuttingTableRecipe> recipes = CuttingTableRecipeManager.getInstance().getRecipeList();
		for (CuttingTableRecipe recipe : recipes)
		{
			for (int i = 0; i < recipe.getRecipeOutput().length; i++)
			{
				if (recipe.getRecipeOutput()[i].getItem() == result.getItem())
				{
					CTRecipe res = new CTRecipe(recipe.getTool(), recipe.getInput(), recipe.getRecipeOutput());
					arecipes.add(res);
				}
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient)
	{
		for (CuttingTableRecipe irecipe : (List<CuttingTableRecipe>) CuttingTableRecipeManager.getInstance().getRecipeList())
		{
			CTRecipe recipe = null;
			if (irecipe instanceof CuttingTableRecipe)
			{
				recipe = new CTRecipe((CuttingTableRecipe) irecipe);
			}

			if (recipe == null || !recipe.contains(recipe.getIngredients(), ingredient.getItem()))
			{
				continue;
			}

			if (recipe.contains(recipe.getIngredients(), ingredient))
			{
				arecipes.add(recipe);
			}
		}
	}

	@Override
	public String getRecipeName()
	{
		return "CuttingTable";
	}

	@Override
	public String getOverlayIdentifier()
	{
		return "CuttingTable";
	}

	@Override
	public void loadTransferRects()
	{
		transferRects.add(new RecipeTransferRect(new Rectangle(48, 22, 24, 16), "CuttingTable", new Object[0]));
	}

	@Override
	public String getGuiTexture()
	{
		return "harderstart:textures/gui/container/cuttingtable.png";
	}

	@Override
	public int recipiesPerPage()
	{
		return 1;
	}

	@Override
	public List<String> handleItemTooltip(GuiRecipe gui, ItemStack stack, List<String> currenttip, int recipe)
	{
		if (stack != null && stack.getItem() == HS_Items.flint_knife)
		{
			currenttip.add(EnumChatFormatting.GREEN + "Not Consumed");
		}

		return currenttip;
	}

}
