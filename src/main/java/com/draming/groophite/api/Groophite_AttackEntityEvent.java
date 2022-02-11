package com.draming.groophite.api;

import groovy.lang.Closure;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class Groophite_AttackEntityEvent {
    public static List<Closure> closures = new ArrayList<Closure>();

    public G_Entity target;
    public G_Entity entity;
    Groophite_AttackEntityEvent(AttackEntityEvent event){
        this.target = new G_Entity(event.getTarget());
        this.entity = new G_Entity(event.getEntity());

    }
    public static void subscribeEvent(Closure closure){
        closures.add(closure);
    }

    @SubscribeEvent
    public static void callEvent(AttackEntityEvent event){
        Groophite_AttackEntityEvent event_to_give = new Groophite_AttackEntityEvent(event);
        for (Closure closure : closures){

            closure.call(event_to_give);
        }
    }
}
