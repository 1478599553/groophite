package com.draming.groophite.api.proxier;

import net.minecraft.world.World;

public class G_World {
    protected World inner_world;
    public boolean isRemote;
    public G_World(World world){
        this.inner_world = world;
        this.isRemote = this.inner_world.isRemote;
    }
    public <T extends G_Entity> void spawnEntity(T entity){
        this.inner_world.spawnEntity(entity.inner_entity);
    }

}
