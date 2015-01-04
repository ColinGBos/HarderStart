package com.vapourdrive.harderstart.blocks;

import net.minecraft.block.Block;

import com.vapourdrive.harderstart.itemblocks.CuttingTableItemBlock;
import com.vapourdrive.harderstart.itemblocks.FlintItemBlock;
import com.vapourdrive.harderstart.itemblocks.FoodBucketItemBlock;
import com.vapourdrive.harderstart.itemblocks.GemItemBlock;

import cpw.mods.fml.common.registry.GameRegistry;

public class HS_Blocks
{
	public static Block BlockFlint;
	public static Block CuttingTable;
	public static Block FoodBucketBlock;
	public static Block GemRock;

	public static void init()
	{
		BlockFlint = new BlockFlint();
		CuttingTable = new CuttingTable();
		FoodBucketBlock = new FoodBucketBlock();
		GemRock = new GemRock();

		GameRegistry.registerBlock(BlockFlint, FlintItemBlock.class, HS_BlockInfo.FlintName);
		GameRegistry.registerBlock(CuttingTable, CuttingTableItemBlock.class, HS_BlockInfo.CuttingTableName);
		GameRegistry.registerBlock(FoodBucketBlock, FoodBucketItemBlock.class,  HS_BlockInfo.FoodBucketBlockName);
		GameRegistry.registerBlock(GemRock, GemItemBlock.class, HS_BlockInfo.GemBlockName);
	}

	public static void registerRecipes()
	{
		
	}

}
