package com.draming.groophite.api;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import scala.Int;

public class G_EntityPlayer extends G_EntityLiving{

    private EntityPlayer player;

    public G_EntityPlayer(EntityPlayer player){
        super(player);
        this.player = player;
    }

    public void setDead(){
        this.player.attackEntityFrom(DamageSource.ANVIL, Integer.MAX_VALUE);
    }
    public void setHealth(float health){
        this.player.setHealth(health);
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
