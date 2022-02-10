package com.draming.groophite.api;

import com.draming.groophite.api.G_EntityPlayer;
import com.draming.groophite.api.G_ItemStack;
import com.draming.groophite.api.G_World;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSetSpawnEvent;

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

    public G_ItemStack itemStackToToolTip;

    public <T extends EntityEvent> EntityEventHelper(T event) {
        this.entity = event.getEntity();
        this.isCancelable = event.isCancelable();
        this.entityEvent = event;
        this.g_world = new G_World(event.getEntity().getEntityWorld());



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

}
