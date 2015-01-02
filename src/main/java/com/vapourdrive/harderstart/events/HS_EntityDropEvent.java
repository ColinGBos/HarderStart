package com.vapourdrive.harderstart.events;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import com.vapourdrive.harderstart.items.HS_Items;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class HS_EntityDropEvent
{
	/**
	 * Case by case dealings with vanilla farm mob drops
	 * TODO baby animals still drop things
	 * TODO implement some sort of looting reward mechanic
	 * @param event
	 */
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void dropEvent(LivingDropsEvent event)
	{
		if (event.entityLiving.worldObj.isRemote)
		{
			return;
		}
		Entity entity = event.entity;
		Random rand = new Random();
		
		if (entity instanceof EntityCow)
		{
			event.drops.clear();
			event.entityLiving.dropItem(HS_Items.dead_cow, 1);
		}
		else if (entity instanceof EntitySheep)
		{
			event.drops.clear();
			event.entityLiving.dropItem(HS_Items.dead_sheep, 1);
		}
		else if (entity instanceof EntityPig)
		{
			event.drops.clear();
			event.entityLiving.dropItem(HS_Items.dead_pig, 1);
		}
		else if (entity instanceof EntityHorse)
		{
			event.drops.clear();
			event.entityLiving.dropItem(HS_Items.dead_horse, 1);
		}
		else if (entity instanceof EntityChicken)
		{
			event.drops.clear();
			event.entityLiving.dropItem(Items.feather, rand.nextInt(4) + 2);
			event.entityLiving.dropItem(HS_Items.dead_chicken, 1);
		}
	}
}
