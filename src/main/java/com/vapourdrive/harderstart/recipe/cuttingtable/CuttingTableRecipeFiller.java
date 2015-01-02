package com.vapourdrive.harderstart.recipe.cuttingtable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Level;

import com.vapourdrive.harderstart.HarderStart;

public class CuttingTableRecipeFiller
{
	public static List<String> recipeList = new ArrayList<String>();

	/**
	 * sets up the files for parsing
	 * creates them if necessary, ensures their existence, sends for parsing
	 */
	public static void init()
	{
		String Location = "/assets/harderstart/config/";
		String CoreFileName = "CoreCuttingTableRecipes.xml";
		String UserFileName = "UserCuttingTableRecipes.xml";

		File CoreConfigRecipeFile;
		File UserConfigRecipeFile;

		CoreConfigRecipeFile = new File(HarderStart.configPath + CoreFileName);
		UserConfigRecipeFile = new File(HarderStart.configPath + UserFileName);

		createFile(CoreConfigRecipeFile, true, Location);
		createFile(UserConfigRecipeFile, false, Location);
		
		CuttingTableRecipeParser.init(UserFileName);
		CuttingTableRecipeParser.init(CoreFileName);
	}

	/**
	 * creates the file if it needs to be done, replace flag is flown
	 * sets a new file evertime
	 * @param file
	 * @param replace
	 * @param ResourceFileLocation
	 * @return
	 */
	public static boolean createFile(File file, Boolean replace, String ResourceFileLocation)
	{
		InputStream stream;

		if (!file.exists() || replace == true)
		{
			try
			{
				file.createNewFile();
				stream = CuttingTableRecipeFiller.class.getResourceAsStream(ResourceFileLocation + file.getName());
				IOUtils.copy(stream, new FileOutputStream(file));
				HarderStart.log.log(Level.INFO, "Successfully copied " + file.getName() + " from location: " + ResourceFileLocation);

			}
			catch (FileNotFoundException e)
			{
				HarderStart.log.log(Level.ERROR, "FileNotFound thrown with: " + file.getName() + " at location: " + ResourceFileLocation);
				e.printStackTrace();
			}
			catch (IOException error)
			{
				HarderStart.log.log(Level.ERROR, "IOException thrown with: " + file.getName() + " at location: " + ResourceFileLocation);
				error.printStackTrace();
			}

			return true;
		}
		else
		{
			HarderStart.log.log(Level.INFO, "File " + file.getName() + " at location: " + ResourceFileLocation + " exists");
			return false;
		}
	}
}
