package com.example.model;

public enum Texture implements ConsoleColors {
    GROUND ("ground", BLACK_BACKGROUND),
    GRAVEL ("gravel", YELLOW_BACKGROUND),
    STONE ("stone", WHITE_BACKGROUND),
    IRON ("iron", WHITE_BACKGROUND_BRIGHT),
    LAWN ("lawn", RED_BACKGROUND),
    RARE_GRASSLAND ("rare grassland", GREEN_BACKGROUND),
    OVERGROWN_GRASSLAND ("overgrown grassland", GREEN_BACKGROUND_BRIGHT),
    PETROLEUM ("petroleum", PURPLE_BACKGROUND),
    PLAIN ("plain", BLUE_BACKGROUND),
    SHALLOW_WATER ("shallow water", BLUE_BACKGROUND),
    RIVER ("river", BLUE_BACKGROUND),
    POOL ("pool", BLUE_BACKGROUND),
    BEACH ("beach", BLUE_BACKGROUND),
    SEA ("sea", BLUE_BACKGROUND)

    ;

    public final String name;
    public final String color;

    Texture(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public static Texture getTextureByName(String name) {
        for (Texture values : Texture.values())
            if (values.name.equals(name)) return values;
        return null;
    }

}
