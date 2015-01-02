package com.vapourdrive.harderstart.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class HS_PlayerAttackEntity
{
	/**
	 * Makes stick truly a rudimentary weapon
	 * TODO add damage to stick info
	 * @param event
	 */
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void playerAttackEvent(AttackEntityEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		ItemStack itemstack = player.getCurrentEquippedItem();

		if (player != null)
		{
			if (!player.capabilities.disableDamage && itemstack != null)
			{
				if (OreDictionary.itemMatches(new ItemStack(Items.stick), itemstack, true))
				{
					event.target.attackEntityFrom(DamageSource.causePlayerDamage(player), 2.0F);
				}
			}
		}

		return;
	}
}
