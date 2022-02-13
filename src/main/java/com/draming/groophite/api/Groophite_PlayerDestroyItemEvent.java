package com.draming.groophite.api;

import groovy.lang.Closure;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class Groophite_PlayerDestroyItemEvent extends G_PlayerEvent{
    public static List<Closure> closures = new ArrayList<Closure>();

    public G_World world;
    public G_EntityPlayer player;
    EnumHand __innerHand;
    EntityEventHelper __innerEvent;

    Groophite_PlayerDestroyItemEvent(PlayerDestroyItemEvent event){
        super(event);
        this.player = new G_EntityPlayer(event.getEntityPlayer());
    }

    public G_EnumHand getHand(){
        if (this.__innerHand == EnumHand.MAIN_HAND){
            return G_EnumHand.MAIN_HAND;
        }
        else
            return G_EnumHand.OFF_HAND;
    }

    public static void subscribeEvent(Closure closure){
        closures.add(closure);
    }

    @SubscribeEvent
    public static void callEvent(PlayerDestroyItemEvent event){

        for (Closure closure : closures){
            Groophite_PlayerDestroyItemEvent event_to_give = new Groophite_PlayerDestroyItemEvent(event);
            closure.call(event_to_give);
        }

    }
}
