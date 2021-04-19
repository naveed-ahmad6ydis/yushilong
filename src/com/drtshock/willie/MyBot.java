/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drtshock.willie;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jibble.pircbot.Colors;
import org.jibble.pircbot.PircBot;

/**
 *
 * @author drtshock
 */
public class MyBot extends PircBot {

    private String ch = "#drtshock";

    public MyBot() {
        this.setName("Willie");
    }

    public void onMessage(String channel, String sender,
            String login, String hostname, String message) {
        if (channel.equalsIgnoreCase(ch)) {
            if(message.equalsIgnoreCase("willie")) {
                sendMessage(ch, "Hello :3");
            }
            else if(message.equalsIgnoreCase(".repo")) {
                sendMessage(ch, Colors.CYAN + "Contribute if you feel so led: "
                        + "https://github.com/drtshock/willie");
            }
            else if (message.equalsIgnoreCase(".od")) {
                sendMessage(ch, Colors.PURPLE + "ObsidianDestroyer http://dev.bukkit.org/server-mods/obsidiandestroyer"
                        + Colors.DARK_GRAY + " Blow up obsidian and other non explosive blocks with TNT. Define the durability of blocks"
                        + " in the config.");
            } else if (message.equalsIgnoreCase(".od release") || message.equalsIgnoreCase(".od version")) {
                try {
                    sendMessage(ch, Colors.PURPLE + "Latest release: " + CheckUpdate.getUpdate("obsidiandestroyer"));
                } catch (Exception ex) {
                    Logger.getLogger(MyBot.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (message.equalsIgnoreCase(".pv")) {
                sendMessage(ch, Colors.BLUE + "PlayerVaults http://dev.bukkit.org/server-mods/playervaults"
                        + Colors.DARK_GRAY + " Private inventories accessible by command and now by signs! Works with all item meta "
                        + "and is version independent.");
            } else if (message.equalsIgnoreCase(".pv release") || message.equalsIgnoreCase(".pv version")) {
                try {
                    sendMessage(ch, Colors.BLUE + "Latest release: " + CheckUpdate.getUpdate("playervaults"));
                } catch (Exception ex) {
                    Logger.getLogger(MyBot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(message.startsWith(".latest")) {
                if(message.length() == 7) {
                    sendMessage(ch, Colors.RED + "Check latest BukkitDev version of a plugin by doing .latest <slug>");
                }
                else {
                    String slug = message.replaceAll(".latest ", "");
                    try {
                        sendMessage(ch, Colors.DARK_GREEN + "Latest version: " + CheckUpdate.getUpdate(slug));
                    } catch (Exception ex) {
                        sendMessage(ch, Colors.RED + "I couldn't find that project.");
                    }
                }
            }
        }

    }
}