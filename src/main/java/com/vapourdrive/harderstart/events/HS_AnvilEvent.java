package com.vapourdrive.harderstart.events;

import java.util.Map;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;

import com.vapourdrive.harderstart.items.ItemEnchantmentGem;

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

		Item leftItem = leftInput.getItem();
		Item rightItem = rightInput.getItem();

		if (rightItem instanceof ItemEnchantmentGem)
		{
			handleAnvilGem(leftInput, leftItem, rightInput, rightItem, event);
		}
		//
		// Map leftEnchantments = EnchantmentHelper.getEnchantments(leftInput);
		// Map rightEnchantments =
		// EnchantmentHelper.getEnchantments(rightInput);
		// Collection leftEnchantmentCollection = leftEnchantments.values();
		// Collection rightEnchantmentCollection = rightEnchantments.values();
		//
		// if(!leftEnchantmentCollection.containsAll(rightEnchantmentCollection))
		// {
		// leftEnchantments.putAll(rightEnchantments);
		// ItemStack result = leftInput.copy();
		// EnchantmentHelper.setEnchantments(leftEnchantments, result);
		//
		// event.output = result;
		// event.cost = 1;
		// }
	}

	public void handleAnvilGem(ItemStack leftInput, Item leftItem, ItemStack rightInput, Item rightItem, AnvilUpdateEvent event)
	{
		if (!(leftItem instanceof ItemEnchantmentGem) && rightItem instanceof ItemEnchantmentGem)
		{
			ItemEnchantmentGem gem = (ItemEnchantmentGem) rightItem;
			if (!rightInput.isItemEnchanted())
			{
				if (leftItem.isItemTool(leftInput) && leftInput.getItemDamage() < (leftInput.getMaxDamage() / 4))
				{
					handleEnchantGem(leftInput, leftItem, rightInput, gem, event);
				}
			}
			else if (rightInput.isItemEnchanted())
			{
				handleEnchantTool(leftInput, leftItem, rightInput, gem, event);
			}
		}

	}

	public void handleEnchantGem(ItemStack toolStack, Item tool, ItemStack gemStack, ItemEnchantmentGem gem, AnvilUpdateEvent event)
	{
		Enchantment enchant = gem.getGemEnchantment();
		int enchantID = enchant.effectId;

		Map enchantMap = EnchantmentHelper.getEnchantments(toolStack);

		if (enchantMap.containsKey(enchantID))
		{
			int level = ((Integer) enchantMap.get(enchantID));

			ItemStack outputStack = new ItemStack(gem);
			outputStack.addEnchantment(enchant, level);

			event.output = outputStack;
			event.cost = getEnchantmentCost(enchant, level);
			event.materialCost = 1;
		}

	}

	public void handleEnchantTool(ItemStack toolStack, Item tool, ItemStack gemStack, ItemEnchantmentGem gem, AnvilUpdateEvent event)
	{
		Enchantment enchant = gem.getGemEnchantment();
		int enchantID = enchant.effectId;

		Map enchantMap = EnchantmentHelper.getEnchantments(gemStack);

		if (!EnchantmentHelper.getEnchantments(toolStack).containsKey(enchantID) && enchantMap.containsKey(enchantID)
				&& enchant.canApply(toolStack))
		{
			int level = ((Integer) enchantMap.get(enchantID));

			ItemStack outputStack = toolStack.copy();
			outputStack.addEnchantment(enchant, level);
			event.output = outputStack;
			event.cost = getEnchantmentCost(enchant, level);
			event.materialCost = 1;
		}
	}

	public static int getEnchantmentCost(Enchantment enchant, int level)
	{
		if (level > enchant.getMaxLevel())
		{
			level = enchant.getMaxLevel();
		}
		int costPerLevel = 0;
		switch (enchant.getWeight())
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
