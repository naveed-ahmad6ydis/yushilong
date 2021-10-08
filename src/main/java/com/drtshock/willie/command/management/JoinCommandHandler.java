package com.drtshock.willie.command.management;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;
import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

public class JoinCommandHandler implements CommandHandler {
    @Override
    public void handle(MessageEvent<Willie> event, Willie bot, Channel channel, User sender, String[] args) {
        if (args.length < 1) return;
        if (!args[0].startsWith("#")) args[0] = ("#" + args[0]).toLowerCase();

        if (bot.isOnChannel(args[0])) {
            event.respond(String.format(Colors.GREEN + "Already there!"));
        } else {
            event.respond(Colors.GREEN + String.format("See you in %s!", args[0]));
            bot.joinChannel(args[0]);
            bot.getChannel(args[0]).sendMessage(Colors.GREEN + String.format("%s told me I belong here.", sender.getNick()));
        }
    }
}
