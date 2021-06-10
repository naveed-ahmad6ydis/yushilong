/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drtshock.willie.command;

import org.pircbotx.Channel;
import org.pircbotx.User;

/**
 *
 * @author drtshock
 */
public class HelpCommandHandler implements CommandHandler
{

    @Override
    public void handle(Channel channel, User sender, String[] args) {
        sender.sendMessage(".ci - shows Jenkins info");
        sender.sendMessage(".issues <job_name> [page] - check github issues for jobs on http://ci.drtshock.com");
        sender.sendMessage(".latest <plugin_name> - Get latest file for plugin on BukkitDev");
        sender.sendMessage(".plugin <name> - looks up a plugin on BukkitDev");
        sender.sendMessage(".repo - show Willie's repo");
        sender.sendMessage(".rules - show channel rules");
        sender.sendMessage(".p - pop some popcorn!");
    }

}
