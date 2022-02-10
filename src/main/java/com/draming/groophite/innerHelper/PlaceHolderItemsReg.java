package com.draming.groophite.innerHelper;

import com.draming.groophite.groophite;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = groophite.MODID)
public class PlaceHolderItemsReg {

    @SubscribeEvent
    public static void reg_placeholder_items(RegistryEvent.Register<Item> event){
        int count = Configs.placeholder_item_count;
    }
}
