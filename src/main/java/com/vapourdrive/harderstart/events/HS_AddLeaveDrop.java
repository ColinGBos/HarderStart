package com.vapourdrive.harderstart.events;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class HS_AddLeaveDrop
{
	/**
	 * Simply causes sticks to fall from some leaves
	 * Oredictionaried leaves that is
	 * @param event
	 */
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void addLeafDrop(BlockEvent.HarvestDropsEvent event)
	{
		if (event.harvester != null && !event.harvester.capabilities.isCreativeMode && !event.harvester.worldObj.isRemote
				&& !event.isCanceled())
		{
			if (OreDictionary.itemMatches(new ItemStack(Blocks.leaves), new ItemStack(event.block), true))
			{
				Random random = new Random();

				if (random.nextInt(8) == 0)
				{
					event.drops.add(new ItemStack(Items.stick));
				}
			}
		}
		return;
	}

}
