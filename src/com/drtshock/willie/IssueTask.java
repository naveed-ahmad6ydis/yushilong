package com.drtshock.willie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author drtshock
 */
public class IssueTask implements Runnable {

    public static HashMap<String, Long> issues = new HashMap<>();
    private boolean drtIsAwesome = true;

    @Override
    public void run() {
        while (drtIsAwesome) {
            try {
                checkNewOpen("drtshock", "playervaults");
                checkNewOpen("drtshock", "obsidiandestroyer");
            } catch (MalformedURLException ex) {
                Logger.getLogger(IssueTask.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(IssueTask.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Thread.sleep(30000);
            } catch (InterruptedException ex) {
                Logger.getLogger(IssueTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static String[] checkNewOpen(String owner, String repo) throws MalformedURLException, IOException {
        String[] stuff = new String[4];
        URL url = new URL("http://apti.github.com/" + owner + "/" + repo + "/issues/events");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (UnknownHostException e) {
            throw new IOException();
        }
        JSONObject json;
        try {
            json = new JSONObject(in.readLine()).getJSONArray("somethinghere").getJSONObject(0);
            long id = Long.valueOf(json.getString("id"));
            if (!issues.containsValue(id)) {
                issues.put(repo, id);

            }
        } catch (JSONException e) {
            
        }
        in.close();
        return stuff;
    }
}
