package com.drtshock.willie;

import java.io.IOException;

import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.exception.NickAlreadyInUseException;

public class Willie extends PircBotX {
	
	private Willie(String[] channels) throws NickAlreadyInUseException, IOException, IrcException{
		super();
		
		this.setName("Willie");
		this.setVerbose(true);
		this.getListenerManager().addListener(new WillieListener());
		this.connect("irc.esper.net");
		this.setAutoReconnectChannels(true);
		
		for (String channel : channels){
			this.joinChannel(channel);
		}
	}
	
	public static void main(String[] args){
		try{
			new Willie(new String[]{"#drtshock-private"});
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
