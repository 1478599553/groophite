package com.draming.groophite.modsCompat;

import com.draming.groophite.api.Getters;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.oredict.IngredientOreDict;
import net.minecraft.client.util.SearchTree;
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

public class GlobalsGenerator {

    //TODO don't forget to add a bracket
    public static final String someForeTemp = "import com.draming.groophite.api.Getters\n" +
            "import crafttweaker.api.item.IItemStack\n" +
            "import groovy.lang.Closure\n" +
            "import groovy.lang.Script\n" +
            "import groovy.transform.BaseScript\n" +
            "\n" +
            " class GlobalUtils extends Script {\n" +
            "\n" +
            "     static IItemStack itemStack(String name){\n" +
            "         Getters.getIItemStackByName(name,1)\n" +
            "     }\n" +
            "     static IItemStack itemStack(String name,int meta){\n" +
            "         Getters.getIItemStackByName(name,1,meta)\n" +
            "     }\n" +
            "     static IItemStack itemStack(String name,int amount,int meta){\n" +
            "         Getters.getIItemStackByName(name,amount,meta)\n" +
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

        ForgeRegistries.ITEMS.forEach((Item item )-> {
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
            stringBuilder.append(ModCompatUtils.lineSeparator);
        });
        stringBuilder.append("}");

        FileUtils
                .writeStringToFile
                        (
                                new File("./scripts/groophite/GlobalUtils.groovy"),
                                stringBuilder.toString(),
                                StandardCharsets.UTF_8);
    }

}
