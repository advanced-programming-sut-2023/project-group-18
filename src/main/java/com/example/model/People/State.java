package com.example.model.People;

public enum State {
    STANDING ("standing", 10),
    DEFENSIVE ("defensive", 50),
    OFFENSIVE ("offensive", 1000);

    private final String name;
    private final int additiveRange;

    private State(String name, int additiveRange) {
        this.name = name;
        this.additiveRange = additiveRange;
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

    public int getAdditiveRange() {
        return additiveRange;
    }

}
