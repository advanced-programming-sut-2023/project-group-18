package com.example.model.chat;

public enum ReactionType {
    LIKE("like"),
    DISLIKE("dislike"),
    ;
    private final String name;

    ReactionType(String name) {
        this.name = name;
    }
}
