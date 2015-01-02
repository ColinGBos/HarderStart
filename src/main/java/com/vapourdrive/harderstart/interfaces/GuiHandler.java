package com.vapourdrive.harderstart.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.vapourdrive.harderstart.HarderStart;
import com.vapourdrive.harderstart.interfaces.cuttingtable.ContainerCuttingTable;
import com.vapourdrive.harderstart.interfaces.cuttingtable.GuiCuttingTable;
import com.vapourdrive.harderstart.tileentities.CuttingTable_TE;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler
{
	public GuiHandler()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(HarderStart.instance, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case 0:
				TileEntity te = world.getTileEntity(x, y, z);
				if (te != null && te instanceof CuttingTable_TE)
				{
					return new ContainerCuttingTable(player.inventory, (CuttingTable_TE) te);
				}
				break;
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case 0:
				TileEntity te = world.getTileEntity(x, y, z);
				if (te != null && te instanceof CuttingTable_TE)
				{
					return new GuiCuttingTable(player.inventory, (CuttingTable_TE) te);
				}

				break;
		}

		return null;
	}

}
