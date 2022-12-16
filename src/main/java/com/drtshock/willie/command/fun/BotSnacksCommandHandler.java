package com.drtshock.willie.command.fun;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;
import org.pircbotx.Channel;
import org.pircbotx.User;

public class BotSnacksCommandHandler implements CommandHandler {

	@Override
	public void handle(Willie bot, Channel channel, User sender, String[] args) {
		bot.sendAction(channel, "NOM NOM NOM");
	}
}
