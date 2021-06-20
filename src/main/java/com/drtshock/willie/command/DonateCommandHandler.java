package com.drtshock.willie.command;

import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.User;

/**
 *
 * @author drtshock
 */
public class DonateCommandHandler implements CommandHandler
{

    @Override
    public void handle(Channel channel, User sender, String[] args) {
        channel.sendMessage(Colors.CYAN + "Buy me food :3 http://tinyurl.com/drtdonate");
    }

}
