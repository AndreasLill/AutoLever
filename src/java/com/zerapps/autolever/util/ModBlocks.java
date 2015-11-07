package com.zerapps.autolever.util;

import com.zerapps.autolever.blocks.AutoLeverBlock;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

/**
 * All blocks in the Mod are stored here for reference.
 */
public final class ModBlocks
{
	// 20 game ticks in a second.
	public static final int TICK_SECOND = 20;
	
	public static Block auto_lever;
	
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(auto_lever = new AutoLeverBlock(), "auto_lever");
	}
}
