package com.example.model.chat;

import com.example.model.User;

public class Reaction {
    private ReactionType reactionType;
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
