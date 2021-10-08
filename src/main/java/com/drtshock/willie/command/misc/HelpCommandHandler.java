package com.drtshock.willie.command.misc;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.Command;
import com.drtshock.willie.command.CommandHandler;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

public class HelpCommandHandler implements CommandHandler {

    @Override
    public void handle(MessageEvent<Willie> event, Willie bot, Channel channel, User sender, String[] args) {
        String cmdPrefix = bot.getConfig().getCommandPrefix();
        for (Command command : bot.commandManager.getCommands()) {
            sender.sendMessage(cmdPrefix + command.getName() + " - " + command.getHelp());
        }
    }

}
