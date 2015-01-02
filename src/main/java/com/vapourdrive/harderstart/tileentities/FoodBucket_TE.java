package com.vapourdrive.harderstart.tileentities;

import com.vapourdrive.harderstart.blocks.HS_Blocks;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class FoodBucket_TE extends TileEntity
{
	public int foodValue = Item.getItemFromBlock(HS_Blocks.FoodBucketBlock).getMaxDamage();
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setInteger("FoodValue", this.foodValue);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		this.foodValue = tag.getInteger("FoodValue");
	}
}
