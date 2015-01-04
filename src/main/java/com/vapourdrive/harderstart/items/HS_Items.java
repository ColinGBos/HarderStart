package com.vapourdrive.harderstart.items;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.EnumChatFormatting;
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
	
	//rubbles
	public static Item stone_rubble;
	public static Item stoneEnd_rubble;
	public static Item netherrack_rubble;
	public static Item quartzOre_rubble;
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
	public static Item darkIronOre_rubble;
	public static Item aluminumOre_rubble;
	public static Item sulfurOre_rubble;
	public static Item saltpeterOre_rubble;
	public static Item cobaltOre_rubble;
	public static Item arditeOre_rubble;
	public static Item apatiteOre_rubble;
	public static Item uraniumOre_rubble;
	public static Item yelloriteOre_rubble;
	public static Item platinumOre_rubble;
	public static Item mithrilOre_rubble;
	
	//Gems
	public static Item gem_Heliodor;
	public static Item gem_Pyrolusite;
	public static Item gem_Aventurine;
	public static Item gem_Nephrite;
	public static Item gem_Hambergite;
	public static Item gem_Amazonite;
	public static Item gem_Aquamarine;
	public static Item gem_Hessonite;
	public static Item gem_Jadeite;
	public static Item gem_Hematite;
	public static Item gem_Spodumene;
	public static Item gem_Citrine;
	public static Item gem_Flourite;
	public static Item gem_Pyrite;
	public static Item gem_Kornerupine;
	public static Item gem_Prehnite;
	public static Item gem_Dioptase;
	public static Item gem_Agate;
	public static Item gem_Variscite;
	public static Item gem_Almandine;
	public static Item gem_Pyrope;
	public static Item gem_Alexandrite;
	public static Item gem_Goshenite;
	public static Item gem_Tourmaline;




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
		
		//Rubbles
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
		netherrack_rubble = new ItemRubble(HS_ItemInfo.NetherrackRubbleName);
		stoneEnd_rubble = new ItemRubble(HS_ItemInfo.EndStoneRubbleName);
		darkIronOre_rubble = new ItemRubble(HS_ItemInfo.DarkIronOreRubbleName);
		aluminumOre_rubble = new ItemRubble(HS_ItemInfo.AluminumOreRubbleName);
		sulfurOre_rubble = new ItemRubble(HS_ItemInfo.SulfurOreRubbleName);
		saltpeterOre_rubble = new ItemRubble(HS_ItemInfo.SaltpeterOreRubbleName);
		cobaltOre_rubble = new ItemRubble(HS_ItemInfo.CobaltOreRubbleName);
		arditeOre_rubble = new ItemRubble(HS_ItemInfo.ArditeOreRubbleName);
		apatiteOre_rubble = new ItemRubble(HS_ItemInfo.ApatiteOreRubbleName);
		uraniumOre_rubble = new ItemRubble(HS_ItemInfo.UraniumOreRubbleName);
		yelloriteOre_rubble = new ItemRubble(HS_ItemInfo.YelloriteOreRubbleName);
		platinumOre_rubble = new ItemRubble(HS_ItemInfo.PlatinumOreRubbleName);
		mithrilOre_rubble = new ItemRubble(HS_ItemInfo.MithrilOreRubbleName);
		quartzOre_rubble = new ItemRubble(HS_ItemInfo.QuartzOreRubbleName);
		
		//Gems
		gem_Heliodor = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.HeliodorGemName, Enchantment.respiration);
		gem_Pyrolusite = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.PyrolusiteGemName, Enchantment.baneOfArthropods);
		gem_Aventurine = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.AventurineGemName, Enchantment.protection);
		gem_Nephrite = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.NephriteGemName, Enchantment.knockback);
		gem_Hambergite = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.HambergiteGemName, Enchantment.sharpness);
		gem_Amazonite = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.AmazoniteGemName, Enchantment.projectileProtection);
		gem_Aquamarine = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.AquamarineGemName, Enchantment.aquaAffinity);
		gem_Hessonite = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.HessoniteGemName, Enchantment.power);
		gem_Jadeite = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.JadeiteGemName, Enchantment.punch);
		gem_Hematite = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.HematiteGemName, Enchantment.smite);
		gem_Spodumene = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.SpodumeneGemName, Enchantment.field_151369_A);
		gem_Citrine = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.CitrineGemName, Enchantment.efficiency);
		gem_Flourite = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.FlouriteGemName, Enchantment.fortune);
		gem_Pyrite = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.PyriteGemName, Enchantment.featherFalling);
		gem_Kornerupine = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.KornerupineGemName, Enchantment.field_151370_z);
		gem_Prehnite = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.PrehniteGemName, Enchantment.infinity);
		gem_Dioptase = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.DioptaseGemName, Enchantment.blastProtection);
		gem_Agate = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.AgateGemName, Enchantment.fireProtection);
		gem_Variscite = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.VarisciteGemName, Enchantment.looting);
		gem_Almandine = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.AlmandineGemName, Enchantment.thorns);
		gem_Pyrope = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.PyropeGemName, Enchantment.silkTouch);
		gem_Alexandrite = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.AlexandriteGemName, Enchantment.fireAspect);
		gem_Goshenite = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.GosheniteGemName, Enchantment.unbreaking);
		gem_Tourmaline = new ItemEnchantmentGem(EnumChatFormatting.DARK_PURPLE, HS_ItemInfo.TourmalineGemName, Enchantment.flame);



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
		
		//rubbles
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
		GameRegistry.registerItem(netherrack_rubble, HS_ItemInfo.NetherrackRubbleName);
		GameRegistry.registerItem(stoneEnd_rubble, HS_ItemInfo.EndStoneRubbleName);
		GameRegistry.registerItem(darkIronOre_rubble, HS_ItemInfo.DarkIronOreRubbleName);
		GameRegistry.registerItem(aluminumOre_rubble, HS_ItemInfo.AluminumOreRubbleName);
		GameRegistry.registerItem(sulfurOre_rubble, HS_ItemInfo.SulfurOreRubbleName);
		GameRegistry.registerItem(saltpeterOre_rubble, HS_ItemInfo.SaltpeterOreRubbleName);
		GameRegistry.registerItem(cobaltOre_rubble, HS_ItemInfo.CobaltOreRubbleName);
		GameRegistry.registerItem(arditeOre_rubble, HS_ItemInfo.ArditeOreRubbleName);
		GameRegistry.registerItem(apatiteOre_rubble, HS_ItemInfo.ApatiteOreRubbleName);
		GameRegistry.registerItem(uraniumOre_rubble, HS_ItemInfo.UraniumOreRubbleName);
		GameRegistry.registerItem(yelloriteOre_rubble, HS_ItemInfo.YelloriteOreRubbleName);
		GameRegistry.registerItem(platinumOre_rubble, HS_ItemInfo.PlatinumOreRubbleName);
		GameRegistry.registerItem(mithrilOre_rubble, HS_ItemInfo.MithrilOreRubbleName);
		GameRegistry.registerItem(quartzOre_rubble, HS_ItemInfo.QuartzOreRubbleName);
		
		//gems
		GameRegistry.registerItem(gem_Heliodor, HS_ItemInfo.HeliodorGemName);
		GameRegistry.registerItem(gem_Pyrolusite, HS_ItemInfo.PyrolusiteGemName);
		GameRegistry.registerItem(gem_Aventurine, HS_ItemInfo.AventurineGemName);
		GameRegistry.registerItem(gem_Nephrite, HS_ItemInfo.NephriteGemName);
		GameRegistry.registerItem(gem_Hambergite, HS_ItemInfo.HambergiteGemName);
		GameRegistry.registerItem(gem_Amazonite, HS_ItemInfo.AmazoniteGemName);
		GameRegistry.registerItem(gem_Aquamarine, HS_ItemInfo.AquamarineGemName);
		GameRegistry.registerItem(gem_Hessonite, HS_ItemInfo.HessoniteGemName);
		GameRegistry.registerItem(gem_Jadeite, HS_ItemInfo.JadeiteGemName);
		GameRegistry.registerItem(gem_Hematite, HS_ItemInfo.HematiteGemName);
		GameRegistry.registerItem(gem_Spodumene, HS_ItemInfo.SpodumeneGemName);
		GameRegistry.registerItem(gem_Citrine, HS_ItemInfo.CitrineGemName);
		GameRegistry.registerItem(gem_Flourite, HS_ItemInfo.FlouriteGemName);
		GameRegistry.registerItem(gem_Pyrite, HS_ItemInfo.PyriteGemName);
		GameRegistry.registerItem(gem_Kornerupine, HS_ItemInfo.KornerupineGemName);
		GameRegistry.registerItem(gem_Prehnite, HS_ItemInfo.PrehniteGemName);
		GameRegistry.registerItem(gem_Dioptase, HS_ItemInfo.DioptaseGemName);
		GameRegistry.registerItem(gem_Agate, HS_ItemInfo.AgateGemName);
		GameRegistry.registerItem(gem_Variscite, HS_ItemInfo.VarisciteGemName);
		GameRegistry.registerItem(gem_Almandine, HS_ItemInfo.AlmandineGemName);
		GameRegistry.registerItem(gem_Pyrope, HS_ItemInfo.PyropeGemName);
		GameRegistry.registerItem(gem_Alexandrite, HS_ItemInfo.AlexandriteGemName);
		GameRegistry.registerItem(gem_Goshenite, HS_ItemInfo.GosheniteGemName);
		GameRegistry.registerItem(gem_Tourmaline, HS_ItemInfo.TourmalineGemName);


	}

	public static void registerRecipes()
	{

	}

}
