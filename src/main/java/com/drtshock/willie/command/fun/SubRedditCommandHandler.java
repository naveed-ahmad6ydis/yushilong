package com.drtshock.willie.command.fun;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;
import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.User;

/**
 * @author stuntguy3000
 */
public class SubRedditCommandHandler implements CommandHandler {

    @Override
    public void handle(Willie bot, Channel channel, User sender, String[] args) {
        if (args.length == 0) {
            channel.sendMessage(Colors.RED + "Please specify a subreddit!");
        } else {
            if (!args[0].matches("^[A-Za-z0-9][A-Za-z0-9_]{2,20}$")) {
                channel.sendMessage(Colors.RED + "Only subreddits with letters and numbers are valid!");
            } else {
                channel.sendMessage(Colors.BOLD + "http://www.reddit.com/r/" + args[0]);
            }
        }
    }
}
