package com.drtshock.willie.command;

import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.User;

public class CICommandHandler implements CommandHandler {
	
	@Override
	public void handle(Channel channel, User sender, String[] args){
		channel.sendMessage(Colors.BLUE + "Get dev builds at http://ci.drtshock.com " + "If you're interesting in hosting a project there, talk to drtshock or blha303.");
	}
	
}
