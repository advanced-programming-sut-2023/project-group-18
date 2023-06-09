package com.example.model;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.model.Assets.Asset;
import com.example.model.Assets.AssetType;
import com.example.model.Buildings.*;
import com.example.model.Map.Cell;
import com.example.model.People.*;

public class Governance {
    private final User owner;
    private final ArrayList<Building> buildings;
    private final ArrayList<Soldier> soldiers;
    private final ArrayList<Worker> workers;
    private final HashMap<AssetType, HashMap<Asset, Integer>> assets;
    private final PopularityFactors popularityFactors;
    private int gold;
    private int nonMilitaryCharacters;
    private int remainingNonMilitary;
    private final ArrayList<Trade> tradeList;
    private final ArrayList<Trade> requestList;
    private final ArrayList<Trade> tradeHistory;
    private final ArrayList<Trade> tradeNotifications;
    private int soldiersCreatedInTurn;
    private Unit lord;

    public Governance(User owner) {
        this.owner = owner;
        buildings = new ArrayList<>();
        soldiers = new ArrayList<>();
        workers = new ArrayList<>();
        assets = Asset.getAllAssets();
        popularityFactors = new PopularityFactors(this);
        gold = 100;
        nonMilitaryCharacters = 10;
        this.remainingNonMilitary = 0;
        tradeList = new ArrayList<>();
        requestList = new ArrayList<>();
        tradeHistory = new ArrayList<>();
        tradeNotifications = new ArrayList<>();
        soldiersCreatedInTurn = 0;
    }

    public User getOwner() {
        return owner;
    }

    public PopularityFactors getPopularityFactors() {
        return popularityFactors;
    }

    public int getGold() {
        return gold;
    }

    public int getNonMilitaryCharacters() {
        return nonMilitaryCharacters;
    }

    public ArrayList<Soldier> getSoldiers() {
        return soldiers;
    }

    public HashMap<AssetType, HashMap<Asset, Integer>> getAssets() {
        return assets;
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

    public void addNonMilitaryCharacters(int nonMilitaryCharacters) {
        this.nonMilitaryCharacters += nonMilitaryCharacters;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }


    public boolean canAcceptTrade(int id) {
        Trade trade = Trade.getTradebyId(id, tradeList);
        if (trade == null) return false;
        return trade.getPrice() <= gold;
    }

    public void acceptTrade(int id, String meesage) {
        Trade.getTradebyId(id, tradeList).acceptTrade(meesage);
    }

    public void rejectTrade(int id, String message) {
        Trade.getTradebyId(id, tradeList).rejectTrade(message);
    }

    public void cancelTrade(int id) {
        Trade.getTradebyId(id, requestList).cancelTrade();
    }

    public void requestTrade(Governance accepter, Asset resourceType, int resourceAmount, int price, String message) {
        new Trade(this, accepter, resourceType, resourceAmount, price, message);
    }

    public String showNotifications() {
        String result = "Trade Notifications: ";
        int index = 0;
        for (Trade trade : tradeNotifications)
            result += "\n" + (++index) + ") " + trade.toStringTradeList();
        tradeNotifications.clear();
        return result;
    }

    public String showTradeList() {
        String result = "Trade List: ";
        int index = 0;
        for (Trade trade : tradeList)
            result += "\n" + (++index) + ") " + trade.toStringTradeList();
        return result;
    }

    public String showRequestList() {
        String result = "Requst List: ";
        int index = 0;
        for (Trade trade : requestList)
            result += "\n" + (++index) + ") " + trade.toStringRequestList();
        return result;
    }

    public String showTradeHistory() {
        String result = "Trade History: ";
        int index = 0;
        for (Trade trade : tradeHistory)
            result += "\n" + (++index) + ") " + trade.toString();
        return result;
    }


    public void buyItem(Asset asset, int count) {
        gold -= count * asset.getBuyPrice();
        addAssetToStorage(asset, count);
    }

    public void sellItem(Asset asset, int count) {
        gold += count * asset.getSellPrice();
        removeAssetFromStorage(asset, count);
    }

    public String showPriceList() {
        String result = "Items:";
        for (AssetType assetType : assets.keySet()) {
            result += "\n\t" + assetType.getName() + "s:";
            for (Asset asset : assets.get(assetType).keySet())
                result += "\n\t\t- " + asset.toString() + "{" + assets.get(assetType).get(asset) + "}";
        }
        return result;
    }


    public String showPopularityFactors() {
        return "Food rate: " + popularityFactors.getFoodRate()
            + "\nKinds of foods: " + getKindsOfFoods()
            + "\nTax rate: " + popularityFactors.getTaxRate()
            + "\nReligious Factor: " + popularityFactors.getReligiousFactor()
            + "\nFear rate: " + popularityFactors.getFearRate()
            + "\nFear buildings" + popularityFactors.getFearBuildings()
            + "\nAle coverage: " + popularityFactors.getAleCoverage();
    }

    public int showPopularity() {
        return popularityFactors.getPopularity();
    }

    public String showFoodList() {
        return "Apple: " + assets.get(AssetType.FOOD).get(Asset.APPLE)
            + "\nMeat: " + assets.get(AssetType.FOOD).get(Asset.MEAT)
            + "\nCheese: " + assets.get(AssetType.FOOD).get(Asset.CHEESE)
            + "\nBread" + assets.get(AssetType.FOOD).get(Asset.BREAD);
    }

    public void setFearRate(int fearRate) {
        popularityFactors.setFearRate(fearRate);
    }

    public int getFearRate() {
        return popularityFactors.getFearRate();
    }



    public void addSpecificAsset(Asset asset, int count) {
        if (asset == null) return;
        int governanceCount = assets.get(asset.getAssetType()).get(asset);
        assets.get(asset.getAssetType()).put(asset, governanceCount + count);
    }

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

    public boolean addBuilding(BuildingType buildingType, Cell cell) {
        switch (buildingType.getCategory()){
            case WALL -> buildings.add(new Wall(buildingType, this, cell));
            case STAIR -> buildings.add(new Stair(buildingType, this, cell, Direction.DOWN));
            case FARM -> buildings.add(new Farm(buildingType, this, cell));
            case TRAP -> buildings.add(new Trap(buildingType, this, cell));
            case GATE -> buildings.add(new Gate(buildingType, this, cell, Direction.DOWN));
            case TOWER -> buildings.add(new Tower(buildingType, this, cell));
            case STABLE -> buildings.add(new Stable(buildingType, this, cell));
            case STORAGE -> buildings.add(new Storage(buildingType, this, cell));
            case BARRACKS -> buildings.add(new Barracks(buildingType, this, cell));
            case GUNSMITH -> buildings.add(new Gunsmith(buildingType, this, cell));
            case PROCESSING -> buildings.add(new Processing(buildingType, this, cell));
            case DAIRY_PRODUCTS -> buildings.add(new DairyProducts(buildingType, this, cell));
            case INDUSTRIAL_BUILDING -> buildings.add(new IndustrialBuilding(buildingType, this, cell));
            case BUILDING -> buildings.add(new Building(buildingType, this, cell));
            default -> {
                return false;
            }
        }
        return true;
    }



    public void removeBuilding(Building building) {
        buildings.remove(building);
    }

    public void addSoldier(Cell cell, SoldierType soldierType) {
        soldiers.add(new Soldier(cell, this, soldierType));
    }

    public int getKindsOfFoods() {
        int result = 0;
        for (Asset food : assets.get(AssetType.FOOD).keySet())
            if (!assets.get(AssetType.FOOD).get(food).equals(0)) result++;
        return result;
    }

    public int getFoodCount() {
        int result = 0;
        for (Asset food : assets.get(AssetType.FOOD).keySet())
            result += assets.get(AssetType.FOOD).get(food);
        return result;
    }

    public int getAssetCount(Asset asset) {
        if (asset == null)
            return 0;
        return assets.get(asset.getAssetType()).get(asset);
    }

    public int getRemainingNonMilitary(){
        return remainingNonMilitary;
    }

    public void updateRemainingNonMilitary(){
        this.remainingNonMilitary = this.nonMilitaryCharacters;
    }

    public void removeRemainingCharacter(){
        this.remainingNonMilitary --;
    }

    public int getSoldiersCreatedInTurn() {
        return soldiersCreatedInTurn;
    }

    public void createSoldier(int count){
        this.soldiersCreatedInTurn += count;
    }


    private void addPopulation() {
        nonMilitaryCharacters += (getFoodCount() - workers.size()) / 2;
    }

    private void peopleEat() {
        int haveToEat = nonMilitaryCharacters + workers.size();
        for (Asset asset : assets.get(AssetType.FOOD).keySet()) {
            int count = assets.get(AssetType.FOOD).get(asset);
            if (haveToEat <= count) {
                removeAssetFromStorage(asset, haveToEat);
                return;
            }
            removeAssetFromStorage(asset, count);
            haveToEat -= count;
        }
    }

    public Unit getLord() {
        return lord;
    }

    protected void setLord() {
        this.lord = new Unit(this, UnitType.LORD, buildings.get(0).getCell());
    }

    public void getTaxFromPeople(){
        int population = this.nonMilitaryCharacters + this.workers.size();
        double taxRate = popularityFactors.getTaxCoefficient();
        this.addGold((int)(population * taxRate));
    }

    public void run() {
        getTaxFromPeople();
        addPopulation();
        peopleEat();
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }
}
