package com.drtshock.willie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.logging.Logger;

import org.pircbotx.Base64;
import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.exception.NickAlreadyInUseException;

import com.drtshock.willie.command.CICommandHandler;
import com.drtshock.willie.command.Command;
import com.drtshock.willie.command.DonateCommandHandler;
import com.drtshock.willie.command.HelpCommandHandler;
import com.drtshock.willie.command.IssuesCommandHandler;
import com.drtshock.willie.command.LatestCommandHandler;
import com.drtshock.willie.command.PluginCommandHandler;
import com.drtshock.willie.command.PopcornCommandHandler;
import com.drtshock.willie.command.RepoCommandHandler;
import com.drtshock.willie.command.RulesCommandHandler;
import com.drtshock.willie.command.TWSSCommandHandler;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

public class Willie extends PircBotX {
	public static final Logger logger = Logger.getLogger(Willie.class.getName());
    public static final Gson gson = new Gson();
	public static final JsonParser parser = new JsonParser();
	public static String GIT_AUTH;
    private static String CONFIG_FILE = "config.yml";

	public JenkinsServer jenkins;
	public CommandManager commandManager;
    private WillieConfig willieConfig;

    public Willie() throws IOException {
        this(new WillieConfig());
    }
	
	public Willie(WillieConfig config) throws IOException {
        super();
        this.willieConfig = config;
		
		GIT_AUTH = "Basic " + Base64.encodeToString((willieConfig.getGitHubUsername() + ":" + willieConfig.getGitHubPassword()).getBytes(), false);
		
		this.jenkins = new JenkinsServer(willieConfig.getJenkinsServer());
		this.commandManager = new CommandManager(this);
		
		this.commandManager.registerCommand(new Command("repo", "show Willie's repo", new RepoCommandHandler()));
		this.commandManager.registerCommand(new Command("latest", "<plugin_name> - Get latest file for plugin on BukkitDev", new LatestCommandHandler()));
		this.commandManager.registerCommand(new Command("plugin", "<name> - looks up a plugin on BukkitDev", new PluginCommandHandler()));
		this.commandManager.registerCommand(new Command("issues", "<job_name> [page] - check github issues for jobs on " + willieConfig.getJenkinsServer(), new IssuesCommandHandler()));
		this.commandManager.registerCommand(new Command("ci", "shows Jenkins info", new CICommandHandler()));
		this.commandManager.registerCommand(new Command("rules", "show channel rules", new RulesCommandHandler()));
		this.commandManager.registerCommand(new Command("help", "show this help info", new HelpCommandHandler()));
		this.commandManager.registerCommand(new Command("p", "pop some popcorn!", new PopcornCommandHandler()));
		this.commandManager.registerCommand(new Command("twss", "that's what she said!", new TWSSCommandHandler()));
		this.commandManager.registerCommand(new Command("donate", "shows donation info", new DonateCommandHandler()));
		
		this.setName(willieConfig.getNick());
		this.setVerbose(false);
		this.getListenerManager().addListener(this.commandManager);
	}

    public void connect() throws NickAlreadyInUseException, IrcException, IOException {
        this.connect(willieConfig.getServer());
        this.setAutoReconnectChannels(true);
        logger.info("Connected to '" + willieConfig.getServer() + "'");

        for (String channel : willieConfig.getChannels()){
            this.joinChannel(channel);
            logger.info("Joined channel '" + channel + "'");
        }

        (new Timer()).schedule(new IssueNotifierTask(this), 300000, 300000); // 5 minutes
    }
    public WillieConfig getConfig() {
        return willieConfig;
    }
	
	public static void main(String[] args){
		try{
            Willie willie = new Willie(WillieConfig.loadFromFile("config.yml"));
            willie.connect();
		} catch (NickAlreadyInUseException ex){
		    Willie.logger.severe("Nickname already in use!");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
	}
	
}
