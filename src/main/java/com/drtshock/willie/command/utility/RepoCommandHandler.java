package com.drtshock.willie.command.utility;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;
import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

public class RepoCommandHandler implements CommandHandler {

    @Override
    public void handle(MessageEvent<Willie> event, Willie bot, Channel channel, User sender, String[] args) {
        event.respond(Colors.BLUE + "Contribute if you feel so led: " + bot.getConfig().getBotSourceUrl());
    }

}
