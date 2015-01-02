package com.vapourdrive.harderstart.oredictionary;

import net.minecraft.init.Blocks;
import net.minecraftforge.oredict.OreDictionary;

import com.vapourdrive.harderstart.items.HS_Items;


public class OreDictionaryRegistry
{
	/**
	 * register everything in the oredictionary
	 */
	public static void init()
	{
		//Blocks
		OreDictionary.registerOre("netherrack", Blocks.netherrack);
		OreDictionary.registerOre("stoneEnd", Blocks.end_stone);
		
		//Rubbles
		OreDictionary.registerOre("oreIron", HS_Items.ironOre_rubble);
		OreDictionary.registerOre("oreCoal", HS_Items.coalOre_rubble);
		OreDictionary.registerOre("oreGold", HS_Items.goldOre_rubble);
		OreDictionary.registerOre("oreDiamond", HS_Items.diamondOre_rubble);
		OreDictionary.registerOre("oreEmerald", HS_Items.emeraldOre_rubble);
		OreDictionary.registerOre("oreLapis", HS_Items.lapisOre_rubble);
		OreDictionary.registerOre("oreRedstone", HS_Items.redstoneOre_rubble);
		OreDictionary.registerOre("oreCopper", HS_Items.copperOre_rubble);
		OreDictionary.registerOre("oreTin", HS_Items.tinOre_rubble);
		OreDictionary.registerOre("oreSilver", HS_Items.silverOre_rubble);
		OreDictionary.registerOre("oreNickel", HS_Items.nickelOre_rubble);
		OreDictionary.registerOre("oreLead", HS_Items.leadOre_rubble);
		OreDictionary.registerOre("stoneEnd", HS_Items.stoneEnd_rubble);
		OreDictionary.registerOre("netherrack", HS_Items.netherrack_rubble);
		OreDictionary.registerOre("oreQuartz", HS_Items.quartzOre_rubble);
		OreDictionary.registerOre("oreAluminum", HS_Items.aluminumOre_rubble);
		OreDictionary.registerOre("oreSulfur", HS_Items.sulfurOre_rubble);
		OreDictionary.registerOre("oreSaltpeter", HS_Items.saltpeterOre_rubble);
		OreDictionary.registerOre("oreCobalt", HS_Items.cobaltOre_rubble);
		OreDictionary.registerOre("oreArdite", HS_Items.arditeOre_rubble);
		OreDictionary.registerOre("oreApatite", HS_Items.apatiteOre_rubble);
		OreDictionary.registerOre("oreUranium", HS_Items.uraniumOre_rubble);
		OreDictionary.registerOre("oreYellorite", HS_Items.yelloriteOre_rubble);
		OreDictionary.registerOre("oreYellorium", HS_Items.yelloriteOre_rubble);
		OreDictionary.registerOre("orePlatinum", HS_Items.platinumOre_rubble);
		OreDictionary.registerOre("oreMithril", HS_Items.mithrilOre_rubble);
		OreDictionary.registerOre("stoneEnd", HS_Items.stoneEnd_rubble);



	}

}
