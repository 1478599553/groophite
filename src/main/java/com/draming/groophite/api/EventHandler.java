package com.draming.groophite.api;

import crafttweaker.mc1120.events.handling.MCPlayerAdvancementEvent;
import groovy.lang.Closure;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.List;

@Mod.EventBusSubscriber
public class EventHandler {
    public static HashMap<Integer, List<Closure>> eventCallList = new HashMap<>();

    public static HashMap<Integer, Class> eventNameToCrtEventClassMap = new HashMap<>();

    @SubscribeEvent
    public static void subscribeEntityEventRoot(Event event){
        Class<? extends Event> clazz = event.getClass();
        List<Closure> closureList =
                eventCallList
                        .get(clazz.getName()
                                .split("\\.")
                                    [clazz.getName()
                                            .split("\\.").length-1
                                    ]
                                .hashCode()
                            );

        Class crtClass = eventNameToCrtEventClassMap.get(clazz.getName().hashCode());

        if (closureList != null && crtClass != null){
            for (Closure closure : closureList) {

                try {
                    closure.call(crtClass.getConstructor().newInstance(event));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
