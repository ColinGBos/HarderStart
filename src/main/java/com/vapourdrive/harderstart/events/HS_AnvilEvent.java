package com.vapourdrive.harderstart.events;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class HS_AnvilEvent
{
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void anvilEvent(AnvilUpdateEvent event)
	{
		ItemStack leftInput = event.left;
		ItemStack rightInput = event.right;

		if (leftInput == null || rightInput == null)
		{
			return;
		}
		
		Map leftEnchantments = EnchantmentHelper.getEnchantments(leftInput);
		Map rightEnchantments = EnchantmentHelper.getEnchantments(rightInput);
		Collection leftEnchantmentCollection = leftEnchantments.values();
		Collection rightEnchantmentCollection = rightEnchantments.values();
		
		if(!leftEnchantmentCollection.containsAll(rightEnchantmentCollection))
		{
			leftEnchantments.putAll(rightEnchantments);
			//ItemStack result = new ItemStack(leftInput.getItem(), 1, leftInput.getItemDamage());
			ItemStack result = leftInput.copy();
			EnchantmentHelper.setEnchantments(leftEnchantments, result);
			
			event.output = result;
			event.cost = 1;
		}
	}

	public static int getEnchantmentCost(ItemStack stack)
	{
		// derived from ContainerRepair
		Map enchantmentMap = EnchantmentHelper.getEnchantments(stack);
		Iterator iterator = enchantmentMap.keySet().iterator();
		while (iterator.hasNext())
		{
			int currentEnchant = ((Integer) iterator.next()).intValue();
			Enchantment enchantment = Enchantment.enchantmentsList[currentEnchant];
			int level = ((Integer) enchantmentMap.get(Integer.valueOf(currentEnchant))).intValue();
			if (enchantment.canApply(stack))
			{
				if (level > enchantment.getMaxLevel())
				{
					level = enchantment.getMaxLevel();
				}
				int costPerLevel = 0;
				switch (enchantment.getWeight())
				{
					case 1:
						costPerLevel = 8;
						break;
					case 2:
						costPerLevel = 4;
					case 3:
					case 4:
					case 6:
					case 7:
					case 8:
					case 9:
					default:
						break;
					case 5:
						costPerLevel = 2;
						break;
					case 10:
						costPerLevel = 1;
				}
				return (costPerLevel * level);
			}
		}
		return 0;
	}
}
