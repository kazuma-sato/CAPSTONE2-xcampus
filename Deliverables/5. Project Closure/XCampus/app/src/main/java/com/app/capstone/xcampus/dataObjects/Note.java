package com.app.capstone.xcampus.dataObjects;

/**
 * Created by pavle on 2017-04-20.
 */

public class Note {


    private String username;
    private String id;
    private String auther;
    private String title;
    private String entryType;
    private String desc;
    private String dateCreated;

    public Note(String username, String id, String auther, String title, String entryType, String desc, String dateCreated) {
        this.username = username;
        this.id = id;
        this.auther = auther;
        this.title = title;
        this.entryType = entryType;
        this.desc = desc;
        this.dateCreated = dateCreated;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
