package com.example.model.Buildings;

public enum StorageType {
    GRANARY("Granary"),
    ARMOURY("Armoury"),
    STOCKPILE("Stockpile");

    private final String name;

    StorageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
