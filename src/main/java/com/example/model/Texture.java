package com.example.model;

public enum Texture {
    DEFAULT ("default"),
    GROUND ("ground"),
    GRAVEL ("gravel"),
    STONE ("stone"),
    IRON ("iron"),
    LAWN ("lawn"),
    RARE_GRASSLAND ("rare grassland"),
    OVERGROWN_GRASSLAND ("overgrown grassland"),
    PETROLEUM ("petroleum"),
    PLAIN ("plain"),
    SHALLOW_WATER ("shallow water"),
    RIVER ("river"),
    POOL ("pool"),
    BEACH ("beach"),
    SEA ("sea")

    ;

    private final String name;

    Texture(String name) {
        this.name = name;
    }

    public static Texture getTextureByName(String name) {
        for (Texture values : Texture.values())
            if (values.name.equals(name)) return values;
        return null;
    }

}
