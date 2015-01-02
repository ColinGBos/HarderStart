package com.vapourdrive.harderstart.config;

import java.io.File;

public class ConfigHandler
{
	public static File hsConfig;

	public static void init(String configPath)
	{
		hsConfig = new File(configPath + "main.cfg");

		HSMainConfig.init(hsConfig);
	}

}
