package com.example.system.hackathon.model;

public class Consultion {
    private String senderId;
    private String recieverId;
    private String consultion;
    private String respond;

    public Consultion() {
    }

    public Consultion(String senderId, String recieverId, String consultion, String respond) {
        this.senderId = senderId;
        this.recieverId = recieverId;
        this.consultion = consultion;
        this.respond = respond;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(String recieverId) {
        this.recieverId = recieverId;
    }

    public String getConsultion() {
        return consultion;
    }

    public void setConsultion(String consultion) {
        this.consultion = consultion;
    }
}
