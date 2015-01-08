package com.vapourdrive.harderstart.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import org.apache.logging.log4j.Level;

import com.vapourdrive.harderstart.HarderStart;
import com.vapourdrive.harderstart.recipe.cuttingtable.CuttingTableRecipeManager;

public class CuttingTable_TE extends TileEntity implements ISidedInventory
{
	public ItemStack[] cuttingTableSlots;
	public boolean hasIngredientDecreased = false;

	public CuttingTable_TE()
	{
		cuttingTableSlots = new ItemStack[6];
	}

	@Override
	public int getSizeInventory()
	{
		return cuttingTableSlots.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return cuttingTableSlots[slot];
	}

	/**
	 * Whenever a stack gets its size decreased this is called currently used to
	 * update the recipe
	 */
	@Override
	public ItemStack decrStackSize(int slot, int decrement)
	{
		if (this.cuttingTableSlots[slot] != null)
		{
			ItemStack itemstack;

			itemstack = this.cuttingTableSlots[slot];
			this.cuttingTableSlots[slot] = null;

			if (slot > 1 && this.hasIngredientDecreased == false)
			{
				this.hasIngredientDecreased = true;
				decrCrafters();
				shouldReset(slot);
			}
			else if (!this.hasIngredientDecreased)
			{
				clearResult();
			}

			return itemstack;
		}

		return null;
	}

	/**
	 * checks the result slots to see if they are empty, if they marks
	 * ingredients as not decreased TODO - rework, broken implementation of slot
	 * stack manipulation is broken
	 * 
	 * @param slot
	 */
	public void shouldReset(int slot)
	{
		Boolean hasFullSlot = false;

		for (int i = 2; i < this.getSizeInventory(); i++)
		{
			if (this.getStackInSlot(i) != null && i != slot)
			{
				hasFullSlot = true;
			}
		}

		if (hasFullSlot != true)
		{
			this.hasIngredientDecreased = false;
		}

	}

	/**
	 * damages tool, consumes one item from ingredient slot
	 */
	public void decrCrafters()
	{
		ItemStack tool = this.getStackInSlot(0);
		if (tool != null)
		{
			int Damage = tool.getItemDamage();
			NBTTagCompound tag = tool.getTagCompound();

			ItemStack returnedtool = new ItemStack(tool.getItem(), 1, Damage + 1);
			if (tag != null)
			{
				returnedtool.stackTagCompound = tag;
			}
			if (Damage + 1 == tool.getMaxDamage())
			{
				this.setInventorySlotContents(0, null);
			}
			else
			{
				this.setInventorySlotContents(0, returnedtool);
			}
		}

		ItemStack ingredient = this.getStackInSlot(1);
		if (ingredient != null)
		{
			int amount = ingredient.stackSize;
			int meta = ingredient.getItemDamage();
			if (amount == 1)
			{
				this.setInventorySlotContents(1, null);
			}
			else
			{
				this.setInventorySlotContents(1, new ItemStack(ingredient.getItem(), amount - 1, meta));
			}
		}
	}

	/**
	 * force crafts a stack when you close the inventory, not guaranteed to work
	 * unfortunately
	 */
	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		if (this.hasIngredientDecreased == false && isResult())
		{
			decrCrafters();
			this.hasIngredientDecreased = true;
		}
		if (this.cuttingTableSlots[slot] != null)
		{
			ItemStack itemstack = this.cuttingTableSlots[slot];
			this.cuttingTableSlots[slot] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		ItemStack tool = this.getStackInSlot(0);
		cuttingTableSlots[slot] = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit())
		{
			stack.stackSize = getInventoryStackLimit();
		}
		if (slot == 0 || slot == 1)
		{
			if (stack != null)
			{
				if (tool == null || (tool.getItem() != stack.getItem()))
				{
					this.updateCraftingMatrix(0);
				}
			}
		}

		return;
	}

	@Override
	public String getInventoryName()
	{
		return "InventoryCuttingTable";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
	}

	@Override
	public void openInventory()
	{
	}

	@Override
	public void closeInventory()
	{
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		if (slot > 1)
		{
			return false;
		}
		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		NBTTagList nbttaglist = tag.getTagList("Items", 10);
		this.cuttingTableSlots = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.cuttingTableSlots.length)
			{
				this.cuttingTableSlots[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.cuttingTableSlots.length; ++i)
		{
			if (this.cuttingTableSlots[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.cuttingTableSlots[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		tag.setTag("Items", nbttaglist);
	}

	@Override
	public void updateEntity()
	{
	}

	/**
	 * Should only be called after the updates to the slot/inventory have taken
	 * place namely removal of stacks etc.
	 * 
	 * @return
	 */
	public boolean updateCraftingMatrix(int excludedSlot)
	{
		for (int i = 2; i < 6; i++)
		{
			if (this.getStackInSlot(i) != null && i != excludedSlot)
			{
				HarderStart.log.log(Level.INFO, "full stack slot: " + i);
				return false;
			}
		}

		if (this.getStackInSlot(0) != null && this.getStackInSlot(1) != null)
		{
			ItemStack stack = this.getStackInSlot(1);
			ItemStack tool = this.getStackInSlot(0);

			if (CuttingTableRecipeManager.getInstance().getValidRecipe(stack, tool) != null)
			{
				ItemStack[] resultArray = CuttingTableRecipeManager.getInstance().getValidRecipe(stack, tool);
				int resultNumber = resultArray.length;
				this.hasIngredientDecreased = false;
				doCraft(resultArray, resultNumber);
				HarderStart.log.log(Level.INFO, "placed stacks");
				return true;
			}
		}

		return false;
	}

	/**
	 * places result stacks
	 * 
	 * @param resultArray
	 * @param resultNumber
	 */
	public void doCraft(ItemStack[] resultArray, int resultNumber)
	{
		for (int i = 2; i < resultNumber + 2; i++)
		{
			ItemStack stack = resultArray[i - 2];
			int number = stack.stackSize;
			int meta = stack.getItemDamage();
			if (stack != null)
			{
				this.setInventorySlotContents(i, new ItemStack(stack.getItem(), number, meta));
			}
		}
	}

	/**
	 * clears the result stacks for assurance of emptiness
	 */
	public void clearResult()
	{
		this.setInventorySlotContents(2, null);
		this.setInventorySlotContents(3, null);
		this.setInventorySlotContents(4, null);
		this.setInventorySlotContents(5, null);
	}

	/**
	 * @return whether or not something exists in the result slots
	 */
	public boolean isResult()
	{
		for (int i = 0; i < 4; i++)
		{
			if (this.getStackInSlot(i) != null)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
		int[] array =
		{
				2, 3, 4, 5
		};
		return array;
	}

	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack stack, int p_102007_3_)
	{
		return false;
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack stack, int p_102008_3_)
	{
		return false;
	}
}