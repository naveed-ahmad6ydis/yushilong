package com.drtshock.willie.command.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.User;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;

public class PluginCommandHandler implements CommandHandler {
	
	private SimpleDateFormat dateFormat;
	
	public PluginCommandHandler(){
		this.dateFormat = new SimpleDateFormat("EEEE dd MMMM YYYY");
	}
	
	@Override
	public void handle(Willie bot, Channel channel, User sender, String[] args){
		if (args.length != 1){
			channel.sendMessage(Colors.RED + "Look up a plugin with .plugin <name>");
			return;
		}
		
		try{
			URL url = new URL("http://dev.bukkit.org/projects/" + args[0] + "/");
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(10000);
			connection.setUseCaches(false);
			
			BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			StringBuilder buffer = new StringBuilder();
			String line;
			
			while ((line = input.readLine()) != null){
				buffer.append(line);
				buffer.append('\n');
			}
			
			String page = buffer.toString();
			
			input.close();
			
			Document document = Jsoup.parse(page);
			
			String name = document.getElementsByTag("h1").get(1).ownText().trim();
			long lastUpdate = Long.parseLong(document.getElementsByClass("standard-date").get(1).attr("data-epoch"));
			int downloads = Integer.parseInt(document.getElementsByAttribute("data-value").first().attr("data-value"));
			
			channel.sendMessage(name + " (" + connection.getURL().toExternalForm() + ")");
			channel.sendMessage("Downloads: " + downloads);
			channel.sendMessage("Last Update: " + this.dateFormat.format(new Date(lastUpdate * 1000)));
		}catch (FileNotFoundException e){
			channel.sendMessage(Colors.RED + "Project not found");
		}catch (MalformedURLException e){
			channel.sendMessage(Colors.RED + "Unable to find that plugin!");
		}catch (IOException e){
			channel.sendMessage(Colors.RED + "Failed: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
}
