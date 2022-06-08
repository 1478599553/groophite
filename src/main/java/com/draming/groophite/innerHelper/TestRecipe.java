package com.draming.groophite.innerHelper;

import com.draming.groophite.api.archived.G_ItemStack;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TestRecipe {
    public TestRecipe() {
        Boolean isEqual = new G_ItemStack("minecraft:apple").__innerItem.equals(Items.APPLE);

        GameRegistry.addShapedRecipe(new ResourceLocation("test4")
                ,new ResourceLocation("groophite"),
                new ItemStack(Items.APPLE),
                "##", "##", '#', new G_ItemStack("minecraft:apple").__innerItem);


        System.out.println(isEqual);
    }
}