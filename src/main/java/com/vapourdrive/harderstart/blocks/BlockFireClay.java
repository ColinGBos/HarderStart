package com.vapourdrive.harderstart.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

import com.vapourdrive.harderstart.HS_ModInfo;
import com.vapourdrive.harderstart.HarderStart;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFireClay extends Block
{

	public BlockFireClay()
	{
		super(Material.clay);
		setBlockName(HS_BlockInfo.FireClayName);
		setCreativeTab(HarderStart.tabharderstart);
		setStepSound(soundTypeGravel);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		blockIcon = register.registerIcon(HS_ModInfo.RESOURSE_LOCATION + HS_BlockInfo.FireClayName);
	}
	
	@Override
    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return 0;
    	//return Blocks.fire.getEncouragement(this);
    }
    
	@Override
    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return 0;
    	//return Blocks.fire.getFlammability(this);
    }
    
	@Override
    public boolean isFlammable(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
    	return true;
        //return getFlammability(world, x, y, z, face) > 0;
    }

}
