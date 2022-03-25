

package com.draming.groophite.modsCompat;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import scala.tools.nsc.io.Jar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;




public class NeonExpose {
    //TODO remember to edit this to "./mods".
    public static Path modsPath = Paths.get("mods");
    public static List<String> packageNames = new ArrayList<>();
    public static List<String> uselessFilesAndFolders =
            Stream.of("assets","META-INF","mcmod.info","pack.mcmeta")
                    .collect(Collectors.toList());
    public static final String PATH_SEPARATOR = File.separator;

    public static void expose(boolean isCraftTweakerLoaded) throws IOException, ClassNotFoundException {

        if (modsPath.toFile().listFiles().length!=0) {
            for (File file : Objects.requireNonNull(modsPath.toFile().listFiles())){
                if (file.getName().endsWith(".jar"))
                {
                    //TODO test print
                    System.out.println("Now exposing the mod folder: "+modsPath.toFile().getAbsolutePath());
                    System.out.println("Exposing mod : " + file.getName());

                    JarFile jarFile = new JarFile(file);
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        try{
                        JarEntry entry = entries.nextElement();
                        if (entry.getName().endsWith(".class")) {
                            if (entry.getName().contains("jei")){
                                continue;
                            }
                            if (entry.getName().contains("groovy")){
                                break;
                            }
                            if (entry.getName().contains("jboss")){
                                continue;
                            }
                            if (entry.getName().contains("apache")){
                                continue;
                            }
                            if (entry.getName().contains("slf4j")){
                                continue;
                            }
                            /*
                            if (entry.getName().contains("pyrotech")){
                                System.out.println("It's pyrotech!!!");
                                continue;
                            }*/

                            String className = entry.getName()
                                    .replace("/", ".")
                                    .replace(".class", "");



                            Class clazz;



                            try {
                                clazz = Class.forName(className);
                            }
                            catch (Exception exception){
                                clazz = NeonExpose.class;
                                System.out.println("Error on exposing class: "+className);
                                System.out.println(exception.toString());
                            }


                            if (isCraftTweakerLoaded) {
                                try {
                                    String nestedClassPureName = className
                                            .split("\\.")[className.split("\\.").length-1]+"$";

                                    InputStream stream = jarFile.getInputStream(entry);
                                    stanhebben.zenscript.annotations.ZenClass zenClass
                                            = (stanhebben.zenscript.annotations.ZenClass)
                                            clazz.getAnnotation
                                                    (stanhebben.zenscript.annotations.ZenClass.class);

                                    if (zenClass != null) {
                                        System.out.println("Exposing ZenClass: " + className);
                                        writeToLocal("./scripts/groophite/" + entry.getName(), stream);
                                        Enumeration<JarEntry> entryEnumeration = jarFile.entries();
                                        while (entryEnumeration.hasMoreElements()){
                                            JarEntry subEntry = entryEnumeration.nextElement();
                                            if (subEntry.getName().contains(nestedClassPureName)){
                                                InputStream nestedClassStream = jarFile.getInputStream(subEntry);
                                                writeToLocal("./scripts/groophite/" + subEntry.getName(), nestedClassStream);
                                            }
                                        }
                                    }
                                    stream.close();
                                }catch (Exception e) {
                                System.out.println(e.getMessage());
                                }
/*
                                ClassReader reader = new ClassReader(stream);
                                ClassNode cn = new ClassNode();//创建ClassNode,读取的信息会封装到这个类里面
                                reader.accept(cn, 0);//开始读取
                                List<AnnotationNode> annotations = cn.visibleAnnotations;//获取声明的所有注解
                                if (annotations != null){
                                    for (AnnotationNode annotationNode : annotations){
                                        String anno = annotationNode.desc.replaceAll("/", ".");
                                        String annoName = anno.substring(1, anno.length()-1);
                                        if(stanhebben.zenscript.annotations.ZenClass.class.getName().equals(annoName)) {
                                            writeToLocal("./scripts/groophite/" + entry.getName(), stream);
                                        }
                                    }
                                }
                                stream.close();

                            }*/

                        }

                    }
                }catch (NoClassDefFoundError error){
                            System.out.println("Error when exposing mod: "+file.getName());
                        }
            }
        }
    }}
    }
    private static void writeToLocal(String destination, InputStream input)
            throws IOException {
        int index;
        byte[] bytes = new byte[1024];
        File fileOut = new File(destination);

        if (!fileOut.exists()){
            fileOut.getParentFile().mkdirs();
        }

        FileOutputStream downloadFile = new FileOutputStream(destination);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        input.close();
    }



}

