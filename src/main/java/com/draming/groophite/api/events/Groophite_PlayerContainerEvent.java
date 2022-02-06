package com.draming.groophite.api.events;


import com.draming.groophite.groophite;
import groovy.lang.Closure;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
@Mod.EventBusSubscriber(modid = groophite.MODID)
public class Groophite_PlayerContainerEvent extends Groophite_PlayerEvent {
    public static List<Closure> closures = new ArrayList<Closure>();
    public static PlayerContainerEvent event;

    public Groophite_PlayerContainerEvent(){

        //closures.add((Closure) defaultClosure.closure_loaded);
    }

    public static void subscribeEvent(Closure closure){
        closures.add(closure);
    }

    @SubscribeEvent
    public static void callEvent(PlayerContainerEvent event){

        for (Closure closure : closures){
            EntityEventHelper entityEventHelper = new EntityEventHelper(event);
            closure.call(entityEventHelper);
        }

    }

}
