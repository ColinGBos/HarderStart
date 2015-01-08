package com.vapourdrive.harderstart;

import net.minecraft.creativetab.CreativeTabs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vapourdrive.harderstart.blocks.HS_Blocks;
import com.vapourdrive.harderstart.config.ConfigHandler;
import com.vapourdrive.harderstart.creativetabs.CreativeTabGem;
import com.vapourdrive.harderstart.creativetabs.CreativeTabHS;
import com.vapourdrive.harderstart.creativetabs.CreativeTabRubble;
import com.vapourdrive.harderstart.entities.HS_Entities;
import com.vapourdrive.harderstart.events.HS_Events;
import com.vapourdrive.harderstart.handlers.CraftingRecipeRemover;
import com.vapourdrive.harderstart.handlers.FurnaceRecipeHandler;
import com.vapourdrive.harderstart.interfaces.GuiHandler;
import com.vapourdrive.harderstart.items.HS_Items;
import com.vapourdrive.harderstart.oredictionary.OreDictionaryRegistry;
import com.vapourdrive.harderstart.proxies.CommonProxy;
import com.vapourdrive.harderstart.recipe.RecipeRegister;
import com.vapourdrive.harderstart.tileentities.HS_TileEntities;
import com.vapourdrive.harderstart.world.HS_WorldGenHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = HS_ModInfo.ModID, version = HS_ModInfo.VERSION)
public class HarderStart
{
	@Instance(HS_ModInfo.ModID)
	public static HarderStart instance;

	@SidedProxy(clientSide = "com.vapourdrive.harderstart.proxies.ClientProxy", serverSide = "com.vapourdrive.harderstart.proxies.CommonProxy")
	public static CommonProxy proxy;

	public static CreativeTabs tabharderstart;
	public static CreativeTabs tabRubble;
	public static CreativeTabs tabGem;
	public static String configPath;

	public static final Logger log = LogManager.getLogger(HS_ModInfo.ModName);

	@EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{

		configPath = event.getModConfigurationDirectory() + "/harderstart/";
		tabharderstart = new CreativeTabHS(CreativeTabs.getNextID(), "tabharderstart");
		tabRubble = new CreativeTabRubble(CreativeTabs.getNextID(), "tabRubble");
		tabGem = new CreativeTabGem(CreativeTabs.getNextID(), "tabGem");

		ConfigHandler.init(configPath);

		HS_Events.init();
		HS_Items.init();
		HS_Blocks.init();
		HS_Entities.init();
		// ClientProxy.initRenders();
		HS_TileEntities.init();

		new HS_WorldGenHandler();

	}

	@EventHandler
	public void Init(FMLInitializationEvent event)
	{

		HS_Items.registerRecipes();

		HS_Blocks.registerRecipes();
		OreDictionaryRegistry.init();

		RecipeRegister.init();

		new GuiHandler();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		FurnaceRecipeHandler.init();
		CraftingRecipeRemover.init();
	}
}
