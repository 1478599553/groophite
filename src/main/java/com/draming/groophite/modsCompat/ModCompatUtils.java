package com.draming.groophite.modsCompat;



import org.apache.commons.io.FileUtils;


import org.objectweb.asm.*;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;


public class ModCompatUtils {
    public static final String lineSeparator = System.lineSeparator();
    public static List<String> stubSources = new ArrayList<String>();
    private static List<Class> clazzesToExpose = new ArrayList<Class>();

    public static void expose(Class clazzToExpose){
        clazzesToExpose.add(clazzToExpose);
    }

    public static void calcExpose() throws IOException {

        for (Class classToExpose : clazzesToExpose) {
            System.out.println("Exposing: "+classToExpose.getName());

            String packageName = classToExpose.getPackage().getName();



            StringBuilder sourceCodeBuilder = new StringBuilder();
            StringBuilder fieldNameBuilder = new StringBuilder();
            Field[] fields = classToExpose.getFields();
            Method[] methods = classToExpose.getDeclaredMethods();

            sourceCodeBuilder
                    .append("package "+packageName+";")
                    .append(lineSeparator);

            if (classToExpose.isEnum()) {
                sourceCodeBuilder
                        .append("public enum " + classToExpose.getName().replace(packageName+".","") + "{" + lineSeparator);

                int count = 0;
                StringBuilder enumBuilder = new StringBuilder();

                for (Field field : fields) {
                    field.setAccessible(true);
                    if (count < (fields.length - 1)) {
                        enumBuilder.append(fields[count].getName() + ",");
                        count = count + 1;
                    }
                    if (count == (fields.length - 1)) {
                        enumBuilder.append(fields[count].getName() + ";");
                        break;
                    }

                }
                sourceCodeBuilder.append(enumBuilder.toString());
                sourceCodeBuilder.append("}");
                stubSources.add(sourceCodeBuilder.toString());
            }
            else
            {

                if (classToExpose.getSuperclass().equals(Object.class)){
                    sourceCodeBuilder
                            .append("public class " + classToExpose.getName()
                                    .replace(packageName+".","") + "{" + lineSeparator);
                }else {
                    sourceCodeBuilder
                            .append("public class " + classToExpose.getName()
                                    .replace(packageName+".","")
                                        +" extends "+ classToExpose.getSuperclass().getName() + "{" + lineSeparator);
                }

                List<String> paraTypes = new ArrayList<>();

                //Generate methods
                for (Method method : methods) {
                    method.setAccessible(true);

                    if (method.getParameters().length != 0) {
                        String rawBracketContent = method.toString()
                                .split("\\(")[1]
                                .split("\\)")[0]
                                .replace(classToExpose.getName() + ".", "");

                        String theWholeBracketContent = method.toString().split("\\(")[1];
                        //.replace("\\)","")

                        //Todo test print
                        System.out.println("rawBracketContent is " + rawBracketContent);

                        boolean isSinglePara = !rawBracketContent.contains(",");
                        if (isSinglePara) {
                            paraTypes.add(rawBracketContent);
                        } else {
                            for (String para : rawBracketContent.split(",")) {
                                paraTypes.add(para);
                            }
                        }
                        String[] paraNames = getMethodParamNames(method);
                        StringBuilder completeBracketContentBuilder = new StringBuilder();

                        int index = 0;
                        if (isSinglePara) {
                            completeBracketContentBuilder.append(paraTypes.get(index) + " " + paraNames[index]);
                        } else {
                            for (String paraType : paraTypes) {
                                //not the last one
                                if (index != (paraNames.length - 1)) {
                                    completeBracketContentBuilder.append(
                                            paraType + " " + paraNames[index] + ","
                                    );
                                    index = index + 1;
                                }
                                //the last one
                                else {
                                    completeBracketContentBuilder.append(
                                            paraType + " " + paraNames[index]
                                    );
                                }
                            }
                        }
                        completeBracketContentBuilder.append(" )");
                        sourceCodeBuilder
                                .append(lineSeparator)
                                //.append(method.toString().replace(classToExpose.getName() + ".", ""))
                                .append(method.toString()
                                        .replace(classToExpose.getName() + ".", "")
                                        .replace(theWholeBracketContent, completeBracketContentBuilder))

                                .append("{")
                                .append(lineSeparator)
                                .append("}")
                                .append(lineSeparator);

                        //Todo test print
                        System.out.println("Source is " + sourceCodeBuilder);
                    }
                    else{
                        sourceCodeBuilder
                                .append(lineSeparator)
                                //.append(method.toString().replace(classToExpose.getName() + ".", ""))
                                .append(method.toString()
                                        .replace(classToExpose.getName() + ".", ""))
                                .append("{")
                                .append(lineSeparator)
                                .append("}")
                                .append(lineSeparator);
                    }
                    paraTypes.clear();
                }

                //Generate fields
                for (Field field : fields) {
                    field.setAccessible(true);
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

                    fieldNameBuilder.append(";").append(lineSeparator);
                }
                sourceCodeBuilder.append(fieldNameBuilder.toString());

                //the end
                sourceCodeBuilder.append(lineSeparator + "}");
                stubSources.add(sourceCodeBuilder.toString());
                /*
            }
*/
        }
            FileUtils.writeStringToFile(
                    new File("./scripts/groophite/"
                            + classToExpose.getPackage().getName()
                            .replace(".", "/")
                            + "/"
                            + classToExpose.getName().replace(classToExpose.getPackage().getName()+".","")
                            + ".java"),
                    stubSources.toString()
                            .replace("[","")
                            .replace("]",""),
                    "UTF-8");
            stubSources.clear();
    }
    }


    public static List<Class> getClasssFromPackage(String pack) {
        List<Class> clazzs = new ArrayList<Class>();

        boolean recursive = true;

        String packageName = pack;

        String packageDirName = packageName.replace('.', '/');

        Enumeration<URL> dirs;

        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();

                String protocol = url.getProtocol();

                if ("file".equals(protocol)) {

                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findClassInPackageByFile(packageName, filePath, recursive, clazzs);
                } else if ("jar".equals(protocol)) {

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clazzs;
    }


    public static void findClassInPackageByFile(String packageName, String filePath, final boolean recursive, List<Class> clazzs) {
        File dir = new File(filePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }

        File[] dirFiles = dir.listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {
                boolean acceptDir = recursive && file.isDirectory();
                boolean acceptClass = file.getName().endsWith("class");
                return acceptDir || acceptClass;
            }
        });

        for (File file : dirFiles) {
            if (file.isDirectory()) {
                findClassInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, clazzs);
            } else {
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    clazzs.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



    private static boolean sameType(Type[] types, Class<?>[] clazzes) {
        // 个数不同
        if (types.length != clazzes.length) {
            return false;
        }

        for (int i = 0; i < types.length; i++) {
            if (!Type.getType(clazzes[i]).equals(types[i])) {
                return false;
            }
        }
        return true;
    }


    public static String[] getMethodParamNames(final Method m) {
        final String[] paramNames = new String[m.getParameterTypes().length];
        final String n = m.getDeclaringClass().getName();
        ClassReader cr = null;
        try {
            cr = new ClassReader(n);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cr.accept(new ClassVisitor(Opcodes.ASM4) {
            @Override
            public MethodVisitor visitMethod(final int access,
                                             final String name, final String desc,
                                             final String signature, final String[] exceptions) {
                final Type[] args = Type.getArgumentTypes(desc);

                if (!name.equals(m.getName())
                        || !sameType(args, m.getParameterTypes())) {
                    return super.visitMethod(access, name, desc, signature,
                            exceptions);
                }
                MethodVisitor v = super.visitMethod(access, name, desc,
                        signature, exceptions);
                return new MethodVisitor(Opcodes.ASM4, v) {
                    @Override
                    public void visitLocalVariable(String name, String desc,
                                                   String signature, Label start, Label end, int index) {
                        int i = index - 1;

                        if (Modifier.isStatic(m.getModifiers())) {
                            i = index;
                        }
                        if (i >= 0 && i < paramNames.length) {
                            paramNames[i] = name;
                        }
                        super.visitLocalVariable(name, desc, signature, start,
                                end, index);
                    }

                };
            }
        }, 0);
        return paramNames;
    }

}

