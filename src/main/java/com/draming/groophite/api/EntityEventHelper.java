package com.draming.groophite.api;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.*;

import java.util.List;

public class EntityEventHelper {
    EntityPlayer entityPlayer;
    Entity entity;
    public G_EntityPlayer g_entityPlayer;
    public boolean isCancelable;
    EntityEvent entityEvent;
    public G_World g_world;
    public G_ItemStack g_emptyBucket;
    public G_ItemStack g_filledBucket;
    public G_Pos newSpawn;
    public List<String>[] containerOfTooltip;
    public G_Entity attackTarget;
    public G_ItemStack itemStackToToolTip;

    public <T extends EntityEvent> EntityEventHelper(T event) {
        this.entity = event.getEntity();
        this.isCancelable = event.isCancelable();
        this.entityEvent = event;

        if (event instanceof PlayerEvent){
            this.entityPlayer = ((PlayerEvent) event).getEntityPlayer();
            this.g_entityPlayer = new G_EntityPlayer(entityPlayer);

            if (event instanceof FillBucketEvent){
                if (((FillBucketEvent) event).getEmptyBucket() != null) {
                    this.g_emptyBucket = new G_ItemStack(((FillBucketEvent) event).getEmptyBucket());
                }
                if (((FillBucketEvent) event).getFilledBucket() != null) {
                    this.g_filledBucket = new G_ItemStack(((FillBucketEvent) event).getFilledBucket());
                }
            }
            if (event instanceof PlayerSetSpawnEvent){
                this.newSpawn = new G_Pos(((PlayerSetSpawnEvent) event).getNewSpawn());
            }
            if (event instanceof ItemTooltipEvent) {
                this.containerOfTooltip = new List[]{((ItemTooltipEvent) event).getToolTip()};

                if (((ItemTooltipEvent) event).getItemStack() != null) {
                    this.itemStackToToolTip = new G_ItemStack(((ItemTooltipEvent) event).getItemStack());
                }
            }
            if (event instanceof AttackEntityEvent){
                this.attackTarget = new G_Entity(((AttackEntityEvent) event).getTarget());
            }
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

    public List<String> getToolTip(){
        return this.containerOfTooltip[0];
    }

    public G_World getG_world(){
        return this.g_world = new G_World(this.entityEvent.getEntity().getEntityWorld());
    }

}
