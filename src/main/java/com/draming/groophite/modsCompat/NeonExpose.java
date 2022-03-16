

package com.draming.groophite.modsCompat;



import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipFile;

public class NeonExpose {
    public static Path modsPath = Paths.get("/mods");
    public static List<String> uselessFilesAndFolders =
            Stream.of("assets","META-INF","mcmod.info","pack.mcmeta")
                    .collect(Collectors.toList());


    public static void expose() throws IOException {
        if (modsPath.toFile().listFiles().length!=0) {
            for (File file : modsPath.toFile().listFiles()) {
                ZipFile zipFile = new ZipFile(file);
                while (zipFile.entries().hasMoreElements()){
                    boolean isPackageRoot = !uselessFilesAndFolders.contains(zipFile.entries().nextElement().getName());
                    if (isPackageRoot){

                    };
                }
            }
        }
    }
}
