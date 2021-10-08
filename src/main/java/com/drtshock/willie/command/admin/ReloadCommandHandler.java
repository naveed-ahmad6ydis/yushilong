package com.drtshock.willie.command.admin;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;
import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * @author B2OJustin
 */
public class ReloadCommandHandler implements CommandHandler {
    @Override
    public void handle(MessageEvent<Willie> event, Willie bot, Channel channel, User sender, String[] args) {
        event.respond(Colors.GREEN + String.format("Yes master %s! Reloading!", sender.getNick()));
        bot.reload();
        event.respond(Colors.GREEN + "Configuration Reloaded.");
    }
}
