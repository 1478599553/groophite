package com.draming.groophite.api.events.playerEvent;


import com.draming.groophite.api.events.EntityEventHelper;
import com.draming.groophite.groophite;
import groovy.lang.Closure;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
@Mod.EventBusSubscriber(modid = groophite.MODID)
public class Groophite_PlayerContainerEvent{
    public static List<Closure> closures = new ArrayList<Closure>();
    public static PlayerContainerEvent event;

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
