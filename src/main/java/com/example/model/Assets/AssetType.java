package com.example.model.Assets;

import com.example.model.Buildings.StorageType;

public enum AssetType {
    FOOD ("Food",StorageType.GRANARY),
    WEAPON ("Weapon", StorageType.ARMOURY),
    RESOURCE ("Resource", StorageType.STOCKPILE);

    private final String name;
    private final StorageType storageType;

    AssetType(String name, StorageType storageType) {
        this.name = name;
        this.storageType = storageType;
    }

    public String getName() {
        return name;
    }

    public StorageType getStorageType(){
        return this.storageType;
    }

}
