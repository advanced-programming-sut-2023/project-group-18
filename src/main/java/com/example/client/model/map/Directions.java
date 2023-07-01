package com.example.client.model.map;

import com.example.client.model.buildings.Direction;

public enum Directions {
    RIGHT("right", 1, 0, Direction.RIGHT),
    LEFT("left", -1, 0, Direction.LEFT),
    UP("up",  0, -1, Direction.UP),
    DOWN("down", 0, 1, Direction.DOWN);

    private final String name;
    private final int[] coordinate;
    private final Direction direction;

    Directions(String name, int x, int y, Direction direction) {
        this.name = name;
        int[] coordinate = {x, y};
        this.coordinate = coordinate;
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public int[] getCoordinate() {
        return coordinate;
    }

    public Direction getDirection() {
        return direction;
    }

    public static Directions getDirectionByName(String name) {
        for (Directions directions : Directions.values()) {
            if (directions.name.equals(name))
                return directions;
        }
        return null;
    }
}
