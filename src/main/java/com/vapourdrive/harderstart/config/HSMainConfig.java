package com.vapourdrive.harderstart.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class HSMainConfig
{
	public static Configuration config;

	/**
	 * TODO config options
	 * @param hsMainConfig
	 */
	public static void init(File hsMainConfig)
	{
		config = new Configuration(hsMainConfig);

		config.load();

		config.save();
	}

}
