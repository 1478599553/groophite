package com.draming.groophite.api;


import com.draming.groophite.api.EntityEventHelper;
import com.draming.groophite.groophite;
import groovy.lang.Closure;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
@Mod.EventBusSubscriber(modid = groophite.MODID)
public class Groophite_PlayerContainerEvent extends G_PlayerEvent{
    public static List<Closure> closures = new ArrayList<Closure>();

    public G_EntityPlayer player;
    Groophite_PlayerContainerEvent (PlayerContainerEvent event){
        super(event);
        this.player = new G_EntityPlayer(event.getEntityPlayer());
    }

    public static void subscribeEvent(Closure closure){
        closures.add(closure);
    }

    @SubscribeEvent
    public static void callEvent(PlayerContainerEvent event){

        for (Closure closure : closures){
            Groophite_PlayerContainerEvent event_to_give = new Groophite_PlayerContainerEvent(event);
            closure.call(event_to_give);
        }

    }

}
