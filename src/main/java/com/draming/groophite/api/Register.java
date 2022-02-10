package com.draming.groophite.api;

import com.draming.groophite.groophite;
import groovy.lang.Closure;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.RegistryManager;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;


public class Register {

    public static void regShapedRecipe(ShapedRecipeBuilder builder){
        ResourceLocation name = builder.name;
        ResourceLocation group = builder.group;
        GameRegistry.addShapedRecipe(name,group,builder.output,builder.dump());
/*
        int i = 0;

        for (Object thing : params){

            if (thing instanceof G_Item){
                params[i] = ((G_Item) thing).__innerItem;
            }

            i = i+1;
        }
 */
    }

    public static void regSimpleItem(){

    }


    /*
    public static void regShapelessRecipe(String recipeName, String group, @Nonnull G_ItemStack output, Ingredient... params){
        Ingredient[] ingredients = params;
        GameRegistry.addShapelessRecipe();
    }
    */


}
