package com.draming.groophite;

import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;

@Mod.EventBusSubscriber

public abstract class testEvent {

    public static String string = "String";
    public static String string2 = "String2";
    public static class testInnerClass{

    }
    public String string3 = "string3";
    @SubscribeEvent
    public static void test(PlayerContainerEvent event) throws IOException {

    }
}

