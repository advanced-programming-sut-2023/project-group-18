package com.example.model.People;

public enum State {
    STANDING ("standing"),
    DEFENSIVE ("defensive"),
    OFFENSIVE ("offensive");

    private final String name;

    private State(String name) {
        this.name = name;
    }

    public static boolean isStateNameCorrect(String name) {
        return getStateByName(name) != null;
    }

    public static State getStateByName(String name) {
        for (State value : values())
            if (value.name.equals(name)) return value;
        return null;
    }

    public String getName() {
        return name;
    }

}
