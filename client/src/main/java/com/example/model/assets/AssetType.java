package com.example.model.assets;

import com.example.model.buildings.StorageType;

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
    public static AssetType getAssetTypeByName(String name){
        for (AssetType assetType : AssetType.values()){
            if (assetType.getName().equals(name))
                return assetType;
        }
        return null;
    }

}
