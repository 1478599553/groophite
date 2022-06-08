package com.draming.groophite.api.events;

import crafttweaker.api.minecraft.CraftTweakerMC;
import groovy.lang.Closure;
import groovy.transform.stc.ClosureParams;
import groovy.transform.stc.FromString;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber
public class G_MCPlayerLoggedInEvent {
    public static Set<Closure> callList = new HashSet<>();

        @SubscribeEvent
    public static void onEvent(PlayerEvent.PlayerLoggedInEvent event){
                for (Closure closure: callList){
                    closure.call(new crafttweaker.mc1120.events.handling.MCPlayerLoggedInEvent(CraftTweakerMC.getIPlayer(event.player)));
                }
    }
public static void sub(@ClosureParams(value = FromString.class,options = "crafttweaker.mc1120.events.handling.MCPlayerLoggedInEvent")Closure closure){
            callList.add(closure);
    }
}