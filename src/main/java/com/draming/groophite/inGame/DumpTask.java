package com.draming.groophite.inGame;

import com.draming.groophite.groophite;
import com.draming.groophite.modsCompat.GlobalsGenerator;
import com.draming.groophite.modsCompat.NeonExpose;
import net.minecraft.server.MinecraftServer;

import java.io.IOException;

public class DumpTask implements Runnable{
    private final MinecraftServer server;
    public DumpTask(MinecraftServer server){
        this.server = server;
    }

    @Override
    public void run() {

        try {
            GlobalsGenerator.generateGlobals();
            NeonExpose.expose(groophite.isCraftTweakerLoaded);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            NeonExpose.decompile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.addScheduledTask(new SendMsgTask(server,"Dump completed."));
    }
}
