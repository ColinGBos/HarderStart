package com.vapourdrive.harderstart.interfaces.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class CuttingRecipeSlot extends Slot
{

	public CuttingRecipeSlot(IInventory inventory, int id, int x, int y)
	{
		super(inventory, id, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return true;
	}

}
