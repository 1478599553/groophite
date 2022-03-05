package com.draming.groophite;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.world.WorldEvent;
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
    @SubscribeEvent
    public static void test2 (WorldEvent.CreateSpawnPosition event){
        System.out.println("OK!!!!!!!!!!!!!!!Spawning!!!!");
        System.out.println("OK!!!!!!!!!!!!!!!Spawning!!!!");
        System.out.println("OK!!!!!!!!!!!!!!!Spawning!!!!");
        System.out.println("OK!!!!!!!!!!!!!!!Spawning!!!!");
        System.out.println("OK!!!!!!!!!!!!!!!Spawning!!!!");
        System.out.println("OK!!!!!!!!!!!!!!!Spawning!!!!");
        System.out.println("OK!!!!!!!!!!!!!!!Spawning!!!!");
        System.out.println("OK!!!!!!!!!!!!!!!Spawning!!!!");
        System.out.println("OK!!!!!!!!!!!!!!!Spawning!!!!");
        System.out.println("OK!!!!!!!!!!!!!!!Spawning!!!!");
        System.out.println("OK!!!!!!!!!!!!!!!Spawning!!!!");
        System.out.println("OK!!!!!!!!!!!!!!!Spawning!!!!");
        System.out.println("OK!!!!!!!!!!!!!!!Spawning!!!!");
        System.out.println("OK!!!!!!!!!!!!!!!Spawning!!!!");
        System.out.println("OK!!!!!!!!!!!!!!!Spawning!!!!");
        event.getWorld().setWorldTime(13000);
    }
}

