package com.drtshock.willie;

import org.pircbotx.Channel;

public class IssueData {

    String author;
    String title;
    String link;
    static String project;

    public IssueData(String author, String title, String link, String proj, Channel chan) {
        this.author = author;
        this.title = title;
        this.link = link;
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
    
}
