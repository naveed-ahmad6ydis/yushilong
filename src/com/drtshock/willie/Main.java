/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drtshock.willie;

/**
 *
 * @author drtshock
 */
public class Main {
    
    public static void main(String[] args) throws Exception {
        MyBot bot = new MyBot();
        
        bot.setVerbose(true);
        
        bot.connect("irc.esper.net");
        
        bot.joinChannel("#drtshock");
    }

}
