package com.draming.groophite.api.proxier;

import com.draming.groophite.groophite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.testng.annotations.Optional;

public class G_ItemStack {
    protected Item item;
    protected ItemStack itemStack;
    public G_ItemStack(String RegistryName , @Optional int amout) {
        this.item = Item.getByNameOrId(RegistryName);
        if(item != null) {
            if (amout != 0) {
                this.itemStack = new ItemStack(item, amout);
            } else {
                this.itemStack = new ItemStack(item);
            }
        }
        else {
            groophite.logger.warn("No item can be got by the RegistryName [ "+RegistryName+" ] !");
        }
    }
    public String getDisplayName (){
        return this.itemStack.getDisplayName();
    }
    public String getUnlocalizedName(){
        return this.itemStack.getUnlocalizedName();
    }
}
