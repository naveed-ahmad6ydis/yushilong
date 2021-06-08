/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drtshock.willie.command;

import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.User;

/**
 *
 * @author drtshock
 */
public class RulesCommandHandler implements CommandHandler {

    @Override
    public void handle(Channel channel, User sender, String[] args) {
        channel.sendMessage(Colors.RED + "Don't be an annoying douche. Ask Chester if you have help");
    }
}
