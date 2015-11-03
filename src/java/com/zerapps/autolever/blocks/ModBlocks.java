package com.zerapps.autolever.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks
{
	public static Block auto_lever;
	
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(auto_lever = new AutoLeverBlock(Material.wood), "auto_lever");
	}
}
