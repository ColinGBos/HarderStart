package com.vapourdrive.harderstart.handlers.NEIIntegration;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIHarderStartConfig implements IConfigureNEI
{
	@Override
	public void loadConfig()
	{
		API.registerRecipeHandler(new CuttingTableRecipeHandler());
		API.registerUsageHandler(new CuttingTableRecipeHandler());

	}

	@Override
	public String getName()
	{
		return "HarderStart";
	}

	@Override
	public String getVersion()
	{
		return "${version}";
	}
}
