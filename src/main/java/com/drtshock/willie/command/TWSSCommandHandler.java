package com.drtshock.willie.command;

import org.pircbotx.Channel;
import org.pircbotx.User;

public class TWSSCommandHandler implements CommandHandler {
	
	@Override
	public void handle(Channel channel, User sender, String[] args){
		channel.sendMessage("That's what she said!");
	}
	
}
