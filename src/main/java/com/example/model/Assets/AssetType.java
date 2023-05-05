package com.example.model.Assets;

public enum AssetType {
    FOOD ("Food"),
    WEAPON ("Weapon"),
    RESOURCE ("Resource");

    private final String name;

    AssetType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
