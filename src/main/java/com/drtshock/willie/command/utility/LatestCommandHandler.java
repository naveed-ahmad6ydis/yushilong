package com.drtshock.willie.command.utility;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;
import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LatestCommandHandler implements CommandHandler {

    @Override
    public void handle(MessageEvent<Willie> event, Willie bot, Channel channel, User sender, String[] args) {
        if (args.length != 1) {
            event.respond(Colors.RED + "Check latest BukkitDev version of a plugin by doing .latest <slug>");
            return;
        }

        try {
            URL url = new URL("http://dev.bukkit.org/projects/" + args[0] + "/files.rss");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setUseCaches(false);

            Document feed = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(connection.getInputStream());

            NodeList titles = feed.getElementsByTagName("title");
            NodeList links = feed.getElementsByTagName("link");

            if (titles.getLength() <= 1 || links.getLength() <= 1) {
                event.respond(args[0] + " has no files");
                return;
            }

            event.respond(Colors.PURPLE + "Latest release: " + titles.item(1).getTextContent() + " | Link: " + links.item(1).getTextContent());
        } catch (FileNotFoundException e) {
            event.respond(Colors.RED + "Project not found");
        } catch (SAXException e) {
            event.respond(Colors.RED + "Failed: " + e.getMessage());
        } catch (MalformedURLException e) {
            event.respond(Colors.RED + "Unable to find that plugin!");
        } catch (IOException e) {
            event.respond(Colors.RED + "Failed: " + e.getMessage());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

}
