package com.draming.groophite.api.proxier;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class G_EntityPlayer extends G_EntityLiving{

    private EntityPlayer player;

    public G_EntityPlayer(EntityPlayer player){
        super(player);
        this.player = player;
    }

    public void setDead(){
        this.player.setDead();
    }

    public void setFire(int seconds){
        this.player.setFire(seconds);
    }

    public G_Pos getPosition(){
        return new G_Pos(this.player.getPosition());
    }

    public String getName (){
        return this.player.getName();
    }



}
