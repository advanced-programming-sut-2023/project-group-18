package com.example.client.model.assets;

import java.util.HashMap;

public enum Asset {
    BOW ("bow", AssetType.WEAPON, 10),
    CROSSBOW ("crossbow", AssetType.WEAPON, 10),
    SPEAR ("spear", AssetType.WEAPON, 10),
    PIKE ("pike", AssetType.WEAPON, 10),
    MACE ("mace", AssetType.WEAPON, 10),
    SWORDS ("swords", AssetType.WEAPON, 10),
    LEATHER_ARMOR ("leather armor", AssetType.WEAPON, 10),
    METAL_ARMOR ("metal armor", AssetType.WEAPON, 10),
    TORCH("Torch", AssetType.WEAPON, 10),
    SLING("Sling", AssetType.WEAPON, 10),
    SCIMITAR("Scimitar", AssetType.WEAPON, 10),
    ARABIAN_BOW("Bow", AssetType.WEAPON, 10),
    GREEK_FIRE("Greek Fire", AssetType.WEAPON, 10),
    ARABIAN_METAL_ARMOR("Metal Armour", AssetType.WEAPON, 10),
    STAFF("Staff", AssetType.WEAPON, 10),
    PICK_AXE("Pick Axe", AssetType.WEAPON, 10),
    MEAT ("meat", AssetType.FOOD, 10),
    APPLE ("apple", AssetType.FOOD, 10),
    CHEESE ("cheese", AssetType.FOOD, 10),
    BREAD ("bread", AssetType.FOOD, 10),
    
    WHEAT ("wheat", AssetType.RESOURCE, 10),
    FLOUR ("flour", AssetType.RESOURCE, 10),
    HOPS ("hops", AssetType.RESOURCE, 10),
    ALE ("ale", AssetType.RESOURCE, 10),
    STONE ("stone", AssetType.RESOURCE, 10),
    IRON ("iron", AssetType.RESOURCE, 10),
    WOOD ("wood", AssetType.RESOURCE, 10),
    PITCH ("pitch", AssetType.RESOURCE, 10);

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

    public int getBuyPrice() {
        return price;
    }

    public int getSellPrice() {
        return (price * 4) / 5;
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
    

    @Override
    public String toString() {
        return name + " [Buy = " + price + "] [Sell = " + getSellPrice() + "] ";
    }

}
