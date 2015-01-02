package com.vapourdrive.harderstart.interfaces.slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class CuttingResultSlot extends Slot
{
	public CuttingResultSlot(IInventory inventory, int id, int x, int y)
	{
		super(inventory, id, x, y);
	}

	/**
	 * Check if the stack is a valid item for this slot. Always true beside for
	 * the armor slots.
	 */
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return false;
	}

	@Override
	public ItemStack decrStackSize(int number)
	{
		return super.decrStackSize(number);
	}

	@Override
	public void onPickupFromSlot(EntityPlayer player, ItemStack stack)
	{
		this.onCrafting(stack);
		super.onPickupFromSlot(player, stack);
	}

	@Override
	protected void onCrafting(ItemStack stack, int par2)
	{
		this.onCrafting(stack);
	}

}
