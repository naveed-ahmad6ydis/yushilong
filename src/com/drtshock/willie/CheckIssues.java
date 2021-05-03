package com.drtshock.willie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author drtshock
 */
public class CheckIssues  {

    /**
     * I'm really not sure how to do this. Hopefully I'll learn sooner or later.
     * We need to run this on a task for both my repos as well so it checks
     * when there are new tickets.
     * 
     * URL for github api: http://developer.github.com/v3/issues/
     * 
     * @param owner
     * @param repo
     * @return string of useful information.
     * @throws MalformedURLException
     * @throws IOException 
     */
    public static String[] check(String owner, String repo) throws MalformedURLException, IOException {
        String[] stuff = new String[4];
        URL url = new URL("http://github.com/" + owner + "/" + repo +  "/issues/");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (UnknownHostException e) {
            throw new IOException();
        }
        JSONObject json;
        try {
            json = new JSONObject(in.readLine()).getJSONArray("somethinghere").getJSONObject(0);
            stuff[0] = json.getString("ticket");
            stuff[1] = json.getString("status");
        } catch (JSONException e) {
            throw new IOException();
        }
        in.close();
        return stuff;
    }
    
}
