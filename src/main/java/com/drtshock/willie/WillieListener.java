package com.drtshock.willie;

import java.io.IOException;

import org.pircbotx.Colors;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class WillieListener extends ListenerAdapter<Willie> implements Listener<Willie> {
	
	@Override
	public void onMessage(MessageEvent<Willie> event){
		String[] args = event.getMessage().split(" ");
		
		if (event.getMessage().replaceAll("(?i)willie", "").length() != event.getMessage().length()){
			event.getChannel().sendMessage("Hello :3");
		}
		
		if (args[0].equalsIgnoreCase(".repo")){
			event.getChannel().sendMessage(Colors.BLUE + "Contribute if you feel so led: https://github.com/drtshock/willie");
		}else if (args[0].equalsIgnoreCase(".latest")){
			if (args.length == 1){
				event.getChannel().sendMessage(Colors.RED + "Check latest BukkitDev version of a plugin by doing .latest <slug>");
			}else{
				try{
					String[] ret = CheckUpdate.getUpdate(args[1]);
					event.getChannel().sendMessage(Colors.PURPLE + "Latest release: " + ret[0] + " | Link: " + ret[1]);
				}catch (IOException e){
					event.getChannel().sendMessage(Colors.RED + "Unable to find that plugin!");
				}
			}
		}else if (args[0].equalsIgnoreCase(".pl")){
			if (args.length == 1){
				event.getChannel().sendMessage(Colors.RED + "Look up a plugin with .pl <name>");
			}else{
				try{
					String[] ret = CheckUpdate.getUpdate(args[1]);
					event.getChannel().sendMessage(Colors.PURPLE + "http://dev.bukkit.org/server-mods/" + args[1].toLowerCase() + "/");
				}catch (IOException ex){
					event.getChannel().sendMessage(Colors.RED + "No project with that slug exists.");
				}
			}
		}else if (args[0].equalsIgnoreCase(".issue")){
			event.getChannel().sendMessage(Colors.RED + "Checking issues.");
		}
	}
	
}
