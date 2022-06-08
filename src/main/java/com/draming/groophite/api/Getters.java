package com.draming.groophite.api;


import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import jline.internal.Nullable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModContainer;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Getters {

    public static List<String> getModList(){
        List<ModContainer> modList = Loader.instance().getModList();
        List<String> modList_string = new ArrayList<String>();
        for (ModContainer item : modList){
            modList_string.add(item.getModId());
        }
        return modList_string;

    }

    @Nullable
    public static IItemStack getIItemStackByName(String name,int amount){
        if (Item.getByNameOrId(name)!=null) {
            return CraftTweakerMC.getIItemStack(new ItemStack(Item.getByNameOrId(name), amount));
        }else {
            System.out.println("Item with name: "+name+" not found!");
            return null;
        }
    }

    @Nullable
    public static ILiquidStack getILiquidStackByName(String name, int amount){
        if (Item.getByNameOrId(name)!=null) {
            return CraftTweakerMC.getILiquidStack(new FluidStack(FluidRegistry.getFluid(name),amount));
        }else {
            System.out.println("Liquid with name: "+name+" not found!");
            return null;
        }
    }


}
