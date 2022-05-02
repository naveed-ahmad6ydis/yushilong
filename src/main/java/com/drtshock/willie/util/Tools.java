package com.drtshock.willie.util;

/**
 * This class contains VERY little tools
 */
public class Tools {

    /**
     * Inserts a blank character inside the String to prevent pinging people
     *
     * @param string The username to silence
     * @return The silenced username
     */
    public static String silence(String string) {
        if (string == null || string.length() < 3) {
            return string;
        } else {
            return string.substring(0, 1) + (char) 0x200b + string.substring(1, string.length());
        }
    }
}
