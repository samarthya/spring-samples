package com.samarthya.external.model;

public class Message {

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Message() {

    }

    public Message(String content) {
        this.content = content;
    }

    private String content;
}
