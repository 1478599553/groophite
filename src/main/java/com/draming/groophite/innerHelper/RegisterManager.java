package com.draming.groophite.innerHelper;

import com.draming.groophite.groophite;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryModifiable;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = groophite.MODID)
public class RegisterManager {
    public static ArrayList<Item> items_to_reg = new ArrayList<Item>();

    @SubscribeEvent
    public static void regSimpleItem(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll();
    }

}
