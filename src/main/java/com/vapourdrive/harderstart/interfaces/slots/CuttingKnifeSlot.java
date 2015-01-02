package com.vapourdrive.harderstart.interfaces.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

import com.vapourdrive.harderstart.items.CuttingKnifeBase;

public class CuttingKnifeSlot extends Slot
{
	public Class[] toolClasses =
	{
			ItemAxe.class, ItemSword.class, ItemPickaxe.class, ItemSpade.class, ItemHoe.class, CuttingKnifeBase.class
	};

	public CuttingKnifeSlot(IInventory inventory, int id, int x, int y)
	{
		super(inventory, id, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack stack)
	{
		for (int i = 0; i < toolClasses.length; i++)
		{
			if (toolClasses[i].isAssignableFrom(stack.getItem().getClass()))
			{
				return true;
			}
		}
		return false;
	}

}