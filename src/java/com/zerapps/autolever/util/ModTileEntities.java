package com.zerapps.autolever.util;

import com.zerapps.autolever.tileentity.AutoTileEntity;

import cpw.mods.fml.common.registry.GameRegistry;

public final class ModTileEntities
{
	public static void registerEntities()
	{
		GameRegistry.registerTileEntity(AutoTileEntity.class, "autolever_tile_entity");
	}
}
