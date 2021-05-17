package com.drtshock.willie.command;

import java.io.IOException;

import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.User;

import com.drtshock.willie.CheckUpdate;

public class PluginCommandHandler implements CommandHandler {
	
	@Override
	public void handle(Channel channel, User sender, String[] args){
		if (args.length == 1){
			channel.sendMessage(Colors.RED + "Look up a plugin with .pl <name>");
		}else{
			try{
				String[] ret = CheckUpdate.getUpdate(args[1]);
				channel.sendMessage(Colors.PURPLE + "http://dev.bukkit.org/server-mods/" + args[1].toLowerCase() + "/");
			}catch (IOException ex){
				channel.sendMessage(Colors.RED + "No project with that slug exists.");
			}
		}
	}
	
}
