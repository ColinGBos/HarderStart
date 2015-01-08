package com.vapourdrive.harderstart.interfaces.cuttingtable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import com.vapourdrive.harderstart.interfaces.slots.CuttingKnifeSlot;
import com.vapourdrive.harderstart.interfaces.slots.CuttingRecipeSlot;
import com.vapourdrive.harderstart.interfaces.slots.CuttingResultSlot;
import com.vapourdrive.harderstart.tileentities.CuttingTable_TE;

public class ContainerCuttingTable extends Container
{
	private CuttingTable_TE cuttingTable;

	public ContainerCuttingTable(InventoryPlayer invPlayer, CuttingTable_TE cuttingtable)
	{
		this.cuttingTable = cuttingtable;

		this.addSlotToContainer(new CuttingKnifeSlot(cuttingtable, 0, 34, 24));
		this.addSlotToContainer(new CuttingRecipeSlot(cuttingtable, 1, 34, 42));

		int i;

		for (i = 0; i < 4; i++)
		{
			addSlotToContainer(new CuttingResultSlot(cuttingtable, 2 + i, 80 + i * 18, 33));
		}

		for (i = 0; i < 3; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
		}

		this.cuttingTable.updateCraftingMatrix(0);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return cuttingTable.isUseableByPlayer(player);
	}

	/**
	 * Many attempts at getting the slots to update properly TODO consider once
	 * again moving logi to container from Tile Entity inventory
	 */
	@Override
	public void onCraftMatrixChanged(IInventory inventory)
	{
		this.detectAndSendChanges();

		return;
	}

	/**
	 * Supposed to update the cutting table when something happens
	 * implementations of stack manipulation are too varied to be reliable TODO
	 * revamp - crafts as default so there are no tricky possible dupe-bug
	 * situations
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
	{
		ItemStack currentStack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotID);

		if (slot != null && slot.getHasStack())
		{
			ItemStack nextStack = slot.getStack();
			currentStack = nextStack.copy();

			if (slotID >= 0 && slotID < 6)
			{
				if (!this.mergeItemStack(nextStack, 6, 42, true))
				{
					return null;
				}
				if (slotID == 0 || slotID == 1)
				{
					if (!this.cuttingTable.hasIngredientDecreased)
					{
						this.cuttingTable.clearResult();
					}
				}
				else
				{
					if (!this.cuttingTable.hasIngredientDecreased)
					{
						this.cuttingTable.hasIngredientDecreased = true;
						this.cuttingTable.decrCrafters();
					}
				}

				slot.onSlotChange(nextStack, currentStack);
			}

			else if (slotID >= 6 && slotID < 42)
			{
				if (ItemTool.class.isAssignableFrom(nextStack.getItem().getClass()))
				{
					if (!this.mergeItemStack(nextStack, 0, 1, false))
					{
						return null;
					}
				}
				else
				{
					return null;
				}
			}

			else if (!this.mergeItemStack(nextStack, 6, 42, false))
			{
				return null;
			}

			if (nextStack.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			}

			else
			{
				slot.onSlotChanged();
			}

			if (nextStack.stackSize == currentStack.stackSize)
			{
				return null;
			}

			slot.onPickupFromSlot(player, nextStack);
		}

		return currentStack;
	}

	/**
	 * Crafts the item and decreases crafters if the container is closed -
	 * unreliable and has chance for dupe bugs now I see why the vanilla
	 * crafting table works as it does...
	 */
	@Override
	public void onContainerClosed(EntityPlayer player)
	{
		super.onContainerClosed(player);
		InventoryPlayer inventoryplayer = player.inventory;

		if (inventoryplayer.getItemStack() != null)
		{
			player.dropPlayerItemWithRandomChoice(inventoryplayer.getItemStack(), false);
			inventoryplayer.setItemStack((ItemStack) null);
		}
		this.cuttingTable.hasIngredientDecreased = false;

		return;
	}

	@Override
	public Slot getSlot(int slot)
	{
		if (slot == 0 || slot == 1)
		{
			this.onCraftMatrixChanged(this.cuttingTable);
		}
		this.onCraftMatrixChanged(this.cuttingTable);

		return (Slot) this.inventorySlots.get(slot);
	}

}
