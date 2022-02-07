package com.draming.groophite.innerHelper;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;

public class TestRecipe {
    public TestRecipe(){
        GameRegistry.addShapedRecipe(new ResourceLocation("test2"), new ResourceLocation ("groophite"), new ItemStack(Items.ARROW), "##", "##", '#', Blocks.VINE);
    }
}
