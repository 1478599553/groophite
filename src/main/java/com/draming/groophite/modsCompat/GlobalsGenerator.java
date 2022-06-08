package com.draming.groophite.modsCompat;

import com.draming.groophite.api.EventHandler;
import com.draming.groophite.api.Getters;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.oredict.IngredientOreDict;
import crafttweaker.util.FileUtil;
import net.minecraft.client.util.SearchTree;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GlobalsGenerator {


    public static final String someForeTemp = "import com.draming.groophite.api.Getters\n" +
            "import crafttweaker.api.item.IItemStack\n" +
            "import groovy.lang.Closure\n" +
            "import groovy.lang.Script\n" +
            "import groovy.transform.BaseScript\n" +
            "\n" +
            "import com.draming.groophite.api.EventHandler"+
            "\n"+
            "import groovy.transform.stc.ClosureParams\n" +
            "import groovy.transform.stc.FromString"+
            "\n" +
            " class GlobalUtils extends Script {\n" +
            "\n" +
            "     static IItemStack itemStack(String name){\n" +
            "         Getters.getIItemStackByName(name,1)\n" +
            "     }\n" +
            "\n" +
            "     @Override\n" +
            "     Object run() {\n" +
            "         return null\n" +
            "     }\n" +
            "     ";



    public static void generateGlobals() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(someForeTemp);
        Map<String,StringBuilder> nameSpaceMap2Content = new HashMap<>();

        String singleChunkForeTemp = "package gens\n"+
                "class Minecraft{\n";
        String singleStatementTemp = "public static String stringName = \"testContent\"\n";

        ForgeRegistries.ITEMS.forEach((Item item )-> {
            if (!nameSpaceMap2Content.containsKey(item.getRegistryName().getResourceDomain().toString())){
                nameSpaceMap2Content
                        .put(
                                item.getRegistryName().getResourceDomain().toString(),
                                new StringBuilder(singleChunkForeTemp.replace("Minecraft",firstChar2UpperCase(item.getRegistryName().getResourceDomain()))));
            }
            StringBuilder contentBuilder = nameSpaceMap2Content.get(item.getRegistryName().getResourceDomain().toString());
            contentBuilder.append(singleStatementTemp
                    .replace("stringName",item.getRegistryName().getResourcePath().replace(".","_"))
                    .replace("testContent",item.getRegistryName().toString()));
            /*
            stringBuilder.append(ModCompatUtils.lineSeparator);




            if (!stringBuilder.toString().contains("String "+item.getRegistryName().getResourcePath())) {

                    stringBuilder
                            .append("public final String ")
                            .append(item.getRegistryName().getResourcePath().replace(".","_"))
                            .append(" = '")
                            .append(item.getRegistryName())
                            .append("'");
                }
                else
                {
                    stringBuilder.append("public final String ")
                            .append(item.getRegistryName().toString().replace(":", "_").replace(".","_"))
                            .append(" = '")
                            .append(item.getRegistryName())
                            .append("'");
                }
                */
        });
        nameSpaceMap2Content.values().forEach((StringBuilder builder)->{
            builder.append("\n}");
        });
        if (!new File("./scripts/groophite/gens").exists()){
            new File("./scripts/groophite/gens").mkdir();
        }
        nameSpaceMap2Content.keySet().forEach((String str)->{
            try {
                FileUtils.writeStringToFile(new File("./scripts/groophite/gens/"+firstChar2UpperCase(str)+".groovy"),nameSpaceMap2Content.get(str).toString(),StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
/*
        for (Class eventClass : EventHandler.eventNameToCrtEventClassMap.values()){
            stringBuilder.append(genEventSubscriber(eventClass));
        }
*/
        stringBuilder.append("}");

        FileUtils
                .writeStringToFile
                        (
                                new File("./scripts/groophite/GlobalUtils.groovy"),
                                stringBuilder.toString(),
                                StandardCharsets.UTF_8);
    }

    public static String firstChar2UpperCase(String string){
        return Pattern.compile(String.valueOf(string.charAt(0)), Pattern.LITERAL).matcher(
                string).replaceFirst(Matcher.quoteReplacement(String.valueOf(string.charAt(0)).toUpperCase()));
    }

}
