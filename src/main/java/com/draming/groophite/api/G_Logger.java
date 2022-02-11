package com.draming.groophite.api;


import com.draming.groophite.groophite;
import org.apache.logging.log4j.Logger;

public class G_Logger {
    private static Logger __innerLogger = groophite.logger;

    public static void info(Object msg){
        __innerLogger.info(msg);
    }

    public static void warn(Object msg){
        __innerLogger.warn(msg);
    }

    public static void fatal(Object msg){
        __innerLogger.fatal(msg);
    }

    public static void debug(Object msg){
        __innerLogger.debug(msg);
    }

    public static void error(Object msg){
        __innerLogger.error(msg);
    }

}
