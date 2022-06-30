package com.draming.groophite.processor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.draming.groophite.groophite;
import org.codehaus.groovy.control.MultipleCompilationErrorsException;
import org.codehaus.groovy.runtime.InvokerHelper;

import groovy.lang.GroovyClassLoader;
import groovy.lang.Binding;
import groovy.lang.Script;

public class GroovyScriptFactory {
    private static Map<String, Class<Script>> scriptCache = new HashMap<>();
    private GroovyClassLoader classLoader = new GroovyClassLoader();
    private static GroovyScriptFactory factory = new GroovyScriptFactory();
    EncryptUtil encryptUtil = new EncryptUtil();
    
    private GroovyScriptFactory(){
        this.classLoader.addClasspath("./scripts/groophite");
    }

    public static GroovyScriptFactory getInstance(){
        return factory;
    }

    private Class getScript(File file) throws MultipleCompilationErrorsException {
        String key;
        key = ReadFileContent.readFileContent(file);

        String encodeStr = encryptUtil.SHA256(key);

        if(scriptCache.containsKey(encodeStr)){
            return scriptCache.get(encodeStr);
        }else{
            // 脚本不存在则创建新的脚本
            try {
                Class<Script> scriptClass = classLoader.parseClass(key);
                scriptCache.put(encodeStr, scriptClass);
                return scriptClass;
            }catch (MultipleCompilationErrorsException e){
                e.printStackTrace();
                groophite.logger.fatal("Unable to load script"+ file.getName());

                return classLoader.parseClass("");

            }

        }
    }

    private Object run(Class<Script> script, Binding binding) {
        Script scriptObj = InvokerHelper.createScript(script, binding);
        Object result = scriptObj.run();
        // clear the script cache
        classLoader.clearCache();
        return result;
    }

    public Object scriptGetAndRun(File file, Binding binding) {
        Object result = "Fail to exec this script.";
        try {
           result =  run(getScript(file), binding);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}