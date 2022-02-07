package com.draming.groophite.api;

import com.draming.groophite.api.G_EntityPlayer;
import com.draming.groophite.api.G_ItemStack;
import com.draming.groophite.api.G_World;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class EntityEventHelper {
    EntityPlayer entityPlayer;
    Entity entity;
    public G_EntityPlayer g_entityPlayer;
    public boolean isCancelable;
    EntityEvent entityEvent;
    public G_World g_world;
    public G_ItemStack g_emptyBucket;
    public G_ItemStack g_filledBucket;

    public <T extends EntityEvent> EntityEventHelper(T event) {
        this.entity = event.getEntity();
        this.isCancelable = event.isCancelable();
        this.entityEvent = event;
        this.g_world = new G_World(event.getEntity().getEntityWorld());


        if (event instanceof PlayerEvent){
            this.entityPlayer = ((PlayerEvent) event).getEntityPlayer();
            this.g_entityPlayer = new G_EntityPlayer(entityPlayer);

            if (event instanceof FillBucketEvent){
                g_emptyBucket =  new G_ItemStack(((FillBucketEvent) event).getEmptyBucket());
                g_filledBucket = new G_ItemStack(((FillBucketEvent) event).getFilledBucket());

            }
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
