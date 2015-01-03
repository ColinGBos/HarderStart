package com.vapourdrive.harderstart.events;

import java.util.Set;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class HS_ItemInfoAdditions
{
	/**
	 * Adds things to the item tooltips currently adds little plug for a stick
	 * being a basic tool as well as ore dictionary listings
	 * TODO make configurable and pretty things up
	 * 
	 * @param event
	 */
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void itemToolTipAddition(ItemTooltipEvent event)
	{
		ItemStack stack = event.itemStack;

		if (stack != null)
		{
			if (OreDictionary.itemMatches(new ItemStack(Items.stick), stack, true))
			{
				event.toolTip.add("Rudimentary weapon and tool");
			}
			//event.toolTip.add(stack.getItem().getUnlocalizedName() + " " + stack.getItemDamage());

			int[] oreIDs = OreDictionary.getOreIDs(stack);
			if (oreIDs.length > 0)
			{
				String oreDictionary = "OreDictionary:";
				for (int i = 0; i < oreIDs.length; i++)
				{
					oreDictionary = oreDictionary + " " + OreDictionary.getOreName(oreIDs[i]);
				}
				event.toolTip.add(oreDictionary);
			}

			Set<String> tooltags = stack.getItem().getToolClasses(stack);
			if (!tooltags.isEmpty())
			{
				event.toolTip.add("ToolTypes: " + tooltags.toString());
			}
		}

		return;
	}
}
