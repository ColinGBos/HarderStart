package com.vapourdrive.harderstart.items;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.registry.GameRegistry;

public class HS_Items
{
	public static ToolMaterial FLINT = EnumHelper.addToolMaterial("BASIC_FLINT", 0, 36, 1.0F, 0.5F, 0);

	public static Item flint_hatchet;
	public static Item flint_spear;
	public static Item rock;
	public static Item flint_knife;
	public static Item iron_knife;
	public static Item diamond_knife;
	public static Item dead_cow;
	public static Item dead_horse;
	public static Item dead_pig;
	public static Item dead_sheep;
	public static Item dead_chicken;
	public static Item rock_mallet;
	public static Item diamond_mallet;
	public static Item iron_mallet;
	public static Item stone_rubble;
	public static Item ironOre_rubble;
	public static Item coalOre_rubble;
	public static Item goldOre_rubble;
	public static Item diamondOre_rubble;
	public static Item emeraldOre_rubble;
	public static Item redstoneOre_rubble;
	public static Item lapisOre_rubble;
	public static Item copperOre_rubble;
	public static Item tinOre_rubble;
	public static Item silverOre_rubble;
	public static Item nickelOre_rubble;
	public static Item leadOre_rubble;

	


	public static void init()
	{
		flint_hatchet = new FlintHatchet(FLINT);
		flint_spear = new FlintSpear(FLINT);
		flint_knife = new Knife(FLINT, HS_ItemInfo.FlintKnifeName);
		iron_knife = new Knife(ToolMaterial.IRON, HS_ItemInfo.IronKnifeName);
		diamond_knife = new Knife(ToolMaterial.EMERALD, HS_ItemInfo.DiamondKnifeName);
		dead_cow = new LogiclessItem(HS_ItemInfo.DeadCowName);
		dead_horse = new LogiclessItem(HS_ItemInfo.DeadHorseName);
		dead_pig = new LogiclessItem(HS_ItemInfo.DeadPigName);
		dead_sheep = new LogiclessItem(HS_ItemInfo.DeadSheepName);
		dead_chicken = new LogiclessItem(HS_ItemInfo.DeadChickenName);
		rock_mallet = new Mallet(FLINT, HS_ItemInfo.RockMalletName);
		iron_mallet = new Mallet(ToolMaterial.IRON, HS_ItemInfo.IronMalletName);
		diamond_mallet = new Mallet(ToolMaterial.EMERALD, HS_ItemInfo.DiamondMalletName);
		stone_rubble = new ItemRubble(HS_ItemInfo.RubbleName);
		ironOre_rubble = new ItemRubble(HS_ItemInfo.IronOreRubbleName);
		coalOre_rubble = new ItemRubble(HS_ItemInfo.CoalOreRubbleName);
		goldOre_rubble = new ItemRubble(HS_ItemInfo.GoldOreRubbleName);
		diamondOre_rubble = new ItemRubble(HS_ItemInfo.DiamondOreRubbleName);
		emeraldOre_rubble = new ItemRubble(HS_ItemInfo.EmeraldOreRubbleName);
		redstoneOre_rubble = new ItemRubble(HS_ItemInfo.RedstoneOreRubbleName);
		lapisOre_rubble = new ItemRubble(HS_ItemInfo.LapisOreRubbleName);
		copperOre_rubble = new ItemRubble(HS_ItemInfo.CopperOreRubbleName);
		tinOre_rubble = new ItemRubble(HS_ItemInfo.TinOreRubbleName);
		silverOre_rubble = new ItemRubble(HS_ItemInfo.SilverOreRubbleName);
		leadOre_rubble = new ItemRubble(HS_ItemInfo.LeadOreRubbleName);
		nickelOre_rubble = new ItemRubble(HS_ItemInfo.NickelOreRubbleName);

		GameRegistry.registerItem(flint_hatchet, HS_ItemInfo.FlintHatchetName);
		GameRegistry.registerItem(flint_spear, HS_ItemInfo.FlintSpearName);
		GameRegistry.registerItem(flint_knife, HS_ItemInfo.FlintKnifeName);
		GameRegistry.registerItem(dead_cow, HS_ItemInfo.DeadCowName);
		GameRegistry.registerItem(dead_horse, HS_ItemInfo.DeadHorseName);
		GameRegistry.registerItem(dead_pig, HS_ItemInfo.DeadPigName);
		GameRegistry.registerItem(dead_sheep, HS_ItemInfo.DeadSheepName);
		GameRegistry.registerItem(dead_chicken, HS_ItemInfo.DeadChickenName);
		GameRegistry.registerItem(iron_knife, HS_ItemInfo.IronKnifeName);
		GameRegistry.registerItem(diamond_knife, HS_ItemInfo.DiamondKnifeName);
		GameRegistry.registerItem(rock_mallet, HS_ItemInfo.RockMalletName);
		GameRegistry.registerItem(stone_rubble, HS_ItemInfo.RubbleName);
		GameRegistry.registerItem(iron_mallet, HS_ItemInfo.IronMalletName);
		GameRegistry.registerItem(diamond_mallet, HS_ItemInfo.DiamondMalletName);
		GameRegistry.registerItem(ironOre_rubble, HS_ItemInfo.IronOreRubbleName);
		GameRegistry.registerItem(coalOre_rubble, HS_ItemInfo.CoalOreRubbleName);
		GameRegistry.registerItem(goldOre_rubble, HS_ItemInfo.GoldOreRubbleName);
		GameRegistry.registerItem(diamondOre_rubble, HS_ItemInfo.DiamondOreRubbleName);
		GameRegistry.registerItem(emeraldOre_rubble, HS_ItemInfo.EmeraldOreRubbleName);
		GameRegistry.registerItem(redstoneOre_rubble, HS_ItemInfo.RedstoneOreRubbleName);
		GameRegistry.registerItem(lapisOre_rubble, HS_ItemInfo.LapisOreRubbleName);
		GameRegistry.registerItem(copperOre_rubble, HS_ItemInfo.CopperOreRubbleName);
		GameRegistry.registerItem(tinOre_rubble, HS_ItemInfo.TinOreRubbleName);
		GameRegistry.registerItem(silverOre_rubble, HS_ItemInfo.SilverOreRubbleName);
		GameRegistry.registerItem(leadOre_rubble, HS_ItemInfo.LeadOreRubbleName);
		GameRegistry.registerItem(nickelOre_rubble, HS_ItemInfo.NickelOreRubbleName);
	}

	public static void registerRecipes()
	{

	}

}