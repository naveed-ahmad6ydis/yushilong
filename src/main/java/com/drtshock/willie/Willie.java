package com.drtshock.willie;

import java.io.IOException;

import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.exception.NickAlreadyInUseException;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

public class Willie extends PircBotX {
	
	public static final Gson gson = new Gson();
	public static final JsonParser parser = new JsonParser();
	
	public JenkinsServer jenkins;
	
	private Willie(String[] channels) throws NickAlreadyInUseException, IOException, IrcException{
		super();
		
		this.jenkins = new JenkinsServer("http://ci.drtshock.com/");
		
		this.setName("Jaceks_Willie");
		this.setVerbose(true);
		this.getListenerManager().addListener(new CommandListener(this));
		this.connect("irc.esper.net");
		this.setAutoReconnectChannels(true);
		
		for (String channel : channels){
			this.joinChannel(channel);
		}
	}
	
	public static void main(String[] args){
		try{
			new Willie(new String[]{"#scbottest"});
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
