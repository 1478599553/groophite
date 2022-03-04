package com.draming.groophite.modsCompat;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ModCompatUtils {
    public static final String lineSeparator = System.lineSeparator();
    public static List<String> stubSources;

    public static void expose(Class classToExpose){
        String packageName = classToExpose.getPackage().getName();

        StringBuilder sourceCodeBuilder = new StringBuilder();
        StringBuilder fieldNameBuilder = new StringBuilder();
        Field[] fields = classToExpose.getFields();
        Method[] methods = classToExpose.getDeclaredMethods();

        sourceCodeBuilder
                .append(classToExpose.getPackage())
                .append(lineSeparator);

        if (classToExpose.isEnum())
        {
            sourceCodeBuilder
                    .append("public enum "+classToExpose.getName() + "{" + lineSeparator);
            Iterator<Field> iterator = Arrays.stream(fields).iterator();
            int count = 0;
            StringBuilder enumBuilder = new StringBuilder();
            System.out.println(fields.length);
            for (Field field : fields){
                if (count < (fields.length-1) ) {
                    enumBuilder.append(fields[count].getName()+",");
                    count = count + 1;
                }
                if (count == (fields.length-1)){
                    enumBuilder.append(fields[count].getName()+";");
                    break;
                }

            }
            sourceCodeBuilder.append(enumBuilder.toString());
            sourceCodeBuilder.append("}");
        }
        else
        {

            sourceCodeBuilder
                    .append("public class " + classToExpose.getName() + "{" + lineSeparator);

            //Generate methods
            for (Method method : methods) {
                    sourceCodeBuilder
                            .append(lineSeparator)
                            .append(method.toString().replace(classToExpose.getName() + ".", ""))
                            .append("{")
                            .append(lineSeparator)
                            .append("}")
                            .append(lineSeparator);
            }


            //Generate fields
            for (Field field : fields) {

                    String[] statementStringArray = field.toString().split(" ");
                    String[] fieldNameArray = statementStringArray[statementStringArray.length - 1]
                            .split("\\.");
                    int i = 0;
                    for (String countHelper : statementStringArray) {
                        if (i < statementStringArray.length - 1) {
                            fieldNameBuilder.append(statementStringArray[i] + " ");
                            i = i + 1;
                        } else {
                            fieldNameBuilder.append(fieldNameArray[fieldNameArray.length - 1]);
                            break;
                        }
                    }
                    fieldNameBuilder.append(lineSeparator);


            }


            System.out.println(classToExpose.toString());
            System.out.println(classToExpose.toString());
            System.out.println(classToExpose.toString());
            System.out.println(classToExpose.toString());
            System.out.println(classToExpose.toString());
            System.out.println(classToExpose.toString());
            System.out.println(classToExpose.getTypeName());
            System.out.println(Arrays.toString(classToExpose.getSigners()));
            System.out.println(classToExpose.getMethods()[0].toString());


            sourceCodeBuilder.append(fieldNameBuilder.toString());


            //the end
            sourceCodeBuilder.append(lineSeparator + "}");


        }

        System.out.println(sourceCodeBuilder.toString());

        }

    }

