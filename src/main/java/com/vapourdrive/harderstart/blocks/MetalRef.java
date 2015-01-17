package com.vapourdrive.harderstart.blocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class MetalRef
{
	public static final String[] RoughMetalNames =
	{
			"copper", "tin", "bronze", "iron", "wrought_iron", "gold", "steel"
	};
	public static final Block[] RefinedBlock =
	{
			HS_Blocks.RefinedMetalBlock, HS_Blocks.RefinedMetalBlock, HS_Blocks.RefinedMetalBlock, Blocks.iron_block,
			HS_Blocks.RefinedMetalBlock, Blocks.gold_block, HS_Blocks.RefinedMetalBlock
	};
	public static final int[] ResultMeta =
	{
			0, 1, 2, 0, 3, 0, 4
	};
	public static final int[] MeltTemperatures =
	{
			0, 0, 0, 1, 1, 0, 1
	};
	public static final String[] IngotItems =
	{};
}
