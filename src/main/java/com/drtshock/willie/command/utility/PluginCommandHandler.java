package com.drtshock.willie.command.utility;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;
import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PluginCommandHandler implements CommandHandler {

    @Override
    public void handle(MessageEvent<Willie> event, Willie bot, Channel channel, User sender, String[] args) {
        if (args.length != 1) {
            event.respond(Colors.RED + "Look up a plugin with .plugin <name>");
            return;
        }

        try {
            URL url = new URL("http://dev.bukkit.org/projects/" + args[0] + "/");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setUseCaches(false);

            BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder buffer = new StringBuilder();
            String line;

            while ((line = input.readLine()) != null) {
                buffer.append(line);
                buffer.append('\n');
            }

            String page = buffer.toString();

            input.close();

            int namePos = page.indexOf("<h1>") + 4;
            int dlPos = page.indexOf("<dt>Downloads</dt>") + 49;
            int updatePos = page.indexOf("<dt>Last update</dt>") + 68;

            String name = page.substring(namePos, page.indexOf("</h1>", namePos)).trim();
            int downloads = Integer.parseInt(page.substring(dlPos, page.indexOf('"', dlPos)));
            String lastUpdate = page.substring(updatePos, page.indexOf('"', updatePos));

            event.respond("Name: " + name + " (" + connection.getURL().toExternalForm() + ")");
            event.respond("Downloads: " + downloads);
            event.respond("Last Update: " + lastUpdate);
        } catch (FileNotFoundException e) {
            event.respond(Colors.RED + "Project not found");
        } catch (MalformedURLException e) {
            event.respond(Colors.RED + "Unable to find that plugin!");
        } catch (IOException e) {
            event.respond(Colors.RED + "Failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
