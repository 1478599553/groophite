package com.draming.groophite.modsCompat;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
            String packageName = classToExpose.getPackage().getName();

            StringBuilder sourceCodeBuilder = new StringBuilder();
            StringBuilder fieldNameBuilder = new StringBuilder();
            Field[] fields = classToExpose.getFields();
            Method[] methods = classToExpose.getDeclaredMethods();

            sourceCodeBuilder
                    .append(classToExpose.getPackage())
                    .append(lineSeparator);

            if (classToExpose.isEnum()) {
                sourceCodeBuilder
                        .append("public enum " + classToExpose.getName() + "{" + lineSeparator);
                Iterator<Field> iterator = Arrays.stream(fields).iterator();
                int count = 0;
                StringBuilder enumBuilder = new StringBuilder();
                System.out.println(fields.length);
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
            } else {
                sourceCodeBuilder
                        .append("public class " + classToExpose.getName() + "{" + lineSeparator);

                //Generate methods
                for (Method method : methods) {
                    method.setAccessible(true);
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
                    fieldNameBuilder.append(lineSeparator);
                }
                sourceCodeBuilder.append(fieldNameBuilder.toString());

                //the end
                sourceCodeBuilder.append(lineSeparator + "}");
                stubSources.add(sourceCodeBuilder.toString());
            }

            FileUtils.writeStringToFile(
                    new File("./scripts/groophite/"
                            + classToExpose.getPackage().getName()
                            .replace(".", "/")
                            + "/"
                            + classToExpose.getName().replace(classToExpose.getPackage().getName()+".","")
                            + ".java"),
                    stubSources.toString(), "UTF-8");
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
                    System.out.println("file类型的扫描");
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findClassInPackageByFile(packageName, filePath, recursive, clazzs);
                } else if ("jar".equals(protocol)) {
                    System.out.println("jar类型的扫描");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clazzs;
    }

    /**
     * 在package对应的路径下找到所有的class
     *
     * @param packageName
     *            package名称
     * @param filePath
     *            package对应的路径
     * @param recursive
     *            是否查找子package
     * @param clazzs
     *            找到class以后存放的集合
     */
    public static void findClassInPackageByFile(String packageName, String filePath, final boolean recursive, List<Class> clazzs) {
        File dir = new File(filePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 在给定的目录下找到所有的文件，并且进行条件过滤
        File[] dirFiles = dir.listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {
                boolean acceptDir = recursive && file.isDirectory();// 接受dir目录
                boolean acceptClass = file.getName().endsWith("class");// 接受class文件
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
    }

