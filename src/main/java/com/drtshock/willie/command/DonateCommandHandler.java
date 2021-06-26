package com.drtshock.willie.command;

import java.util.Random;
import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.User;

public class DonateCommandHandler implements CommandHandler {
	
	private final Random rand = new Random();
	
	@Override
	public void handle(Channel channel, User sender, String[] args){
		int num = getRandom(1, 4);
		
		if (num == 4){
			channel.sendMessage(Colors.DARK_GREEN + "Buy me food :3 http://tinyurl.com/drtdonate");
		}else if (num == 3){
			channel.sendMessage(Colors.DARK_GREEN + "Donations are nice. http://tinyurl.com/drtdonate");
		}else if (num == 2){
			channel.sendMessage(Colors.DARK_GREEN + "Hey there http://tinyurl.com/drtdonate");
		}else{
			channel.sendMessage(Colors.DARK_GREEN + "Buy dirt for drt http://tinyurl.com/drtdonate");
		}
	}
	
	private int getRandom(int min, int max){
		return rand.nextInt(max - min) + min;
	}
	
}
