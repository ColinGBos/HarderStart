package com.vapourdrive.harderstart.tileentities;

import cpw.mods.fml.common.registry.GameRegistry;

public class HS_TileEntities
{
	public static void init()
	{
		GameRegistry.registerTileEntity(CuttingTable_TE.class, HS_TileEntityInfo.CuttingTable_TE_ID);
		GameRegistry.registerTileEntity(FoodBucket_TE.class, HS_TileEntityInfo.FoodBucket_TE_ID);

		return;
	}
}
