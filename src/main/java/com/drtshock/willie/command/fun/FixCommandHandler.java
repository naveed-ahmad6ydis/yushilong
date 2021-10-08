package com.drtshock.willie.command.fun;

import com.drtshock.willie.command.CommandHandler;
import org.pircbotx.Channel;
import org.pircbotx.User;

import com.drtshock.willie.Willie;
import org.pircbotx.hooks.events.MessageEvent;


public class FixCommandHandler implements CommandHandler {

    @Override
    public void handle(MessageEvent<Willie> event, Willie bot, Channel channel, User sender, String[] args) {
        if(args.length != 1) {
            event.respond("Fix it!");
        } else {
            event.respond(String.format("Fix it %s!", args[0]));
        }
    }

}
