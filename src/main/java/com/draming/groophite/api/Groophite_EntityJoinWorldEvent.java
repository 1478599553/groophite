package com.draming.groophite.api;

import groovy.lang.Closure;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class Groophite_EntityJoinWorldEvent {

    public static List<Closure> closures = new ArrayList<Closure>();

    public G_World world;
    public G_Entity entity;
    EntityEventHelper __innerEvent;

    Groophite_EntityJoinWorldEvent(EntityJoinWorldEvent event){
        this.entity = new G_Entity(event.getEntity());
        this.world = new G_World(event.getWorld());
    }

    public static void subscribeEvent(Closure closure){
        closures.add(closure);
    }

    @SubscribeEvent
    public static void callEvent(EntityJoinWorldEvent event){

        for (Closure closure : closures){
            Groophite_EntityJoinWorldEvent event_to_give = new Groophite_EntityJoinWorldEvent(event);
            closure.call(event_to_give);
        }

    }
}
