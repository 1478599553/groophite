package com.draming.groophite.processor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadFileContent {
    public static String readFileContent(File file){
        try{
            StringBuilder builder = new StringBuilder();
            String data;
            Path path = file.toPath();
            List<String> data_lines = Files.readAllLines(path);
            for (String line : data_lines){
                builder.append(line+"\r\n");
            }
            return com.draming.groophite.processor.HealthyFixer.Fix(builder.toString());
        }
        catch (IOException exception){
            exception.printStackTrace();
            return null;
        }
        
    }
}
