package com.draming.groophite.api;

import com.draming.groophite.groophite;
import groovy.lang.Closure;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = groophite.MODID)
public class Groophite_ItemTooltipEvent {

    public static List<Closure> closures = new ArrayList<Closure>();


    public static void subscribeEvent(Closure closure){
        closures.add(closure);
    }

    @SubscribeEvent
    public static void callEvent(ItemTooltipEvent event){

        for (Closure closure : closures){
            EntityEventHelper entityEventHelper = new EntityEventHelper(event);
            closure.call(entityEventHelper);
        }

    }
}
