package com.draming.groophite.api;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class G_PlayerEvent extends G_Event {

    public G_EntityPlayer player;

    public G_EntityPlayer getPlayer(){
        return this.player;
    }

    <T extends PlayerEvent> G_PlayerEvent(T event) {
        super(event);
        this.player = new G_EntityPlayer(event.getEntityPlayer());
    }
}
