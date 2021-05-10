package com.drtshock.willie;

public class IssueTask implements Runnable {

    private boolean drtIsAwesome = true;

    @Override
    public void run() {
        System.out.println("calling runnable.");
        while (drtIsAwesome) {
            IssueData sueData = new IssueData(null, null, null, null, Willie.chan);
        }
    }
}
