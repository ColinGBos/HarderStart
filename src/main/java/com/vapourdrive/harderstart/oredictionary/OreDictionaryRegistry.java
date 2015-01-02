package com.vapourdrive.harderstart.oredictionary;

import com.vapourdrive.harderstart.items.HS_Items;

import net.minecraftforge.oredict.OreDictionary;


public class OreDictionaryRegistry
{
	/**
	 * register everything in the oredictionary
	 */
	public static void init()
	{
		//Blocks
		//OreDictionary.registerOre("oreRedstone", new ItemStack(Blocks.lit_redstone_ore));
		//It doesn't want to register!
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

	}

}
