package com.draming.groophite.api.events.playerEvent;

import com.draming.groophite.api.events.EntityEventHelper;
import groovy.lang.Closure;
import net.minecraftforge.event.entity.player.FillBucketEvent;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class Groophite_FillBucketEvent{

    public static List<Closure> closures = new ArrayList<Closure>();
    public static FillBucketEvent event;

    public static void subscribeEvent(Closure closure){
        closures.add(closure);
    }

    @SubscribeEvent
    public static void callEvent(FillBucketEvent event){

        for (Closure closure : closures){
            EntityEventHelper entityEventHelper = new EntityEventHelper(event);
            closure.call(entityEventHelper);

        }

    }
}
