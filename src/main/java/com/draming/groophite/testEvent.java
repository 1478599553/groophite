package com.draming.groophite;

import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;

@Mod.EventBusSubscriber
public class testEvent {
    public static String string = "String";
    public static String string2 = "String2";
    @SubscribeEvent
    public static void test(PlayerContainerEvent event) throws IOException {

    }
}

