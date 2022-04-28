package com.draming.groophite;



import com.codetaylor.mc.pyrotech.modules.tech.basic.plugin.crafttweaker.ZenSoakingPot;
import com.draming.groophite.api.G_EntityPlayer;
import com.draming.groophite.inGame.CommandRoot;

import com.draming.groophite.modsCompat.GlobalsGenerator;
import com.draming.groophite.modsCompat.ModCompatUtils;

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
import com.draming.groophite.modsCompat.NeonExpose;
import net.minecraft.init.Blocks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage;
import net.minecraftforge.fml.relauncher.libraries.ModList;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;


@Mod(
        modid = groophite.MODID,
        name = groophite.NAME,
        version = groophite.VERSION,
dependencies = "required-after:knoothing")

public class groophite
{
    public static boolean isCraftTweakerLoaded;
    public static final String MODID = "groophite";
    public static final String NAME = "Groophite";
    public static final String VERSION = "1.0";
    @Mod.Instance
    public static groophite Instance;


    public static Clipboard clipboard;

    public static Logger logger;

    public groophite(){
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) throws Exception {

        /*
        ZenSoakingPot.addRecipe("test",
         CraftTweakerMC.getIItemStack(new ItemStack(Items.APPLE)),
        CraftTweakerMC.getILiquidStack(new FluidStack(FluidRegistry.WATER,1000)),
                CraftTweakerMC.getIItemStack(new ItemStack(Items.ARROW)), 1000);

*/
        /*
        String groophitePackName = this.getClass().getPackage().getName().replace("groophite","groophite.api");

        Class[] classes = ModCompatUtils.getClasssFromPackage(groophitePackName).toArray(new Class[0]);
        for (Class clz : classes){
            System.out.println(clz.getName());

            ModCompatUtils.expose(clz);
        }
        for (Method method : G_EntityPlayer.class.getMethods()){
            System.out.println(method);
        }
*/
        //ModCompatUtils.expose(com.draming.knoothing.processor.EncryptUtil.class);
        //ModCompatUtils.calcExpose();

        logger = event.getModLog();

        //GlobalsGenerator.generateGlobals();


    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

        try {
            new com.draming.groophite.processor.GroovyProcessor();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

    }
    @EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {
        isCraftTweakerLoaded = Loader.isModLoaded("crafttweaker");


    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandRoot());
    }

}
