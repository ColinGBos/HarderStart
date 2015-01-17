package com.vapourdrive.harderstart.blocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import com.vapourdrive.harderstart.itemblocks.CarbonItemBlock;
import com.vapourdrive.harderstart.itemblocks.CuttingTableItemBlock;
import com.vapourdrive.harderstart.itemblocks.FireBrickItemBlock;
import com.vapourdrive.harderstart.itemblocks.FireClayItemBlock;
import com.vapourdrive.harderstart.itemblocks.FlintItemBlock;
import com.vapourdrive.harderstart.itemblocks.FoodBucketItemBlock;
import com.vapourdrive.harderstart.itemblocks.GemItemBlock;
import com.vapourdrive.harderstart.itemblocks.RefinedMetalItemBlock;
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
	public static Block RefinedMetalBlock;
	public static Block RoughIron;
	public static Block RoughCopper;
	public static Block RoughTin;
	public static Block RoughBronze;
	public static Block RoughGold;
	public static Block RoughWroughtIron;
	public static Block RoughSteel;


	public static void init()
	{
		BlockFlint = new BlockFlint();
		CuttingTable = new CuttingTable();
		FoodBucketBlock = new FoodBucketBlock();
		GemRock = new GemRock();
		FireClay = new BlockFireClay();
		HardenedFireClay = new BlockFireBrick();
		CarbonBlock = new CarbonBlock();
		RefinedMetalBlock = new RefinedMetalBlock();
		RoughIron = new RoughMetal(HS_BlockInfo.RoughIron, 12, 1, Blocks.iron_block, 0, 100);
		RoughCopper = new RoughMetal(HS_BlockInfo.RoughCopper, 12, 0, HS_Blocks.RefinedMetalBlock, 0, 40);
		RoughTin = new RoughMetal(HS_BlockInfo.RoughTin, 12, 0, HS_Blocks.RefinedMetalBlock, 1, 40);
		RoughBronze = new RoughMetal(HS_BlockInfo.RoughBronze, 12, 0, HS_Blocks.RefinedMetalBlock, 2, 60);
		RoughGold = new RoughMetal(HS_BlockInfo.RoughGold, 12, 0, Blocks.gold_block, 0, 60);
		RoughWroughtIron = new RoughMetal(HS_BlockInfo.RoughWroughtIron, 12, 1, HS_Blocks.RefinedMetalBlock, 3, 110);
		RoughSteel = new RoughMetal(HS_BlockInfo.RoughSteel, 12, 1, HS_Blocks.RefinedMetalBlock, 4, 150);



		GameRegistry.registerBlock(BlockFlint, FlintItemBlock.class, HS_BlockInfo.FlintName);
		GameRegistry.registerBlock(CuttingTable, CuttingTableItemBlock.class, HS_BlockInfo.CuttingTableName);
		GameRegistry.registerBlock(FoodBucketBlock, FoodBucketItemBlock.class, HS_BlockInfo.FoodBucketBlockName);
		GameRegistry.registerBlock(GemRock, GemItemBlock.class, HS_BlockInfo.GemBlockName);
		GameRegistry.registerBlock(FireClay, FireClayItemBlock.class, HS_BlockInfo.FireClayName);
		GameRegistry.registerBlock(HardenedFireClay, FireBrickItemBlock.class, HS_BlockInfo.FireBrickName);
		GameRegistry.registerBlock(CarbonBlock, CarbonItemBlock.class, HS_BlockInfo.CarbonBlock);
		GameRegistry.registerBlock(RefinedMetalBlock, RefinedMetalItemBlock.class, HS_BlockInfo.RefinedMetalBlock);
		GameRegistry.registerBlock(RoughIron, RoughMetalItemBlock.class, HS_BlockInfo.RoughIron);
		GameRegistry.registerBlock(RoughCopper, RoughMetalItemBlock.class, HS_BlockInfo.RoughCopper);
		GameRegistry.registerBlock(RoughTin, RoughMetalItemBlock.class, HS_BlockInfo.RoughTin);
		GameRegistry.registerBlock(RoughGold, RoughMetalItemBlock.class, HS_BlockInfo.RoughGold);
		GameRegistry.registerBlock(RoughBronze, RoughMetalItemBlock.class, HS_BlockInfo.RoughBronze);
		GameRegistry.registerBlock(RoughWroughtIron, RoughMetalItemBlock.class, HS_BlockInfo.RoughWroughtIron);
		GameRegistry.registerBlock(RoughSteel, RoughMetalItemBlock.class, HS_BlockInfo.RoughSteel);


	}

	public static void registerRecipes()
	{

	}

}
