package com.drtshock.willie.command;

import com.drtshock.willie.Willie;
import org.pircbotx.Channel;
import org.pircbotx.User;

import java.awt.*;

public class LeaveCommandHandler implements CommandHandler {
    @Override
    public void handle(Willie bot, Channel channel, User sender, String[] args) {
        // Prevent leaving the last channel.
        if(bot.getChannels().size() == 1) {
            channel.sendMessage(Color.RED + "Please don't make me leave!");
            return;
        }

        if(args.length != 0 && !channel.getName().equals(args[0])) {
            channel.sendMessage(Color.RED + String.format("Leaving channel %s", args[0]));
            channel = bot.getChannel(args[0]);
        }
        else {
            channel.sendMessage(Color.RED + String.format("%s says I don't belong here...", sender.getNick()));
        }
        bot.partChannel(channel);
        bot.getConfig().removeChannel(channel.getName());
    }
}
