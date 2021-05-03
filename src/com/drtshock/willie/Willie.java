package com.drtshock.willie;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.exception.NickAlreadyInUseException;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.DisconnectEvent;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class Willie extends ListenerAdapter implements Listener {

    @Override
    public void onMessage(MessageEvent event) {
        String[] args = event.getMessage().split(" ");
        if (event.getMessage().replaceAll("(?i)willie", "").length() != event.getMessage().length()) {
            event.getChannel().sendMessage("Hello :3");
        }
        if (args[0].equalsIgnoreCase(".repo")) {
            event.getChannel().sendMessage(Colors.BLUE + "Contribute if you feel so led: https://github.com/drtshock/willie");
        } else if (args[0].equalsIgnoreCase(".od")) {
            if (args.length == 1) {
                event.getChannel().sendMessage(Colors.PURPLE + "ObsidianDestroyer http://dev.bukkit.org/server-mods/obsidiandestroyer" + Colors.DARK_GRAY + " Blow up obsidian and other non explosive blocks with TNT. Define the durability of blocks in the config.");
            } else if (args.length == 2) {
                if (args[1].equalsIgnoreCase("release")) {
                    try {
                        String[] ret = CheckUpdate.getUpdate("obsidiandestroyer");
                        event.getChannel().sendMessage(Colors.PURPLE + "Latest release: " + ret[0] + " | Link: " + ret[1]);
                    } catch (IOException e) {
                        event.getChannel().sendMessage(Colors.RED + "Unable to find that plugin!");
                    }
                } else if (args[1].equalsIgnoreCase("dev")) {
                    event.getChannel().sendMessage(Colors.PURPLE + "Dev builds can be found here: http://ci.drtshock.com/job/obsidiandestroyer");
                }
            }
        } else if (args[0].equalsIgnoreCase(".pv")) {
            if (args.length == 1) {
                event.getChannel().sendMessage(Colors.BLUE + "PlayerVaults http://dev.bukkit.org/server-mods/playervaults" + Colors.DARK_GRAY + " Private inventories accessible by command and now by signs! Works with all item meta and is version independent.");
            } else if (args.length == 2) {
                if (args[1].equalsIgnoreCase("release") || args[1].equalsIgnoreCase("latest")) {
                    try {
                        String[] ret = CheckUpdate.getUpdate("playervaults");
                        event.getChannel().sendMessage(Colors.BLUE + "Latest release: " + ret[0] + " | Link: " + ret[1]);
                    } catch (IOException e) {
                        event.getChannel().sendMessage(Colors.RED + "Unable to find that plugin!");
                    }
                } else if (args[1].equalsIgnoreCase("dev")) {
                    event.getChannel().sendMessage(Colors.BLUE + "Dev builds can be found here: http://ci.drtshock.com/job/playervaults/");
                }
            }
        } else if (args[0].equalsIgnoreCase(".latest")) {
            if (args.length == 1) {
                event.getChannel().sendMessage(Colors.RED + "Check latest BukkitDev version of a plugin by doing .latest <slug>");
            } else {
                try {
                    String[] ret = CheckUpdate.getUpdate(args[1]);
                    event.getChannel().sendMessage(Colors.PURPLE + "Latest release: " + ret[0] + " | Link: " + ret[1]);
                } catch (IOException e) {
                    event.getChannel().sendMessage(Colors.RED + "Unable to find that plugin!");
                }
            }
        } else if (args[0].equalsIgnoreCase(".pl")) {
            if (args.length == 1) {
                event.getChannel().sendMessage(Colors.RED + "Look up a plugin with .pl <name>");
            } else {
                try {
                    String[] ret = CheckUpdate.getUpdate(args[1]);
                    event.getChannel().sendMessage(Colors.PURPLE + "http://dev.bukkit.org/server-mods/" + args[1].toLowerCase() + "/");
                } catch (IOException ex) {
                    event.getChannel().sendMessage(Colors.RED + "No project with that slug exists.");
                }
            }
        } else if (args[0].equalsIgnoreCase(".issues") || args[0].equalsIgnoreCase(".i")) {
            if(args.length == 1) {
                event.getChannel().sendMessage(Colors.RED + "Look up issues on one of your github repos with .issues <repo>");
            } else {
                try {
                    CheckIssues.check(event.getUser().toString(), args[1]);
                } catch (MalformedURLException ex) {
                    event.getChannel().sendMessage(Colors.RED + "Couldn't find that repo.");
                } catch (IOException ex) {
                    event.getChannel().sendMessage(Colors.RED + "Something went wrong :(");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, IrcException, NickAlreadyInUseException {
        String[] channels = {"#drtshock"};
        PircBotX bot = new PircBotX();
        bot.setName("Willie");
        bot.setVerbose(true);
        bot.getListenerManager().addListener(new Willie());
        bot.connect("irc.esper.net");
        bot.setAutoReconnectChannels(true);
        for (String c : channels) {
            bot.joinChannel(c);
        }
    }
}
