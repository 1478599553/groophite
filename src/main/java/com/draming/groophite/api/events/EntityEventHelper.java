package com.draming.groophite.api.events;

import com.draming.groophite.api.proxier.G_EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class EntityEventHelper {
    EntityPlayer entityPlayer;
    Entity entity;
    public G_EntityPlayer g_entityPlayer;
    public boolean isCancelable;
    EntityEvent entityEvent;
    public <T extends EntityEvent> EntityEventHelper(T event) {
        this.entity = event.getEntity();
        this.isCancelable = event.isCancelable();
        this.entityEvent = event;
        if (event instanceof PlayerEvent){
            this.entityPlayer = ((PlayerEvent) event).getEntityPlayer();
            this.g_entityPlayer = new G_EntityPlayer(entityPlayer);

        }
        else{
            this.entityPlayer = (EntityPlayer) this.entity;
        }

    }

    public boolean cancel_event(){
        if (!this.isCancelable){
            return false;
        }
        else {
            this.entityEvent.setCanceled(true);
            return true;

        }
    }

}
