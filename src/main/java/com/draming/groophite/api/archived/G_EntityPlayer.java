package com.draming.groophite.api.archived;


import net.minecraft.entity.player.EntityPlayer;

public class G_EntityPlayer extends G_EntityLiving{

    private EntityPlayer player;

    public G_EntityPlayer(EntityPlayer player){
        super(player);
        this.player = player;
    }

    public void setDead(){
        this.player.attackEntityFrom(G_DamageSource.OUT_OF_WORLD.__innerDamageSource, Integer.MAX_VALUE);
    }
    public void setHealth(float health){
        this.player.setHealth(health);
    }
    public void setFire(int seconds){
        this.player.setFire(seconds);
    }

    public void attackEntityFrom(G_DamageSource g_damageSource, float amount){
        this.player.attackEntityFrom(g_damageSource.__innerDamageSource,amount);
    }

    public G_Pos getPosition(){
        return new G_Pos(this.player.getPosition());
    }

    public String getName (){
        return this.player.getName();
    }

    

}
