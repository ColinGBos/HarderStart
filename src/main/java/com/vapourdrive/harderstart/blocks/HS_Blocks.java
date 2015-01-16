package com.vapourdrive.harderstart.blocks;

import net.minecraft.block.Block;

import com.vapourdrive.harderstart.itemblocks.CarbonItemBlock;
import com.vapourdrive.harderstart.itemblocks.CuttingTableItemBlock;
import com.vapourdrive.harderstart.itemblocks.FireBrickItemBlock;
import com.vapourdrive.harderstart.itemblocks.FireClayItemBlock;
import com.vapourdrive.harderstart.itemblocks.FlintItemBlock;
import com.vapourdrive.harderstart.itemblocks.FoodBucketItemBlock;
import com.vapourdrive.harderstart.itemblocks.GemItemBlock;
import com.vapourdrive.harderstart.itemblocks.RoughMetalItemBlock;

import cpw.mods.fml.common.registry.GameRegistry;

public class HS_Blocks
{
	public static Block BlockFlint;
	public static Block CuttingTable;
	public static Block FoodBucketBlock;
	public static Block GemRock;
	public static Block FireClay;
	public static Block HardenedFireClay;
	public static Block CarbonBlock;
	public static Block RoughMetal;

	public static void init()
	{
		BlockFlint = new BlockFlint();
		CuttingTable = new CuttingTable();
		FoodBucketBlock = new FoodBucketBlock();
		GemRock = new GemRock();
		FireClay = new BlockFireClay();
		HardenedFireClay = new BlockFireBrick();
		CarbonBlock = new CarbonBlock();
		RoughMetal = new RoughMetalBlock();

		GameRegistry.registerBlock(BlockFlint, FlintItemBlock.class, HS_BlockInfo.FlintName);
		GameRegistry.registerBlock(CuttingTable, CuttingTableItemBlock.class, HS_BlockInfo.CuttingTableName);
		GameRegistry.registerBlock(FoodBucketBlock, FoodBucketItemBlock.class, HS_BlockInfo.FoodBucketBlockName);
		GameRegistry.registerBlock(GemRock, GemItemBlock.class, HS_BlockInfo.GemBlockName);
		GameRegistry.registerBlock(FireClay, FireClayItemBlock.class, HS_BlockInfo.FireClayName);
		GameRegistry.registerBlock(HardenedFireClay, FireBrickItemBlock.class, HS_BlockInfo.FireBrickName);
		GameRegistry.registerBlock(CarbonBlock, CarbonItemBlock.class, HS_BlockInfo.CarbonBlock);
		GameRegistry.registerBlock(RoughMetal, RoughMetalItemBlock.class, HS_BlockInfo.RougMetalBlock);
	}

	public static void registerRecipes()
	{

	}

}
