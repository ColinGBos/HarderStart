package com.vapourdrive.harderstart.recipe.cuttingtable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

import org.apache.logging.log4j.Level;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.vapourdrive.harderstart.HarderStart;
import com.vapourdrive.harderstart.items.CuttingKnifeBase;
import com.vapourdrive.harderstart.utils.ItemStackUtils;

public class CuttingTableRecipeParser
{
	/**
	 * preps the file from the name instantiates DOM documentbuilderfactory and document builder
	 * sends the document to get parsed
	 * @param fileName
	 */
	public static void init(String fileName)
	{
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			factory.setIgnoringComments(true);
			factory.setIgnoringElementContentWhitespace(true);

			DocumentBuilder builder = factory.newDocumentBuilder();
			File file = new File(HarderStart.configPath + fileName);
			if (file.exists())
			{
				Document document = builder.parse(file);
				document.getDocumentElement().normalize();

				processDocument(document, fileName);
			}
			else
			{
				HarderStart.log.log(Level.WARN, "Could not find recipe file for CuttingTable");
			}
		}
		catch (ParserConfigurationException e)
		{
			HarderStart.log.log(Level.ERROR, "ParsingConfigurationException fired");
		}
		catch (SAXException e)
		{
			HarderStart.log.log(Level.ERROR, "SAX exception fired");
		}
		catch (IOException e)
		{
			HarderStart.log.log(Level.ERROR, "IOException fired");
		}
	}

	/**
	 * Creates a nodelist of recipes (from tag "recipe")
	 * loops through the list of recipe nodes sending them for reading
	 * 
	 * @param document
	 * @param fileName
	 */
	public static void processDocument(Document document, String fileName)
	{
		NodeList rootNodes = document.getElementsByTagName("recipe");
		HarderStart.log.log(Level.INFO, "Starting to read recipe file " + fileName);

		for (int i = 0; i < rootNodes.getLength(); i++)
		{
			Node currentNode = rootNodes.item(i);

			Element recipeElement = (Element) currentNode;
			String recipeName = recipeElement.getAttribute("name");
			if (CuttingTableRecipeFiller.recipeList.contains(recipeName))
			{
				HarderStart.log.log(Level.INFO, "Recipe already registered, skipping recipe: " + recipeName);
			}
			else
			{
				handleRecipeNode(recipeElement, recipeName);
				CuttingTableRecipeFiller.recipeList.add(recipeName);
			}
		}
		HarderStart.log.log(Level.INFO, "Finished reading recipe file " + fileName);

		return;
	}

	/**
	 * Breaks recipe node up into tool class, input and output
	 * @param recipeElement
	 * @param recipeName
	 */
	public static void handleRecipeNode(Element recipeElement, String recipeName)
	{
		Class toolType;
		ItemStack ingredient = null;
		ItemStack[] results = new ItemStack[]
		{};

		Node tool = recipeElement.getElementsByTagName("tool").item(0);

		toolType = getToolClassFromNode(tool);

		Node input = recipeElement.getElementsByTagName("input").item(0);
		if (input != null)
		{
			ingredient = getInputFromNode(input, recipeName);
		}
		else
		{
			HarderStart.log.log(Level.WARN, recipeName + " input node is not found");
		}

		Node outputs = recipeElement.getElementsByTagName("outputs").item(0);
		if (outputs != null)
		{
			results = getResultsFromNode(outputs, recipeName);
		}
		else
		{
			HarderStart.log.log(Level.WARN, "outputs node is not found");
		}

		if (ingredient != null && results != null && results.length > 0)
		{
			if (!addRecipe(toolType, ingredient, results, recipeName))
			{
				HarderStart.log.log(Level.WARN, "Failed to add recipe: " + recipeName);
			}
		}

		return;
	}

	/**
	 * switches through input number to determine desired tool
	 * @param tool
	 * @return
	 */
	public static Class getToolClassFromNode(Node tool)
	{
		if (tool != null)
		{
			if (tool.getNodeType() == Node.ELEMENT_NODE)
			{
				int toolIndex = 0;
				Element toolElement = (Element) tool;
				toolIndex = Integer.parseInt(toolElement.getTextContent());

				switch (toolIndex)
				{
					case 0:
						return CuttingKnifeBase.class;
					case 1:
						return ItemAxe.class;
					case 2:
						return ItemPickaxe.class;
					case 3:
						return ItemSpade.class;
				}
			}
		}
		return CuttingKnifeBase.class;
	}

	/**
	 * Breaks itemstack node up into modID, name, number and meta
	 * @param input
	 * @param recipeName
	 * @return
	 */
	public static ItemStack getInputFromNode(Node input, String recipeName)
	{
		ItemStack returnStack = null;

		if (input.getNodeType() == Node.ELEMENT_NODE)
		{
			Element inputElement = (Element) input;
			Node itemstack = inputElement.getElementsByTagName("itemStack").item(0);
			if (itemstack.getNodeType() == Node.ELEMENT_NODE)
			{
				Element elementItemStack = (Element) itemstack;

				returnStack = getValidStack(elementItemStack.getAttribute("modID"), elementItemStack.getAttribute("itemName"), null,
						elementItemStack.getAttribute("itemMeta"), recipeName);
			}
		}

		return returnStack;
	}

	/**
	 * Same as getInputFromNode except with a loop and nodelist for the itemstacks
	 * @param outputs
	 * @param recipeName
	 * @return
	 */
	public static ItemStack[] getResultsFromNode(Node outputs, String recipeName)
	{
		List<ItemStack> resultList = new ArrayList<ItemStack>();

		if (outputs.getNodeType() == Node.ELEMENT_NODE)
		{
			ItemStack stack;

			Element outputsElement = (Element) outputs;
			NodeList stacklist = outputsElement.getElementsByTagName("itemStack");

			for (int i = 0; (i < stacklist.getLength() && i < 4); i++)
			{
				Node currentStack = stacklist.item(i);
				if (currentStack.getNodeType() == Node.ELEMENT_NODE)
				{
					Element elementItemStack = (Element) currentStack;
					stack = getValidStack(elementItemStack.getAttribute("modID"), elementItemStack.getAttribute("itemName"),
							elementItemStack.getAttribute("number"), elementItemStack.getAttribute("itemMeta"), recipeName);

					if (stack == null)
					{
						HarderStart.log.log(Level.WARN, recipeName + " Itemstack is not valid, output is null");
						return null;
					}
					else
					{
						resultList.add(stack);
					}
				}
				else
				{
					HarderStart.log.log(Level.WARN, recipeName + " Itemstack node could not be cast to element");
				}
			}

			ItemStack[] returnStack = new ItemStack[resultList.size()];
			resultList.toArray(returnStack);
			return returnStack;
		}
		else
		{
			HarderStart.log.log(Level.WARN, recipeName + " output node was not valid element, returning null");
			return null;
		}
	}

	/**
	 * Takes strings from the itemstack tag
	 * Exists to deal pleasantly with possible null values for number and metadata
	 * @param modID
	 * @param itemName
	 * @param number
	 * @param metadata
	 * @param recipeName
	 * @return
	 */
	public static ItemStack getValidStack(String modID, String itemName, String number, String metadata, String recipeName)
	{
		ItemStack bufferStack;
		ItemStack returnStack = null;
		int numberInt = 1;
		int metadataInt = 0;

		if (number != null && number != "")
		{
			numberInt = Integer.parseInt(number);
		}
		if (metadata != null && metadata != "")
		{
			metadataInt = Integer.parseInt(metadata);
		}

		bufferStack = ItemStackUtils.getItemStackFromString(modID, itemName, numberInt);

		if (bufferStack != null)
		{
			if (metadata != null && metadata != "")
			{
				returnStack = new ItemStack(bufferStack.getItem(), numberInt, metadataInt);
			}
			else
			{
				returnStack = new ItemStack(bufferStack.getItem(), numberInt);
			}
		}
		else
		{
			HarderStart.log.log(Level.WARN, recipeName + " contains a null itemstack");
			HarderStart.log.log(Level.WARN, "check that '" + modID + "' and '" + itemName + "' are valid");
		}

		return returnStack;
	}

	/**
	 * Sends off the info to get added in as a recipe
	 * @param tool
	 * @param ingredient
	 * @param results
	 * @param recipeName
	 * @return
	 */
	public static boolean addRecipe(Class tool, ItemStack ingredient, ItemStack[] results, String recipeName)
	{
		CuttingTableRecipeManager.getInstance().addCutRecipe(tool, ingredient, results);
		return true;
	}

}
