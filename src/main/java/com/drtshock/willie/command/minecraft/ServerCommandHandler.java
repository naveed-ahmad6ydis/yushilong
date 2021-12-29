package com.drtshock.willie.command.minecraft;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;
import com.drtshock.willie.util.WebHelper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.pircbotx.Channel;
import org.pircbotx.User;

/**
 *
 * @author drtshock
 */
public class ServerCommandHandler implements CommandHandler {

    @Override
    public void handle(Willie bot, Channel channel, User sender, String[] args) {
        if (args.length == 0) {
            channel.sendMessage("Usage: !server <IP>");
        } else {
            try {
                JsonObject obj = new JsonParser().parse(WebHelper.readURLToString(new URL("http://api.iamphoenix.me/get/?server_ip=" + args[0]))).getAsJsonObject();
                String json = obj.getAsString();
                String motd = obj.get("motd").getAsString();
                
                channel.sendMessage(json + " string: " + motd);
            } catch (IOException ex) {
                channel.sendMessage(ex.toString());
            }

        }
    }
}
