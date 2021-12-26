package com.drtshock.willie.command.minecraft;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.pircbotx.Channel;
import org.pircbotx.Colors;
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
                JSONObject json = readJsonFromUrl("http://api.iamphoenix.me/get/?server_ip=" + args[0]);
                String motd = json.getString("motd");
                String players = json.getString("players");
                String max = json.getString("players_max");
                String version = json.getString("version");
                channel.sendMessage("(" + args[1] + ") " + motd + " - " + version + " - " + players + "/" + max + " players.");
            } catch (IOException | JSONException ex) {
                channel.sendMessage(Colors.RED + "Failed to read json from url.");
                Logger.getLogger(ServerCommandHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            is.close();
            return json;
        }
    }

    public static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
