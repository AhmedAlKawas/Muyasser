package com.example.system.hackathon.model;

public class Users {
    private String email;
    private String username;
    private String rule;
    private int language;
    private String id;

    public Users(String email, String username, String rule, int language, String id) {
        this.email = email;
        this.username = username;
        this.rule = rule;
        this.language = language;
        this.id = id;
    }

    public Users() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
