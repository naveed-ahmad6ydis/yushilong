package com.drtshock.willie.auth;

import com.drtshock.willie.Willie;
import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.WaitForQueue;
import org.pircbotx.hooks.events.NoticeEvent;

public class Auth {

    public static boolean checkAuth(User user) {
        return Willie.getInstance().getChannel("#Willie").getOps().contains(user);
    }
}
