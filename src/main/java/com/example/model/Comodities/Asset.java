package com.example.model.Comodities;

import java.util.HashMap;

public enum Asset {
    BOW ("bow", AssetType.WEAPON, 0),
    CROSSBOW ("crossbow", AssetType.WEAPON, 0),
    SPEAR ("spear", AssetType.WEAPON, 0),
    PIKE ("pike", AssetType.WEAPON, 0),
    MACE ("mace", AssetType.WEAPON, 0),
    SWORDS ("swords", AssetType.WEAPON, 0),
    LEATHER_ARMOR ("leather armor", AssetType.WEAPON, 0),
    METAL_ARMOR ("metal armor", AssetType.WEAPON, 0),

    MEAT ("meat", AssetType.FOOD, 0),
    APPLE ("apple", AssetType.FOOD, 0),
    CHEESE ("cheese", AssetType.FOOD, 0),
    BREAD ("bread", AssetType.FOOD, 0),
    
    WHEAT ("wheat", AssetType.RESOURCE, 0),
    FLOUR ("flour", AssetType.RESOURCE, 0),
    HOPS ("hops", AssetType.RESOURCE, 0),
    ALE ("ale", AssetType.RESOURCE, 0),
    STONE ("stone", AssetType.RESOURCE, 0),
    IRON ("iron", AssetType.RESOURCE, 0),
    WOOD ("wood", AssetType.RESOURCE, 0),
    PITCH ("pitch", AssetType.RESOURCE, 0);

    private final String name;
    private final AssetType assetType;
    private final int price;

    Asset(String name, AssetType assetType, int price) {
        this.name = name;
        this.assetType = assetType;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public int getPrice() {
        return price;
    }

    public static Asset getAssetByName(String name) {
        for (Asset asset : Asset.values())
            if (asset.name.equals(name)) return asset;
        return null;
    }
    
    public static HashMap<AssetType, HashMap<Asset, Integer>> getAllAssets() {
        final HashMap<AssetType, HashMap<Asset, Integer>> assets = new HashMap<>();
        for (AssetType assetType : AssetType.values())
            assets.put(assetType, new HashMap<>());
        for (Asset asset : Asset.values())
            assets.get(asset.assetType).put(asset, 0);
        return assets;
    }
   
    public static HashMap<Asset, Integer> getAllAssets(AssetType assetType) {
        final HashMap<Asset, Integer> assets = new HashMap<>();
        for (Asset asset : Asset.values())
            if (asset.assetType.equals(assetType)) assets.put(asset, 0);
        return assets;
    }
    
}
