/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drtshock.willie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map.Entry;
import org.jibble.pircbot.Colors;
import org.json.JSONArray;
import org.json.JSONObject;
import org.pircbotx.Channel;

/**
 *
 * @author drtshock
 */
public class IssueData {

    private static HashMap<Integer, IssueData> data = new HashMap<>();
    String author;
    String title;
    String link;
    static Channel channel;
    static String project;

    public IssueData(String author, String title, String link, String proj, Channel chan) {
        this.author = author;
        this.title = title;
        this.link = link;
        channel = chan;
        project = proj;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public String getLink() {
        return this.link;
    }

    @Override
    public String toString() {
        return this.author + " - " + this.title + " - " + this.link;
    }

    public static void main(String[] args) throws Exception {
        URL url = new URL("https://api.github.com/repos/drtshock/" + project + "/issues/events");
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (UnknownHostException e) {
            return;
        }
        String line;
        String bigline = "";
        while ((line = in.readLine()) != null) {
            bigline += line;
        }
        in.close();
        JSONArray json = new JSONArray(bigline);
        for (int i = 0; i < json.length(); i++) {
            JSONObject issue = json.getJSONObject(i).getJSONObject("issue");
            JSONObject actor = json.getJSONObject(i).getJSONObject("actor");
            int num = issue.getInt("number");
            String title = issue.getString("title");
            String link = issue.getString("url");
            String author = actor.getString("login");
            IssueData id = new IssueData(author, title, link, project, channel);
            if (!data.containsKey(num)) {
                data.put(num, id);
            }
        }
        for (Entry<Integer, IssueData> e : data.entrySet()) {
            print(e.getKey() + " - " + e.getValue());
        }
    }

    public static <T> void print(T o) {
        channel.sendMessage(Colors.RED+ "[New Issue] " + Colors.BLUE + project + Colors.NORMAL + o);
    }
}
