package com.vapourdrive.harderstart.events;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class HS_BreakSpeed
{
	/**
	 * Damages the player if they try to punch certain blocks
	 * Slow then down while punching other
	 * TODO consider moving from block materials to block hardnesses
	 * @param event
	 */
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void playerPunchPain(PlayerEvent.BreakSpeed event)
	{
		EntityPlayer player = event.entityPlayer;
		Block block = event.block;
		Material material = block.getMaterial();
		ItemStack itemstack = player.getCurrentEquippedItem();

		if (material == Material.wood || material == Material.anvil || material == Material.rock || material == Material.iron)
		{
			if (itemstack == null)
			{
				player.attackEntityFrom(DamageSource.generic, 1.5F);
				event.newSpeed = 0.0F;
			}
			if (itemstack != null && itemstack.getItem().getToolClasses(itemstack).isEmpty())
			{
				event.newSpeed = 0.0F;
			}
		}
		else
		{
			if (itemstack == null || itemstack.getItem().getToolClasses(itemstack).isEmpty())
			{
				event.newSpeed = 0.25F;
			}
			/**
			 * A stick is your first tool, like real life survival
			 */
			if (OreDictionary.itemMatches(new ItemStack(Items.stick), itemstack, true))
			{
				event.newSpeed = 0.5F;
			}
		}

		return;
	}

}
