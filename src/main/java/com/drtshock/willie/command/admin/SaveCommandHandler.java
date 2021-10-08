package com.drtshock.willie.command.admin;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;
import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

public class SaveCommandHandler implements CommandHandler {
    @Override
    public void handle(MessageEvent<Willie> event, Willie bot, Channel channel, User sender, String[] args) {
        bot.save();
        event.respond(Colors.GREEN + "Configuration saved!");
    }
}
