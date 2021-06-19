package com.drtshock.willie;

import com.drtshock.willie.command.CICommandHandler;
import java.util.HashMap;

import org.pircbotx.Channel;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.drtshock.willie.command.CommandHandler;
import com.drtshock.willie.command.DonateCommandHandler;
import com.drtshock.willie.command.HelpCommandHandler;
import com.drtshock.willie.command.IssuesCommandHandler;
import com.drtshock.willie.command.LatestCommandHandler;
import com.drtshock.willie.command.PluginCommandHandler;
import com.drtshock.willie.command.PopcornCommandHandler;
import com.drtshock.willie.command.RepoCommandHandler;
import com.drtshock.willie.command.RulesCommandHandler;
import com.drtshock.willie.command.TWSSCommandHandler;

public class CommandListener extends ListenerAdapter<Willie> implements Listener<Willie> {
	
	private Willie bot;
	private HashMap<String, CommandHandler> handlers;
	
	public CommandListener(Willie bot){
		this.bot = bot;
		this.handlers = new HashMap<String, CommandHandler>();
		
		this.handlers.put("repo", new RepoCommandHandler());
		this.handlers.put("latest", new LatestCommandHandler());
		this.handlers.put("plugin", new PluginCommandHandler());
		this.handlers.put("issues", new IssuesCommandHandler(bot));
                this.handlers.put("ci", new CICommandHandler());
                this.handlers.put("rules", new RulesCommandHandler());
                this.handlers.put("help", new HelpCommandHandler());
                this.handlers.put("p", new PopcornCommandHandler());
                this.handlers.put("twss", new TWSSCommandHandler());
                this.handlers.put("donate", new DonateCommandHandler());
	}
	
	@Override
	public void onMessage(MessageEvent<Willie> event){
		String message = event.getMessage();
		
		if (message.isEmpty() || message.charAt(0) != '!'){
			return;
		}
		
		String[] parts = message.substring(1).split(" ");
		Channel channel = event.getChannel();
		
		String command = parts[0].toLowerCase();
		String[] args = new String[parts.length - 1];
		System.arraycopy(parts, 1, args, 0, args.length);
		
		CommandHandler handler = this.handlers.get(command);
		
		if (handler != null){
			handler.handle(channel, event.getUser(), args);
		}
	}
	
}
