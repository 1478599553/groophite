package com.draming.groophite;


import com.draming.groophite.inGame.CommandRoot;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@Mod(
        modid = groophite.MODID,
        name = groophite.NAME,
        version = groophite.VERSION
        /*dependencies = "required-after:crafttweaker"*/)

public class groophite
{
    public static final String MODID = "groophite";
    public static final String NAME = "Groophite";
    public static final String VERSION = "1.0";
    @Mod.Instance
    public static groophite Instance;

    public static Logger logger;

    public groophite(){

    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) throws IOException {


        logger = event.getModLog();


        new com.draming.groophite.processor.GroovyProcessor();

    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

    }
    @EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandRoot());
    }

}
