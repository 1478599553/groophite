package com.draming.groophite.api;

import com.draming.groophite.groophite;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class G_Item {
    public Item __innerItem;

    public static G_Item getGItemByRegName(String RegistryName) {
        Item item_to_return = Item.getByNameOrId(RegistryName);
        if(item_to_return == null) {
            groophite.logger.warn("No item can be got by the RegistryName [ "+RegistryName+" ] !");
            return null;
        }
        else{
            return new G_Item(Item.getByNameOrId(item_to_return.getRegistryName().toString()));
        }
    }

    G_Item(Item __Item){
        this.__innerItem = __Item;
    }

    public G_Item(String regName){
        this.__innerItem = new Item().setRegistryName(regName);
    }

    public String getRegistryName(){
        return this.__innerItem.getRegistryName().toString();
    }

    public String getUnlocalizedName(){
        return this.__innerItem.getUnlocalizedName();
    }

    public G_Item setMaxStackSize(int limit){
        this.__innerItem.setMaxStackSize(limit);
        return this;
    }

    

}
