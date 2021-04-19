/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drtshock.willie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;

/**
 *
 * @author drtshock
 */
public class CheckUpdate  {

    static String newVersion = "";
    
    public static String getUpdate(String slug) throws Exception {
        URL url = new URL("http://dev.bukkit.org/server-mods/" + slug + "/files.rss");
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(url.openStream());
        } catch(UnknownHostException e) {
            return newVersion;
        }
        BufferedReader in = new BufferedReader(isr);
        String line;
        int lineNum = 0;
        while ((line = in.readLine()) != null) {
            if(line.length() != line.replace("<title>", "").length()) {
                line = line.replaceAll("<title>", "").replaceAll("</title>", "").replaceAll("	", "").substring(1);
                if(lineNum == 1) {
                    newVersion = line;
                    Integer newVer = Integer.parseInt(line.replace(".", ""));
                }
                lineNum = lineNum + 1;
            }
        }
        in.close();
        return newVersion;
    }
}
