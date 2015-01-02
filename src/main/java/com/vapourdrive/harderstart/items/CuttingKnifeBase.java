package com.vapourdrive.harderstart.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CuttingKnifeBase extends ItemTool
{
	private final Item.ToolMaterial material;
	private float damage;

	public CuttingKnifeBase(ToolMaterial material)
	{
		super(1.0F, material, null);
		this.material = material;
		this.setMaxDamage(material.getMaxUses());
		maxStackSize = 1;
		this.damage = 2.0F + material.getDamageVsEntity();
	}

	public float damageCalc()
	{
		return this.material.getDamageVsEntity();
	}

	/**
	 * harvest speed of the tool
	 */
	@Override
	public float func_150893_a(ItemStack stack, Block block)
	{
		Material material = block.getMaterial();
		if(material == Material.plants && material == Material.vine && material == Material.coral && material == Material.leaves
					&& material == Material.gourd)
		{
			return 1.5F;
		}
		else
		{
			return 0.8F;
		}
	}

	/**
	 * Current implementations of this method in child classes do not use the
	 * entry argument beside ev. They just raise the damage on the stack.
	 */
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase player, EntityLivingBase target)
	{
		stack.damageItem(1, target);
		return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase player)
	{
		if ((double) block.getBlockHardness(world, x, y, z) != 0.0D)
		{
			stack.damageItem(2, player);
		}

		return true;
	}

	/**
	 * Returns True is the item is rendered in full 3D when held.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	/**
	 * returns the action that specifies what animation to play when the items
	 * is being used
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.block;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 72000;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		return stack;
	}

	@Override
	public boolean func_150897_b(Block block)
	{
		return block == Blocks.web;
	}

	/**
	 * Return the enchantability factor of the item, most of the time is based
	 * on material.
	 */
	@Override
	public int getItemEnchantability()
	{
		return Items.iron_sword.getItemEnchantability();
	}

	/**
	 * Return the name for this tool's material.
	 */

	public String getToolMaterialName()
	{
		return this.material.toString();
	}

	/**
	 * Return whether this item is repairable in an anvil.
	 */
	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairstack)
	{
		return this.material.getRepairItemStack() == repairstack ? true : super.getIsRepairable(stack, repairstack);
	}

	/**
	 * Gets a map of item attribute modifiers, used by ItemSword to increase hit
	 * damage.
	 */
	@Override
	public Multimap getItemAttributeModifiers()
	{
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e,
				"Weapon modifier", (double) this.damage, 0));
		return multimap;
	}
}
