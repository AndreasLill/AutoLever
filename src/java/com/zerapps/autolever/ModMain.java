package com.zerapps.autolever;

import com.zerapps.autolever.util.ModBlocks;
import com.zerapps.autolever.util.ModRecipes;
import com.zerapps.autolever.util.ModReferences;
import com.zerapps.autolever.util.ModTileEntities;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModReferences.MOD_ID, name = ModReferences.MOD_NAME, version = ModReferences.MOD_VERSION)
public class ModMain
{
    @Instance
    public static ModMain instance = new ModMain();
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
    	ModBlocks.registerBlocks();
    	ModTileEntities.registerEntities();
    	ModRecipes.registerRecipes();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	
    }
}