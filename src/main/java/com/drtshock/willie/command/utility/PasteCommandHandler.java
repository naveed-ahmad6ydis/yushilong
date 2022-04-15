package com.drtshock.willie.command.utility;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;
import com.drtshock.willie.github.GistHelper;
import org.pircbotx.Channel;
import org.pircbotx.User;

public class PasteCommandHandler implements CommandHandler {

    @Override
    public void handle(Willie bot, Channel channel, User sender, String[] args) throws Exception {
        final String toBePasted = "Hello Wolrd!";
        channel.sendMessage("Here: " + GistHelper.gist(toBePasted));
    }

}
