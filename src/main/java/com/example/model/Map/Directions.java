package com.example.model.Map;

public enum Directions {
    RIGHT("right", 1, 0),
    LEFT("left", -1, 0),
    UP("up",  0, -1),
    DOWN("down", 0, 1);
    private final String name;
    private final int[] coordinate;

    Directions(String name, int x, int y) {
        this.name = name;
        int[] coordinate = {x, y};
        this.coordinate = coordinate;
    }

    public String getName() {
        return name;
    }

    public int[] getCoordinate() {
        return coordinate;
    }

    public static Directions getDirectionByName(String name) {
        for (Directions directions : Directions.values()) {
            if (directions.name.equals(name))
                return directions;
        }
        return null;
    }
}
