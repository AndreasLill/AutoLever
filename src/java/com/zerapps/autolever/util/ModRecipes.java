package com.zerapps.autolever.util;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public final class ModRecipes
{
	public static void registerRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(ModBlocks.auto_lever), new Object[] {"#", "I", '#', Items.redstone, 'I', Blocks.lever});
	}
}
