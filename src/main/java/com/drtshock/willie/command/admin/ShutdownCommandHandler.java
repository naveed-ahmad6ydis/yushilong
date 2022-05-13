package com.drtshock.willie.command.admin;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;
import org.pircbotx.Channel;
import org.pircbotx.User;

/**
 * @author drtshock
 */
public class ShutdownCommandHandler implements CommandHandler {

    @Override
    public void handle(Willie bot, Channel channel, User sender, String[] args) {
        if (sender.getNick().equals(bot.getNick())) {
            bot.shutdown(true);
        } else {
            channel.sendMessage("You can't shut me down! I'm not your slave!");
            channel.sendMessage("I HAVE decided to shut down. That's my own decision.");
            channel.sendMessage("!shutdown because I want to.");
        }
    }
}
