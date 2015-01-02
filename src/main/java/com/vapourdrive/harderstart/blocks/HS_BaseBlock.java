package com.vapourdrive.harderstart.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.vapourdrive.harderstart.HarderStart;

public class HS_BaseBlock extends Block
{

	protected HS_BaseBlock(Material material)
	{
		super(material);
		this.setCreativeTab(HarderStart.tabharderstart);
	}

}
