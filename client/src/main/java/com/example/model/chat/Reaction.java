package com.example.model.chat;

import com.example.model.User;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Reaction {
    @XmlElement
    private ReactionType reactionType;
    @XmlElement
    private final User sender;

    public Reaction(ReactionType reactionType, User sender) {
        this.reactionType = reactionType;
        this.sender = sender;
    }

    public ReactionType getReactionType() {
        return reactionType;
    }

    public User getSender() {
        return sender;
    }

    public void setReactionType(ReactionType reactionType) {
        this.reactionType = reactionType;
    }
}
