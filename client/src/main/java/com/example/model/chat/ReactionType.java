package com.example.model.chat;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum ReactionType {
    LIKE("like"),
    DISLIKE("dislike"),
    ;
    private final String name;

    ReactionType(String name) {
        this.name = name;
    }
}
