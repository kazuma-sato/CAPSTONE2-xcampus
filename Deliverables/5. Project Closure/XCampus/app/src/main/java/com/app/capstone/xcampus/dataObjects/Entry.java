package com.app.capstone.xcampus.dataObjects;

import org.json.JSONArray;

/**
 * Created by pavle on 2017-03-14.
 */

public class Entry {

    private String id;
    private String auther;
    private String parentId;
    private String title;
    private String entryType;
    private String description;
    private String dateCreated;
    private String dateModified;
    private String courseId;
    private String programCode;
    private String instition;
    private String startSemester;
    private String flaggedBy;
    private String dateFlagged;
    private JSONArray comments;


    public Entry(String id, String auther, String parentId, String title, String entryType, String description, String dateCreated, String dateModified, String courseId, String programCode, String instition, String startSemester, String flaggedBy, String dateFlagged) {
        this.id = id;
        this.auther = auther;
        this.parentId = parentId;
        this.title = title;
        this.entryType = entryType;
        this.description = description;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.courseId = courseId;
        this.programCode = programCode;
        this.instition = instition;
        this.startSemester = startSemester;
        this.flaggedBy = flaggedBy;
        this.dateFlagged = dateFlagged;
    }
}
