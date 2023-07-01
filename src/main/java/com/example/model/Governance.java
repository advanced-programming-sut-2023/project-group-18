package com.example.model;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.model.assets.Asset;
import com.example.model.assets.AssetType;
import com.example.model.buildings.Building;
import com.example.model.buildings.Storage;
import com.example.model.people.Unit;

public class Governance {
    private final User owner;
    private final ArrayList<Building> buildings;
    private final ArrayList<Unit> units;
    private final HashMap<AssetType, HashMap<Asset, Integer>> assets;
    private final PopularityFactors popularityFactors;
    private final ArrayList<Trade> tradeList;
    private final ArrayList<Trade> requestList;
    private final ArrayList<Trade> tradeHistory;
    private final ArrayList<Trade> tradeNotifications;
    private int gold;
    private int population;
    private Building selectedBuilding;
    private Unit selectedUnit;

    public Governance(User owner) {
        this.owner = owner;
        buildings = new ArrayList<>();
        units = new ArrayList<>();
        assets = Asset.getAllAssets();
        popularityFactors = new PopularityFactors(this);
        tradeList = new ArrayList<>();
        requestList = new ArrayList<>();
        tradeHistory = new ArrayList<>();
        tradeNotifications = new ArrayList<>();
        gold = 100;
        population = 50;
    }

    public User getOwner() {
        return owner;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public HashMap<AssetType, HashMap<Asset, Integer>> getAssets() {
        return assets;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public PopularityFactors getPopularityFactors() {
        return popularityFactors;
    }

    protected ArrayList<Trade> getTradeList() {
        return tradeList;
    }

    protected ArrayList<Trade> getRequestList() {
        return requestList;
    }

    protected ArrayList<Trade> getTradeHistory() {
        return tradeHistory;
    }

    protected ArrayList<Trade> getTradeNotifications() {
        return tradeNotifications;
    }

    public int getGold() {
        return gold;
    }

    public int getPopulation() {
        return population;
    }

    public Building getSelectedBuilding() {
        return selectedBuilding;
    }

    public Unit getSelectedUnit() {
        return selectedUnit;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public void addPopulation(int population) {
        this.population += population;
    }

    public void setSelectedBuilding(Building selectedBuilding) {
        this.selectedBuilding = selectedBuilding;
    }

    public void setSelectedUnit(Unit selectedUnit) {
        this.selectedUnit = selectedUnit;
    }

    // TODO: move to GameController
    public boolean canAcceptTrade(int id) {
        Trade trade = Trade.getTradebyId(id, tradeList);
        if (trade == null) return false;
        return trade.getPrice() <= gold;
    }

    // TODO: move to GameController
    public void acceptTrade(int id, String meesage) {
        Trade.getTradebyId(id, tradeList).acceptTrade(meesage);
    }

    // TODO: move to GameController
    public void rejectTrade(int id, String message) {
        Trade.getTradebyId(id, tradeList).rejectTrade(message);
    }

    // TODO: move to GameController
    public void cancelTrade(int id) {
        Trade.getTradebyId(id, requestList).cancelTrade();
    }

    // TODO: move to GameController
    public void requestTrade(Governance accepter, Asset resourceType, int resourceAmount, int price, String message) {
        new Trade(this, accepter, resourceType, resourceAmount, price, message);
    }

    // TODO: move to GameController
    public String showNotifications() {
        String result = "Trade Notifications: ";
        int index = 0;
        for (Trade trade : tradeNotifications)
            result += "\n" + (++index) + ") " + trade.toStringTradeList();
        tradeNotifications.clear();
        return result;
    }

    // TODO: move to GameController
    public String showTradeList() {
        String result = "Trade List: ";
        int index = 0;
        for (Trade trade : tradeList)
            result += "\n" + (++index) + ") " + trade.toStringTradeList();
        return result;
    }

    // TODO: move to GameController
    public String showRequestList() {
        String result = "Requst List: ";
        int index = 0;
        for (Trade trade : requestList)
            result += "\n" + (++index) + ") " + trade.toStringRequestList();
        return result;
    }

    // TODO: move to GameController
    public String showTradeHistory() {
        String result = "Trade History: ";
        int index = 0;
        for (Trade trade : tradeHistory)
            result += "\n" + (++index) + ") " + trade.toString();
        return result;
    }


    // TODO: move to GameController
    public void buyItem(Asset asset, int count) {
        gold -= count * asset.getBuyPrice();
        addAssetToStorage(asset, count);
    }

    // TODO: move to GameController
    public void sellItem(Asset asset, int count) {
        gold += count * asset.getSellPrice();
        removeAssetFromStorage(asset, count);
    }

    // TODO: move to GameController
    public String showPriceList() {
        String result = "Items:";
        for (AssetType assetType : assets.keySet()) {
            result += "\n\t" + assetType.getName() + "s:";
            for (Asset asset : assets.get(assetType).keySet())
                result += "\n\t\t- " + asset.toString() + "{" + assets.get(assetType).get(asset) + "}";
        }
        return result;
    }


    // TODO: move to GameController
    public String showPopularityFactors() {
        return "Food rate: " + popularityFactors.getFoodRate()
            + "\nKinds of foods: " + getKindsOfFoods()
            + "\nTax rate: " + popularityFactors.getTaxRate()
            + "\nReligious Factor: " + popularityFactors.getReligiousFactor()
            + "\nFear rate: " + popularityFactors.getFearRate()
            + "\nFear buildings" + popularityFactors.getFearBuildings()
            + "\nAle coverage: " + popularityFactors.getAleCoverage();
    }

    // TODO: move to GameController
    public int showPopularity() {
        return popularityFactors.getPopularity();
    }

    // TODO: move to GameController
    public String showFoodList() {
        return "Apple: " + assets.get(AssetType.FOOD).get(Asset.APPLE)
            + "\nMeat: " + assets.get(AssetType.FOOD).get(Asset.MEAT)
            + "\nCheese: " + assets.get(AssetType.FOOD).get(Asset.CHEESE)
            + "\nBread" + assets.get(AssetType.FOOD).get(Asset.BREAD);
    }

    // TODO: move to GameController
    public void setFearRate(int fearRate) {
        popularityFactors.setFearRate(fearRate);
    }

    // TODO: move to GameController
    public int getFearRate() {
        return popularityFactors.getFearRate();
    }



    // TODO: move to GameController
    public void addSpecificAsset(Asset asset, int count) {
        if (asset == null) return;
        int governanceCount = assets.get(asset.getAssetType()).get(asset);
        assets.get(asset.getAssetType()).put(asset, governanceCount + count);
    }

    // TODO: move to GameController
    public boolean canRemoveAssetFromStorage(Asset asset, int count) {
        if (asset == null) return true;
        int canRemove = 0;
        for (Building building : buildings) {
            if (!(building instanceof Storage)) continue;
            Storage storage = (Storage) building;
            if (!storage.isAssetCompatible(asset)) continue;
            if (storage.isPossibleRemoveProduct(asset, count)) return true;
            canRemove += storage.getAssetCount(asset);
            if (canRemove >= count) return true;
        }
        return false;
    }

    // TODO: move to GameController
    public boolean canAddAssetToStorage(Asset asset, int count) {
        if (asset == null) return true;
        for (Building building : buildings) {
            if (!(building instanceof Storage)) continue;
            Storage storage = (Storage) building;
            if (!storage.isAssetCompatible(asset)) continue;
            count -= storage.remainingCapacity();
            if (count <= 0) return true;
        }
        return false;
    }

    // TODO: move to GameController
    public void addAssetToStorage(Asset asset, int count) {
        if (asset == null) return;
        for (Building building : buildings) {
            if (!(building instanceof Storage)) continue;
            Storage storage = (Storage) building;
            if (!storage.isAssetCompatible(asset)) continue;
            int canAdd = count > storage.remainingCapacity() ? storage.remainingCapacity() : count;
            storage.addProduct(asset, canAdd);
            count -= canAdd;
            if (count == 0) return;
        }
        addSpecificAsset(asset, count);
    }

    // TODO: move to GameController
    public void removeAssetFromStorage(Asset asset, int count) {
        if (asset == null) return;
        for (Building building : buildings) {
            if (!(building instanceof Storage)) continue;
            Storage storage = (Storage) building;
            if (!storage.isAssetCompatible(asset)) continue;
            int canRemove = count > storage.getAssetCount(asset) ? storage.getAssetCount(asset) : count;
            storage.addProduct(asset, -canRemove);
            count -= canRemove;
            if (count == 0) return;
        }
        addSpecificAsset(asset, -count);
    }


    // TODO: move to GameController
    public int getKindsOfFoods() {
        int result = 0;
        for (Asset food : assets.get(AssetType.FOOD).keySet())
            if (!assets.get(AssetType.FOOD).get(food).equals(0)) result++;
        return result;
    }

    // TODO: move to GameController
    public int getFoodCount() {
        int result = 0;
        for (Asset food : assets.get(AssetType.FOOD).keySet())
            result += assets.get(AssetType.FOOD).get(food);
        return result;
    }

    // TODO: move to GameController
    public int getAssetCount(Asset asset) {
        if (asset == null)
            return 0;
        return assets.get(asset.getAssetType()).get(asset);
    }


    // TODO: move to GameController
    private void peopleEat() {
        // int haveToEat = nonMilitaryCharacters + workers.size();
        // for (Asset asset : assets.get(AssetType.FOOD).keySet()) {
        //     int count = assets.get(AssetType.FOOD).get(asset);
        //     if (haveToEat <= count) {
        //         removeAssetFromStorage(asset, haveToEat);
        //         return;
        //     }
        //     removeAssetFromStorage(asset, count);
        //     haveToEat -= count;
        // }
    }

    // TODO: move to GameController
    public void getTaxFromPeople(){
        // int population = this.nonMilitaryCharacters + this.workers.size();
        // double taxRate = popularityFactors.getTaxCoefficient();
        // this.addGold((int)(population * taxRate));
    }

    // TODO: move to GameController
    public void run() {
        getTaxFromPeople();
        // addPopulation();
        peopleEat();
    }


}
