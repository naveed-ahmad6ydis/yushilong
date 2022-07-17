package com.drtshock.willie.command.minecraft;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;
import com.drtshock.willie.util.Tools;
import com.drtshock.willie.util.WebHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MCStatsCommandHandler implements CommandHandler {

    private static final Logger LOG = Logger.getLogger(MCStatsCommandHandler.class.getName());

    @Override
    public void handle(Willie bot, Channel channel, User sender, String[] args) throws Exception {
        if (args.length != 1) {
            channel.sendMessage(Colors.RED + "Look up a plugin with !plugin <name>");
            return;
        }

        String mcStatsURL = "http://mcstats.org/plugin/";
        String pluginStatsURL = mcStatsURL + args[0];

        try {
            Document doc = parse(getPage(pluginStatsURL));

            PluginStats stats = PluginStats.get(doc);

            channel.sendMessage(Colors.BOLD + "MCStats" + Colors.NORMAL + " informations for plugin " + Colors.DARK_GREEN + stats.name +
                                Colors.NORMAL + " - " + WebHelper.shortenURL(pluginStatsURL));
            channel.sendMessage("Rank: " + Colors.BOLD + stats.rank + Colors.NORMAL + " (" + colorizeDiff(stats.rankDiff) +
                                ") | Servers: " + Colors.BOLD + stats.servers + Colors.NORMAL + " (" + colorizeDiff(stats.serversDiff) +
                                ") | Players: " + Colors.BOLD + stats.players + Colors.NORMAL + " (" + colorizeDiff(stats.playersDiff) +
                                ")");

            String authModeJsonString = getPage("http://api.mcstats.org/1.0/" + stats.name + "/graph/Auth+Mode");
            if (!authModeJsonString.contains("NO DATA")) {
                JsonObject authModeJson = new JsonParser().parse(authModeJsonString).getAsJsonObject();
                JsonArray array = authModeJson.getAsJsonArray("data");

                String offlineModeAmount = array.get(0).getAsJsonArray().get(0).getAsString().substring(9);
                offlineModeAmount = offlineModeAmount.substring(0, offlineModeAmount.length() - 1);
                String offlineModePercentage = array.get(0).getAsJsonArray().get(1).getAsString();

                String onlineModeAmount = array.get(1).getAsJsonArray().get(0).getAsString().substring(8);
                onlineModeAmount = onlineModeAmount.substring(0, onlineModeAmount.length() - 1);
                String onlineModePercentage = array.get(1).getAsJsonArray().get(1).getAsString();

                double left = Double.parseDouble(onlineModePercentage);
                double right = Double.parseDouble(offlineModePercentage);

                channel.sendMessage("Auth: " + Tools.asciiBar(left, Colors.DARK_GREEN, right, Colors.RED, 20, '█') +
                                    " | " + Colors.DARK_GREEN + onlineModePercentage + "% (" + onlineModeAmount + ")" + Colors.NORMAL +
                                    " - " + Colors.RED + offlineModePercentage + "% (" + offlineModeAmount + ")");
            } else {
                channel.sendMessage("Sorry, no auth information :-(");
            }
        } catch (FileNotFoundException | MalformedURLException | IndexOutOfBoundsException | NumberFormatException e) {
            LOG.log(Level.INFO, "Plugin could not be found.", e);
            channel.sendMessage(Colors.RED + "Plugin could not be found.");
        } catch (IOException e) {
            channel.sendMessage(Colors.RED + "Failed: " + e.getMessage());
            throw e; // Gist
        }
    }

    private String getPage(String urlString) throws IOException {
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);
        connection.setUseCaches(false);

        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder buffer = new StringBuilder();
        String line;

        while ((line = input.readLine()) != null) {
            buffer.append(line);
            buffer.append('\n');
        }

        String page = buffer.toString();

        input.close();

        return page;
    }

    private Document parse(String page) {
        return Jsoup.parse(page);
    }

    private static class PluginStats {

        public String name;
        public String rank;
        public String rankDiff;
        public String servers;
        public String serversDiff;
        public String players;
        public String playersDiff;

        private PluginStats() {
        }

        public static PluginStats get(Document doc) {
            PluginStats res = new PluginStats();
            res.name = doc.getElementsByClass("open").get(0).getElementsByTag("strong").get(0).ownText().trim();

            Element statBoxes = doc.getElementsByClass("stat-boxes").get(0);

            Element rankLi = statBoxes.child(0);
            Element serversLi = statBoxes.child(1);
            Element playersLi = statBoxes.child(2);

            Element rankLiStrong = rankLi.getElementsByClass("right").get(0).getElementsByTag("strong").get(0);
            if (rankLiStrong.children().size() == 0) {
                res.rank = rankLiStrong.ownText().trim();
            } else {
                res.rank = rankLiStrong.child(0).ownText().trim();
            }
            res.rankDiff = rankLi.getElementsByClass("left").get(0).ownText().trim();
            res.rankDiff = res.rankDiff.replace("&plusmn;", "±");

            Element serversLiStrong = serversLi.getElementsByClass("right").get(0).getElementsByTag("strong").get(0);
            if (serversLiStrong.children().size() == 0) {
                res.servers = serversLiStrong.ownText().trim();
            } else {
                res.servers = serversLiStrong.child(0).ownText().trim();
            }
            res.serversDiff = serversLi.getElementsByClass("left").get(0).ownText().trim();
            res.serversDiff = res.serversDiff.replace("&plusmn;", "±");

            Element playersLiStrong = playersLi.getElementsByClass("right").get(0).getElementsByTag("strong").get(0);
            if (playersLiStrong.children().size() == 0) {
                res.players = playersLiStrong.ownText().trim();
            } else {
                res.players = playersLiStrong.child(0).ownText().trim();
            }
            res.playersDiff = playersLi.getElementsByClass("left").get(0).ownText().trim();
            res.playersDiff = res.playersDiff.replace("&plusmn;", "±");

            return res;
        }
    }

    private String colorizeDiff(String diff) {
        if (diff.contains("+")) {
            return Colors.BOLD + Colors.DARK_GREEN + diff + Colors.NORMAL;
        } else if (diff.contains("-")) {
            return Colors.BOLD + Colors.RED + diff + Colors.NORMAL;
        } else {
            return Colors.BOLD + Colors.DARK_GRAY + diff + Colors.NORMAL;
        }
    }

}
