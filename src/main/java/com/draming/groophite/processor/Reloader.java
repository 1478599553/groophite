package com.draming.groophite.processor;

import java.io.IOException;

public class Reloader {
    public static void reloadScript() throws NoSuchFieldException, IllegalAccessException {
        new com.draming.groophite.processor.GroovyProcessor();
    }
}
