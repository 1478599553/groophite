package com.draming.groophite.api;

import net.minecraft.entity.Entity;

public class G_Entity {
    protected Entity inner_entity;
    public G_Entity(Entity entity){
        this.inner_entity = entity;
    }
    public G_World getWorld(){
        return new G_World(this.inner_entity.getEntityWorld());
    }
}
