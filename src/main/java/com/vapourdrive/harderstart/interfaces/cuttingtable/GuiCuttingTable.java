package com.vapourdrive.harderstart.interfaces.cuttingtable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.vapourdrive.harderstart.tileentities.CuttingTable_TE;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCuttingTable extends GuiContainer
{
	protected CuttingTable_TE cuttingtable;

	public GuiCuttingTable(InventoryPlayer invPlayer, CuttingTable_TE cuttingtable)
	{
		super(new ContainerCuttingTable(invPlayer, cuttingtable));
		xSize = 176;
		ySize = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("harderstart:textures/gui/container/cuttingtable.png");

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y)
	{
		GL11.glColor4f(1, 1, 1, 1);

		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

	}

}
