package com.draming.groophite.modsCompat;


import java.lang.reflect.Field;
import java.util.List;

public class ModCompatUtils {
    public static final String lineSeparator = System.lineSeparator();
    public static List<String> stubSources;

    public static void expose(Class classToExpose){
        Field[] fields = classToExpose.getFields();
        StringBuilder sourceCodeBuilder = new StringBuilder();
        StringBuilder fieldNameBuilder = new StringBuilder();
        sourceCodeBuilder
                .append(classToExpose.getPackage().toString()).append(lineSeparator)
                .append("public class "+ classToExpose.getName()+"{"+lineSeparator);

        if (fields.length != 0){

            for (Field field : fields) {

                String[] statementStringArray = field.toString().split(" ");
                String[] fieldNameArray = statementStringArray[statementStringArray.length - 1]
                        .split("\\.");
                int i = 0;
                for (String countHelper : statementStringArray){
                    if (i < statementStringArray.length - 1) {
                        fieldNameBuilder.append(statementStringArray[i]+" ");
                        i = i + 1;
                    }
                    else {
                        fieldNameBuilder.append(fieldNameArray[fieldNameArray.length-1]);
                        break;
                    }
                }
                fieldNameBuilder.append(lineSeparator);
            }
            }

        sourceCodeBuilder.append(fieldNameBuilder.toString());

        //the end
        sourceCodeBuilder.append(lineSeparator + "}");

        }

    }

