package com.draming.groophite.processor;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import com.draming.groophite.api.events.playerEvent.Groophite_PlayerContainerEvent;
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

    public static void cleanClosures(){
        Groophite_PlayerContainerEvent.closures = new ArrayList<Closure>();
        //TODO add more cleaning entries!
    }

    public GroovyProcessor(){
        cleanClosures();
        if (script_files  != null)
        {

        for (File key : script_files)
            {
            groophite.logger.info("loaded script : " + key.getName());

            GroovyScriptFactory.getInstance().scriptGetAndRun(key, binding);
            }
        }
    }

}
