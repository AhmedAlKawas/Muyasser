package com.example.system.hackathon.model;

public class Feeds {
    public Feeds(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Feeds() {

    }

    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
