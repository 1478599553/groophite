package com.draming.groophite.api;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

public class register {
    public static void regShapedRecipe(String recipeName, String recipeGroup, @Nonnull G_ItemStack output, Object... params){
        ResourceLocation name = new ResourceLocation(recipeName);
        ResourceLocation group = new ResourceLocation(recipeGroup);
        GameRegistry.addShapedRecipe(name,group,output.__innerItemStack,params);
    }
    /*
    public static void regShapelessRecipe(String recipeName, String group, @Nonnull G_ItemStack output, Ingredient... params){
        Ingredient[] ingredients = params;
        GameRegistry.addShapelessRecipe();
    }
    */

}
