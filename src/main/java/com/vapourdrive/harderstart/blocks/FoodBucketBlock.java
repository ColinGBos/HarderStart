package com.vapourdrive.harderstart.blocks;

import java.util.Random;

import org.apache.logging.log4j.Level;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.vapourdrive.harderstart.HS_ModInfo;
import com.vapourdrive.harderstart.HarderStart;
import com.vapourdrive.harderstart.tileentities.FoodBucket_TE;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FoodBucketBlock extends Block implements ITileEntityProvider
{
	private IIcon blockTop;
	private IIcon blockSide;

	public FoodBucketBlock()
	{
		super(Material.wood);
		float min = 0.125F;
		this.setCreativeTab(HarderStart.tabharderstart);
		this.setBlockName(HS_BlockInfo.FoodBucketBlockName);
		this.setBlockBounds(min, 0.0F, min, 1.0F - min, 1.0F - min, 1.0F - min);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		blockTop = register.registerIcon(HS_ModInfo.RESOURSE_LOCATION + HS_BlockInfo.FoodBucketTop);
		blockSide = register.registerIcon(HS_ModInfo.RESOURSE_LOCATION + HS_BlockInfo.FoodBucketSide);
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (side == 1 || side == 0)
		{
			return blockTop;
		}
		else
		{
			return blockSide;
		}
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int id)
	{
		return new FoodBucket_TE();
	}

	/**
	 * The food bucket can be filled, picked up and size scoped
	 * TODO break into smaller functions
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof FoodBucket_TE)
		{
			FoodBucket_TE bucket = (FoodBucket_TE) te;
			if (!player.isSneaking())
			{
				if (player.getCurrentEquippedItem() != null)
				{
					ItemStack stack = player.getCurrentEquippedItem();
					if (ItemFood.class.isAssignableFrom(stack.getItem().getClass()) && stack.getItem() != Item.getItemFromBlock(this))
					{
						int stackValue = ((ItemFood) stack.getItem()).func_150905_g(stack);
						float stackSat = ((ItemFood) stack.getItem()).func_150906_h(stack);
						if (!world.isRemote && bucket.foodValue > stackValue && stackSat >= 0.6F)
						{
							bucket.foodValue = bucket.foodValue - stackValue;
							if (bucket.foodValue < 0)
							{
								bucket.foodValue = 0;
							}
							player.inventory.getCurrentItem().stackSize--;
						}
					}
				}
				else if (!world.isRemote)
				{
					int max = Item.getItemFromBlock(this).getMaxDamage();
					player.addChatMessage(new ChatComponentText("Food value: " + (max - bucket.foodValue) + "/" + max));
					HarderStart.log.log(Level.INFO, world.getBlockMetadata(x, y, z));
				}
			}
			else
			{
				if (!world.isRemote)
				{
					if (!player.inventory.addItemStackToInventory(new ItemStack(Item.getItemFromBlock(this), 1, bucket.foodValue)))
					{
						this.breakBlock(world, x, y, z, this, 0);
					}
					else
					{
						world.removeTileEntity(x, y, z);
					}
				}
				world.setBlockToAir(x, y, z);
			}
		}

		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof FoodBucket_TE)
		{
			FoodBucket_TE bucket = (FoodBucket_TE) te;
			int damage = bucket.foodValue;

			float spawnX = x + world.rand.nextFloat();
			float spawnY = y + world.rand.nextFloat();
			float spawnZ = z + world.rand.nextFloat();

			EntityItem droppeditem = new EntityItem(world, spawnX, spawnY, spawnZ, new ItemStack(Item.getItemFromBlock(this), 1, damage));

			float multiplyer = 0.05F;

			droppeditem.motionX = (-0.5 + world.rand.nextFloat()) * multiplyer;
			droppeditem.motionY = (3 + world.rand.nextFloat()) * multiplyer;
			droppeditem.motionZ = (-0.5 + world.rand.nextFloat()) * multiplyer;

			world.spawnEntityInWorld(droppeditem);
			world.removeTileEntity(x, y, z);
		}

		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public Item getItemDropped(int par1, Random rand, int par3)
	{
		return null;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null)
		{
			if (te instanceof FoodBucket_TE)
			{
				((FoodBucket_TE) te).foodValue = stack.getItemDamage();
			}
		}
	}

}
