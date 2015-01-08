package com.vapourdrive.harderstart.events;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.oredict.OreDictionary;

import com.vapourdrive.harderstart.items.HS_Items;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class HS_AddVanillaDrop
{
	/**
	 * Simply causes sticks to fall from some leaves Oredictionaried leaves that
	 * is
	 * 
	 * @param event
	 */
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void addVanillaDrop(BlockEvent.HarvestDropsEvent event)
	{
		if (event.harvester != null && !event.harvester.capabilities.isCreativeMode && !event.harvester.worldObj.isRemote
				&& !event.isCanceled())
		{
			Random random = new Random();

			if (OreDictionary.itemMatches(new ItemStack(Blocks.leaves), new ItemStack(event.block), true))
			{
				int option = random.nextInt(16);
				if (option > 12)
				{
					event.drops.add(new ItemStack(Items.stick));
				}
				else if (event.harvester.getCurrentEquippedItem() == null && option < 10)
				{
					event.drops.add(new ItemStack(HS_Items.food_cricket));
				}
			}
			else if (event.block == Blocks.dirt || event.block == Blocks.grass)
			{
				if (event.harvester.getCurrentEquippedItem() == null && random.nextFloat() > 0.5F)
				{
					event.drops.add(new ItemStack(HS_Items.food_grub));
				}

			}
		}
		return;
	}

}
