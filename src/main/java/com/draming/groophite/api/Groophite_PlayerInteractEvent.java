package com.draming.groophite.api;

import com.draming.groophite.groophite;
import groovy.lang.Closure;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = groophite.MODID)
public class Groophite_PlayerInteractEvent extends G_PlayerEvent{
    public G_World world;
    public G_EnumFacingHelper facing;
    public static List<Closure> root_closures = new ArrayList<Closure>();
    public static List<Closure> entityInteractSpecific_closures = new ArrayList<Closure>();


    Groophite_PlayerInteractEvent(PlayerInteractEvent event) {

        super(event);
        this.world = new G_World(event.getWorld());
        if (event.getFace() != null) {
            this.facing = new G_EnumFacingHelper(Objects.requireNonNull(event.getFace()));
        }
    }

    public static void subscribeEvent(Closure closure){
        root_closures.add(closure);
    }

    @SubscribeEvent
    public static void callEvent(PlayerInteractEvent event){

        for (Closure closure : root_closures){
            Groophite_PlayerInteractEvent event_to_give = new Groophite_PlayerInteractEvent(event);
            closure.call(event_to_give);
        }

    }


    @Mod.EventBusSubscriber(modid = groophite.MODID)
    public static class Groophite_EntityInteractSpecific extends Groophite_PlayerInteractEvent {

        Groophite_EntityInteractSpecific(PlayerInteractEvent.EntityInteractSpecific event) {
            super(event);
        }

        public static void subscribeEvent(Closure closure){
            entityInteractSpecific_closures.add(closure);
        }

        @SubscribeEvent
        public static void callEvent(PlayerInteractEvent.EntityInteractSpecific event){

            for (Closure closure : entityInteractSpecific_closures){
                Groophite_PlayerInteractEvent.Groophite_EntityInteractSpecific event_to_give = new Groophite_PlayerInteractEvent.Groophite_EntityInteractSpecific(event);
                closure.call(event_to_give);
            }

        }

    }
}
