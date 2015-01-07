package com.vapourdrive.harderstart.events;

import net.minecraftforge.common.MinecraftForge;

public class HS_Events
{

	/**
	 * Initiates all of the smaller event classes, keeps things nice and clean
	 */
	public static void init()
	{
		MinecraftForge.EVENT_BUS.register(new HS_BreakSpeed());
		MinecraftForge.EVENT_BUS.register(new HS_AddVanillaDrop());
		MinecraftForge.EVENT_BUS.register(new HS_PlayerAttackEntity());
		MinecraftForge.EVENT_BUS.register(new HS_ItemInfoAdditions());
		MinecraftForge.EVENT_BUS.register(new HS_EntityDropEvent());
		MinecraftForge.EVENT_BUS.register(new HS_AnvilEvent());

	}

}
