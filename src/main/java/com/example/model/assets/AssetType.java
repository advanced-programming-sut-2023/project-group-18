package com.example.model.assets;

import com.example.model.buildings.BuildingType;

public enum AssetType {
    FOOD ("Food", BuildingType.FOOD_STORAGE),
    WEAPON ("Weapon", BuildingType.ARMOURY),
    RESOURCE ("Resource", BuildingType.STOCKPILE);

    private final String name;
    private final BuildingType buildingType;

    private AssetType(String name, BuildingType buildingType) {
        this.name = name;
        this.buildingType = buildingType;
    }

    public static AssetType getAssetTypeByName(String name){
        for (AssetType assetType : AssetType.values())
            if (assetType.name.equals(name))
                return assetType;
        return null;
    }

    public static AssetType getAssetTypeByBuildingType(BuildingType buildingType) {
        for (AssetType assetType : AssetType.values())
            if (assetType.buildingType.equals(buildingType))
                return assetType;
        return null;
    }

    public String getName() {
        return name;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

}
