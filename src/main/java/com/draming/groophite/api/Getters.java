package com.draming.groophite.api;

import com.draming.groophite.groophite;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModContainer;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Getters {

    public static List<String> getModList(){
        List<ModContainer> modList = Loader.instance().getModList();
        List<String> modList_string = new ArrayList<String>();
        for (ModContainer item : modList){
            modList_string.add(item.getModId());
        }
        return modList_string;
    }

}
