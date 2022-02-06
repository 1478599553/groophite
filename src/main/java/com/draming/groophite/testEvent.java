package com.draming.groophite;

import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class testEvent {
    @SubscribeEvent
    public static void test(PlayerContainerEvent event){
        event.getEntityPlayer().setDead();
        System.out.println("OK, java called!");
        event.getListenerList();
    }
}

