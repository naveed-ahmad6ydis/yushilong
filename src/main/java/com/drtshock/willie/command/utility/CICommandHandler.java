package com.drtshock.willie.command.utility;

import com.drtshock.willie.jenkins.JenkinsJob;
import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;
import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.IOException;

public class CICommandHandler implements CommandHandler {

    @Override
    public void handle(MessageEvent<Willie> event, Willie bot, Channel channel, User sender, String[] args) {
        if (args.length != 1) {
            String message = String.format(Colors.BLUE + "Get dev builds at %s If you're interesting in hosting a project there, talk to %s",
                    bot.getConfig().getJenkinsServer(), bot.getConfig().getJenkinsAdmins());
            event.respond(message);
        } else try {
            JenkinsJob job = bot.jenkins.getJob(args[0]);
            event.respond(String.format("Project %s %s -", job.getDisplayName(), job.getUrl()));

            // Health report
            for (JenkinsJob.HealthReport report : job.getHealthReports()) {
                String color;
                switch (report.score) {
                    case 100:
                        color = Colors.GREEN;
                        break;
                    case 80:
                        color = Colors.BLUE;
                        break;
                    default:
                        color = Colors.RED;
                        break;
                }
                String msg[] = report.description.split(":");
                event.respond(msg[0] + ":" + color + msg[1]);
            }

            // Repository url
            if (!job.getGitHubUrl().isEmpty()) {
                event.respond("Repository: " + job.getGitHubUrl());
            }

            // Issues
            if (job.getIssues().length != 0) {
                event.respond("Total issues: " + Colors.RED + job.getIssues().length);
            }

        } catch (IOException e) {
            event.respond(Colors.RED + "Sorry, I don't know anything about that project.");
        }
    }

}
