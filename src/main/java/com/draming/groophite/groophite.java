package com.draming.groophite;



import com.draming.groophite.inGame.CommandRoot;

/*
//import com.codetaylor.mc.pyrotech.modules.tech.basic.plugin.crafttweaker.ZenSoakingPot;
//import com.codetaylor.mc.pyrotech.modules.tech.refractory.ModuleTechRefractory;
import com.codetaylor.mc.pyrotech.modules.tech.basic.plugin.crafttweaker.ZenSoakingPot;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
//import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.minecraft.CraftTweakerMC;
*/

import com.draming.groophite.processor.GroovyProcessor;
import com.draming.groophite.processor.Reloader;
import net.minecraft.init.Blocks;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Mod(
        modid = groophite.MODID,
        name = groophite.NAME,
        version = groophite.VERSION,
dependencies = "required-after:knoothing;required-after:crafttweaker")

public class groophite
{
    public static boolean isCraftTweakerLoaded;
    public static final String MODID = "groophite";
    public static final String NAME = "Groophite";
    public static final String VERSION = "1.3";
    @Mod.Instance
    public static groophite Instance;


    public static Clipboard clipboard;

    public static Logger logger;

    public groophite(){
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    }

    public static List<Exception> exceptionList = new ArrayList<>();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = event.getModLog();
        GroovyProcessor.findEventClasses();
        try {
            new com.draming.groophite.processor.GroovyProcessor();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {



    }
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {
        isCraftTweakerLoaded = Loader.isModLoaded("crafttweaker");

        //RuntimeUtil.genEventCode();

    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandRoot());
    }

}
