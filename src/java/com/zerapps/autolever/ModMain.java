package com.zerapps.autolever;

import com.zerapps.autolever.blocks.ModBlocks;
import com.zerapps.autolever.lib.ModReferences;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
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
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
    	
    }
}