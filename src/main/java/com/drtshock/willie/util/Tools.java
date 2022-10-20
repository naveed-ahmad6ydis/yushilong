package com.drtshock.willie.util;

import org.pircbotx.Colors;

/**
 * This class contains VERY little tools
 */
public class Tools {

    /**
     * Inserts a blank character inside the String to prevent pinging people
     *
     * @param string The username to silence
     *
     * @return The silenced username
     */
    public static String silence(String string) {
        if (string == null || string.length() < 3) {
            return string;
        } else {
            return string.substring(0, 1) + (char) 0x200b + string.substring(1, string.length());
        }
    }

    /**
     * Return a colorized String containing nbChars c characters, with left% of
     * them colored with leftColor and right% of them colored with rightColor.
     */
    public static String asciiBar(double left, String leftColor, double right, String rightColor, int nbChars, char barCharacter, char separator, String separatorColor) {
        double total = left + right;
        int nbLeftChars = (int) Math.round(left * nbChars / total);
        int nbRightChars = nbChars - nbLeftChars;
        final StringBuilder builder = new StringBuilder();
        builder.append(leftColor);
        for (int i = 0; i < nbLeftChars; i++) {
            builder.append(barCharacter);
        }
        builder.append(separatorColor);
        builder.append(separator);
        builder.append(rightColor);
        for (int i = 0; i < nbRightChars; i++) {
            builder.append(barCharacter);
        }
        builder.append(Colors.NORMAL);
        return builder.toString();
    }
}
