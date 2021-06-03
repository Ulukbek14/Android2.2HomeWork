package com.example.android22homework;

import java.io.Serializable;

public class TaskModel implements Serializable {
    String title, description, date;

    public TaskModel(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
}
