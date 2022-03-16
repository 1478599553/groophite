package com.draming.groophite.api;

import com.draming.groophite.groophite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class G_ItemStack {
    public Item __innerItem;
    public ItemStack __innerItemStack;
    public G_ItemStack(String RegistryName) {
        this.__innerItem = Item.getByNameOrId(RegistryName);
        if(__innerItem != null) {
                this.__innerItemStack = new ItemStack(__innerItem);
        }
        else {
            groophite.logger.warn("No item can be got by the RegistryName [ "+RegistryName+" ] !");
        }
    }

    G_ItemStack(ItemStack __ItemStack){
        if (__ItemStack.getItem() != null) {
            this.__innerItem = __ItemStack.getItem();
        }
        this.__innerItemStack = __ItemStack;
    }

    public String getDisplayName (){
        return this.__innerItemStack.getDisplayName();
    }
    public String getUnlocalizedName(){
        return this.__innerItemStack.getUnlocalizedName();
    }

    public int getCount(){
        return this.__innerItemStack.getCount();
    }

    public void setCount(int count){
        this.__innerItemStack.setCount(count);
    }


}
