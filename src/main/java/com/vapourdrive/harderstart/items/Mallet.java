package com.vapourdrive.harderstart.items;

import java.util.Set;

import org.apache.logging.log4j.Level;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.collect.Sets;
import com.vapourdrive.harderstart.HS_ModInfo;
import com.vapourdrive.harderstart.HarderStart;
import com.vapourdrive.harderstart.utils.ItemStackUtils;
import com.vapourdrive.harderstart.utils.RandomUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Mallet extends ItemTool
{
	String name;

	public static final Set EffectiveBlocksSet = Sets.newHashSet(new Block[]
	{
			Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone,
			Blocks.iron_ore, Blocks.coal_ore, Blocks.gold_ore, Blocks.diamond_ore, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore,
			Blocks.redstone_ore, Blocks.lit_redstone_ore
	});

	public Mallet(ToolMaterial material, String Name)
	{
		super(1.0F, material, EffectiveBlocksSet);
		this.name = Name;
		this.setCreativeTab(HarderStart.tabharderstart);
		this.efficiencyOnProperMaterial = material.getEfficiencyOnProperMaterial();
		this.setUnlocalizedName(name);
		this.setHarvestLevel("mallet", material.getHarvestLevel());
		this.setMaxDamage(material.getMaxUses());
		this.setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		itemIcon = register.registerIcon(HS_ModInfo.RESOURSE_LOCATION + name);
	}

	/**
	 * returns whether or not the block gets dropped on harvesting, returns
	 * false for rocks because they result in rubble
	 */
	@Override
	public boolean func_150897_b(Block block)
	{
		Material mat = block.getMaterial();
		if (mat == Material.rock)
		{
			return false;
		}
		return true;
	}

	/**
	 * returns the mining speed while attempting to harvest a block
	 */
	@Override
	public float func_150893_a(ItemStack stack, Block block)
	{
		if (block.getMaterial() == Material.rock)
		{
			return this.efficiencyOnProperMaterial + 5.0F;
		}
		else
		{
			return 0.75F;
		}
	}

	/**
	 * gets called when the block is actually broken, damages the players item
	 * as per usual one special case for lit-redstone simply because it refuses
	 * to be registered in the ore-dictionary spawns a coresponding rubble stack
	 * for the ore-dictionary or a default rubble drop per dimension
	 * 
	 * @return boolean for block destroyed or not - true
	 */
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entity)
	{
		if ((double) block.getBlockHardness(world, x, y, z) != 0.0D)
		{
			stack.damageItem(1, entity);
		}

		if (block == Blocks.lit_redstone_ore)
		{
			int bonus = world.rand.nextInt(EnchantmentHelper.getFortuneModifier(entity) + 1);

			RandomUtils.spawnItem(world, x, y, z, new ItemStack(HS_Items.redstoneOre_rubble, 1 + bonus), 0.7F);
			RandomUtils.spawnItem(world, x, y, z, new ItemStack(getDefaultRubble(world, block, entity), 6 + bonus), 0.7F);

			return true;
		}

		if (block.getMaterial() == Material.rock)
		{
			if (tryRubbleDrop(stack, world, block, x, y, z, entity) == false)
			{
				int bonus = world.rand.nextInt(EnchantmentHelper.getFortuneModifier(entity) + 1);
				RandomUtils.spawnItem(world, x, y, z, new ItemStack(getDefaultRubble(world, block, entity), 6 + bonus), 0.7F);
			}
		}

		return true;
	}

	/**
	 * Gets called from local onBlockDestroyed creates an array of oredictionary
	 * ints for the block loops through the array and searches for a matched
	 * rubble item, and spawns it (most loop 1) total rubble drop count remains
	 * static
	 * @param stack 
	 * 
	 * @param world
	 * @param block
	 * @param x
	 * @param y
	 * @param z
	 * @param entity
	 * @return whether or not an ore-dictionary match is found
	 */
	public boolean tryRubbleDrop(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entity)
	{
		boolean hasDrop = false;
		int bonus = 0;
		int metadata = world.getBlockMetadata(x, y, z);
		int toolHarvestLevel = this.toolMaterial.getHarvestLevel();
		ItemStack rubble = null;
		Item defaultRubble = getDefaultRubble(world, block, entity);
		
		int[] oreIDs = OreDictionary.getOreIDs(new ItemStack(block, 1, metadata));
		if (oreIDs != null && toolHarvestLevel >= block.getHarvestLevel(metadata))
		{
			for (int i = 0; i < oreIDs.length; i++)
			{
				HarderStart.log.log(Level.INFO, OreDictionary.getOreName(oreIDs[i]));
				bonus = world.rand.nextInt(EnchantmentHelper.getFortuneModifier(entity) + 1);

				rubble = ItemStackUtils.getItemStackFromString("harderstart", OreDictionary.getOreName(oreIDs[i]) + "_rubble",
						1 + bonus);
				if (rubble != null)
				{
					RandomUtils.spawnItem(world, x, y, z, rubble, 0.05F);
					hasDrop = true;
				}
			}
		}

		// check exists to make sure stone doesn't drop twice as much rubble as it should
		if(rubble != null && rubble.getItem() != getDefaultRubble(world, block, entity))
		{
			RandomUtils.spawnItem(world, x, y, z, new ItemStack(defaultRubble, 7), 0.7F);
		}

		return hasDrop;
	}

	/**
	 * Called from local onBlockDestroyed Determines the default rubble to spawn
	 * based off of the dimension id possibly more checks in the future.
	 * 
	 * @param world
	 * @param block
	 * @param entity
	 * @return rubble item to spawn based on the dimension
	 */
	public Item getDefaultRubble(World world, Block block, EntityLivingBase entity)
	{
		int dimensionID = world.provider.dimensionId;
		switch (dimensionID)
		{
			case 0:
				return HS_Items.stone_rubble;
			case -1:
				return HS_Items.netherrack_rubble;
			case 1:
				return HS_Items.stoneEnd_rubble;
			default:
				return HS_Items.stone_rubble;
		}
	}
}
