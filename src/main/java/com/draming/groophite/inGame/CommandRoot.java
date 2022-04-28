package com.draming.groophite.inGame;

import com.draming.groophite.modsCompat.NeonExpose;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import com.draming.groophite.groophite;
import net.minecraftforge.fml.common.Loader;

public class CommandRoot extends CommandBase {
    @Override
    public String getName() {
        return "grt";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "coming soon";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        switch (args[0]) {
            case ("reload"):
                try {
                    com.draming.groophite.processor.Reloader.reloadScript();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                sender.sendMessage(new TextComponentString(I18n.format("groophite.reloaded.text")));
                break;
            case ("regName"):
                String regName = ((EntityPlayer) sender).getHeldItemMainhand().getItem().getRegistryName().toString();
                sender.sendMessage(new TextComponentString(regName));
                Transferable transferable = new StringSelection(regName);
                groophite.clipboard.setContents(transferable, null);
                sender.sendMessage(new TextComponentString(I18n.format("groophite.copied.text")));
                break;
            case ("dump"):
                new Thread(new DumpTask(server)).start();
                break;
        }
    }
        //if (args[0].equals("reload")) {

        //}


    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        if (args.length == 1)
        {
            String[] cmds = {"reload","regName","dump"};
            return CommandBase.getListOfStringsMatchingLastWord(args, cmds);
        }
        return null;
    }
}
