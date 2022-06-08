package com.draming.groophite.api.archived;


import com.draming.groophite.groophite;
import groovy.lang.Closure;
import net.minecraftforge.event.entity.player.FillBucketEvent;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = groophite.MODID)

public class Groophite_FillBucketEvent{

    public static List<Closure> closures = new ArrayList<Closure>();
    public G_ItemStack emptyBucket;
    public G_ItemStack filledBucket;
    public G_EntityPlayer player;
    FillBucketEvent __innerEvent;

    Groophite_FillBucketEvent(FillBucketEvent event){
        this.emptyBucket = new G_ItemStack(event.getEmptyBucket());
        this.filledBucket = new G_ItemStack(event.getFilledBucket());
        this.player = new G_EntityPlayer(event.getEntityPlayer());
        this.__innerEvent = event;
    }

    public static void subscribeEvent(Closure closure){
        closures.add(closure);
    }

    @SubscribeEvent
    public static void callEvent(FillBucketEvent event){

        for (Closure closure : closures){
            Groophite_FillBucketEvent event_to_give = new Groophite_FillBucketEvent(event);
            closure.call(event_to_give);
        }

    }
}
