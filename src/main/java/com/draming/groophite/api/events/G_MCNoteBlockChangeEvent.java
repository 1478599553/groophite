package com.draming.groophite.api.events;

import groovy.lang.Closure;
import groovy.transform.stc.ClosureParams;
import groovy.transform.stc.FromString;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber
public class G_MCNoteBlockChangeEvent {
    public static Set<Closure> callList = new HashSet<>();
    
        @SubscribeEvent
    public static void onEvent(net.minecraftforge.event.world.NoteBlockEvent.Change event){
        for (Closure closure: callList){
            closure.call(new crafttweaker.mc1120.events.handling.MCNoteBlockChangeEvent(event));
        }
    }
public static void sub(@ClosureParams(value = FromString.class,options = "crafttweaker.mc1120.events.handling.MCNoteBlockChangeEvent")Closure closure){
            callList.add(closure);
    }
}