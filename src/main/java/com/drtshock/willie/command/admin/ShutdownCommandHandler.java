package com.drtshock.willie.command.admin;

import org.pircbotx.Channel;
import org.pircbotx.User;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;

/**
 * @author drtshock
 */
public class ShutdownCommandHandler implements CommandHandler {

	@Override
	public void handle(Willie bot, Channel channel, User sender, String[] args) {
		channel.sendMessage("Away with me!");
		while(bot.getOutgoingQueueSize() > 0) {
			// Wait for all pending messages to be sent
		}
		bot.shutdown(true);
	}
}
