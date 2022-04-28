package com.draming.groophite.inGame;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class SendMsgTask implements Runnable{
    private final MinecraftServer server;
    private final String msg;
    public SendMsgTask(MinecraftServer server,String msg){
        this.server = server;
        this.msg = msg;
    }
    @Override
    public void run() {
        server.sendMessage(new TextComponentString(this.msg));
    }
}
