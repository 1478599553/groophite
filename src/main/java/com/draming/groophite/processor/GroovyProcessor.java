package com.draming.groophite.processor;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;


import com.draming.groophite.api.*;
import com.draming.groophite.groophite;
import groovy.lang.Closure;

import groovy.lang.Binding;


public class GroovyProcessor {

    final File SCRIPT_DIR = new File("./scripts/groophite");
    Binding binding = new Binding();
    File[] script_files = SCRIPT_DIR.listFiles
        (
            new FilenameFilter() 
            {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".groovy");
                };
            }
        );

    public static void cleanClosures() throws NoSuchFieldException, IllegalAccessException {
        List<Class> event_clazz = new ArrayList<Class>();

        event_clazz.add(Groophite_PlayerDestroyItemEvent.class);
        event_clazz.add(Groophite_EntityJoinWorldEvent.class);
        event_clazz.add(Groophite_PlayerSetSpawnEvent.class);
        event_clazz.add(Groophite_PlayerContainerEvent.class);
        event_clazz.add(Groophite_FillBucketEvent.class);
        event_clazz.add(Groophite_ItemTooltipEvent.class);
        event_clazz.add(Groophite_AttackEntityEvent.class);


        for (Class clazz : event_clazz){
            clazz.getField("closures").set(clazz,new ArrayList<Closure>());;
        }
        //TODO add more cleaning entries!
    }

    public GroovyProcessor() throws NoSuchFieldException, IllegalAccessException {
        cleanClosures();
        if (script_files  != null)
        {

        for (File key : script_files)
            {
            groophite.logger.info("loading script : " + key.getName());

            GroovyScriptFactory.getInstance().scriptGetAndRun(key, binding);
                groophite.logger.info("loaded script : " + key.getName());
            }
        }
    }

}
