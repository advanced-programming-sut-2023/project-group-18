package com.example.model.Buildings;

import java.util.Random;

public enum TreeType {
    DESERT_SHRUB ("desert shrub"),

    ;

    private final String name;

    private TreeType(String name) {
        this.name = name;
    }

    public static TreeType getTreeTypeByName(String name) {
        for (TreeType value : values())
            if (value.name.equals(name)) return value;
        return null;
    }

    public static TreeType getRandomTreeType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    public String getName() {
        return name;
    }

}
