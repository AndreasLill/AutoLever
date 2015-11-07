package com.zerapps.autolever.util;

import com.zerapps.autolever.tileentity.ModTileEntity;

import cpw.mods.fml.common.registry.GameRegistry;

public final class ModTileEntities
{
	public static void registerEntities()
	{
		GameRegistry.registerTileEntity(ModTileEntity.class, "autolever_tile_entity");
	}
}
