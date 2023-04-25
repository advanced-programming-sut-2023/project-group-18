package com.example.model;

public enum Texture {
    DEFAULT ("default"),
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
