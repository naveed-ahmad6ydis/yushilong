package com.drtshock.willie.command;

import java.io.IOException;

import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.User;

import com.drtshock.willie.CheckUpdate;

public class LatestCommandHandler implements CommandHandler {
	
	@Override
	public void handle(Channel channel, User sender, String[] args){
		if (args.length == 1){
			channel.sendMessage(Colors.RED + "Check latest BukkitDev version of a plugin by doing .latest <slug>");
		}else{
			try{
				String[] ret = CheckUpdate.getUpdate(args[1]);
				channel.sendMessage(Colors.PURPLE + "Latest release: " + ret[0] + " | Link: " + ret[1]);
			}catch (IOException e){
				channel.sendMessage(Colors.RED + "Unable to find that plugin!");
			}
		}
	}
	
}
