package com.draming.groophite.api;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class ShapedRecipeBuilder {


    ResourceLocation name;
    ResourceLocation group;
    ItemStack output;
    String[] shapes;
    private List<Object> maps = new ArrayList<Object>();

    public ShapedRecipeBuilder(String name,String group){
        this.name = new ResourceLocation(name);
        this.group = new ResourceLocation(group);
    }


    public void setOutput(G_ItemStack output){
        this.output = output.__innerItemStack;
    }
    public void setShape(String... shape){
        this.shapes = shape;
    }
    public void setMap(String placeHolderChar,G_Item item){
        this.maps.add(placeHolderChar.charAt(0),item.__innerItem);
    }

    protected Object[] dump(){
        int element_count = shapes.length + maps.size();
        Object[] RecipeContent = new Object[element_count];
        int i = 0;
        for (String item : this.shapes){
            RecipeContent[i] = item;
                    i = i+1;
        }
        for (Object item : maps){
            RecipeContent[i] = item;
            i = i+1;
        }
        return RecipeContent;
    }
}
