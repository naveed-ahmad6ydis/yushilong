/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drtshock.willie.command;

import java.util.Random;
import org.pircbotx.Channel;
import org.pircbotx.User;

/**
 *
 * @author drtshock
 */
public class PopcornCommandHandler implements CommandHandler {

    private final Random rand = new Random();

    @Override
    public void handle(Channel channel, User sender, String[] args) {
        int num = getRandom(1, 4);
        if (num == 4) {
            channel.getBot().sendAction(channel, "pops some plain popcorn.");
        } else if (num == 3) {
            channel.getBot().sendAction(channel, "pops some popcorn with butter.");
        } else if (num == 2) {
            channel.getBot().sendAction(channel, "pops some popcorn with salt and butter!");
        } else {
            channel.getBot().sendAction(channel, "pops some kettle corn.");
        }
    }

    private int getRandom(int min, int max) {
        return rand.nextInt(max - min) + min;
    }
}
