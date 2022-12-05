package com.drtshock.willie.command.fun;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;
import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.User;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author stuntguy3000
 */
public class TrendsCommandHandler implements CommandHandler {

    @Override
    public void handle(Willie bot, Channel channel, User sender, String[] args) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(bot.getConfig().getTwitterConsumerKey())
                .setOAuthConsumerSecret(bot.getConfig().getTwitterConsumerKeySecret())
                .setOAuthAccessToken(bot.getConfig().getTwitterAccessToken())
                .setOAuthAccessTokenSecret(bot.getConfig().getTwitterAccessTokenSecret());
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        
        try {
            Trends trend = twitter.getPlaceTrends(1);
            int trendsToShow = bot.getConfig().getTwitterTrendsToShow();
            
            if (trendsToShow < 1) {
                channel.sendMessage(Colors.RED + "Invalid amount of trends!");
                return;
            }
            
            if (trendsToShow < 2) {
            	channel.sendMessage(Colors.CYAN + "The top trend on twitter right now...");
            } else {
            	channel.sendMessage(Colors.CYAN + "Top " + trendsToShow + " trends on Twitter right now...");
            }
            
            int count = 0;
            for (Trend t : trend.getTrends()) {
                if (trendsToShow > 0) {
                	count = count + 1;
                    channel.sendMessage(count + ". " + t.getName());
                }
                
                trendsToShow =- 1;
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
