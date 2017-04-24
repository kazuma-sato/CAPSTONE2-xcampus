package com.app.capstone.xcampus.dataObjects;

/**
 * Created by pavle on 2017-03-14.
 */

public class Notification {

    private String entryId;
    private String userId;
    private String actionId;

    public Notification(String entryId, String userId, String actionId) {
        this.entryId = entryId;
        this.userId = userId;
        this.actionId = actionId;
    }

}
