package com.draming.groophite.api.archived;

import com.draming.groophite.groophite;
import groovy.lang.Closure;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = groophite.MODID)
public class Groophite_PlayerSetSpawnEvent extends G_PlayerEvent {
    public static List<Closure> closures = new ArrayList<Closure>();

    public G_Pos newSpawn;
    public G_EntityPlayer player;
    Groophite_PlayerSetSpawnEvent(PlayerSetSpawnEvent event){
        super(event);
        this.newSpawn = new G_Pos(event.getNewSpawn());
        this.player = new G_EntityPlayer(event.getEntityPlayer());
    }

    public static void subscribeEvent(Closure closure){
        closures.add(closure);
    }

    @SubscribeEvent
    public static void callEvent(PlayerSetSpawnEvent event){

        for (Closure closure : closures){
            Groophite_PlayerSetSpawnEvent event_to_give = new Groophite_PlayerSetSpawnEvent(event);
            closure.call(event_to_give);
        }

    }
}
