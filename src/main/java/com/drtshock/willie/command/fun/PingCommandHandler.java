package com.drtshock.willie.command.fun;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;
import org.pircbotx.Channel;
import org.pircbotx.User;

/**
 *
 * @author stuntguy3000
 */
public class PingCommandHandler implements CommandHandler {

    @Override
    public void handle(Willie bot, Channel channel, User sender, String[] args) {
        bot.sendAction(channel, "I sure want to pong " + sender.getNick() + " right now...");
    }

}