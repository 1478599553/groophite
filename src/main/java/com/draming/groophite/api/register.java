package com.draming.groophite.api;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

public class register {
    public static void regShapedRecipe(String recipeName, @Nonnull G_ItemStack output,Object... params){
        ResourceLocation name = new ResourceLocation(recipeName);
        ResourceLocation group = new ResourceLocation("groophite");
        ItemStack itemStack = new ItemStack(Items.APPLE);
        Object[] list = {"aaa","aaa","aaa","a",itemStack.getItem()};
        GameRegistry.addShapedRecipe(name,group,output.__innerItemStack,list);
    }
    /*
    public static void regShapelessRecipe(String recipeName, String group, @Nonnull G_ItemStack output, Ingredient... params){
        Ingredient[] ingredients = params;
        GameRegistry.addShapelessRecipe();
    }
    */

}
