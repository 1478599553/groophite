package com.draming.groophite.api;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModContainer;

import java.util.ArrayList;
import java.util.List;

public class getters {

    public static List<String> getModList(){
        List<ModContainer> modList = Loader.instance().getModList();
        List<String> modList_string = new ArrayList<String>();
        for (ModContainer item : modList){
            modList_string.add(item.getModId());
        }
        return modList_string;
    }
}
