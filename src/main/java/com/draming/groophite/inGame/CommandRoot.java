package com.draming.groophite.inGame;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CommandRoot extends CommandBase {
    @Override
    public String getName() {
        return "gt";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "coming soon";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args[0].equals("reload")) {
            com.draming.groophite.processor.Reloader.reloadScript();
            sender.sendMessage(new TextComponentString("Reload completed!"));
        }
    }
    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        if (args.length == 1)
        {
            String[] cmds = {"reload"};
            return CommandBase.getListOfStringsMatchingLastWord(args, cmds);
        }
        return null;
    }
}
