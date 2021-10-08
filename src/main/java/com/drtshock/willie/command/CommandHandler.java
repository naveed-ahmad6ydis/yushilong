package com.drtshock.willie.command;

import com.drtshock.willie.Willie;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

public interface CommandHandler {

    public void handle(MessageEvent<Willie> event, Willie bot, Channel channel, User sender, String[] args);

}
