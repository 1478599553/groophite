package com.draming.groophite.api;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class G_Event {
    public Boolean isCancelable;
    Event __innerEvent;
    <T extends Event> G_Event(T event){
        this.isCancelable = event.isCancelable();
        this.__innerEvent = event;
    }
    public void setCanceled(Boolean canceled){
        this.__innerEvent.setCanceled(canceled);
    }
}
