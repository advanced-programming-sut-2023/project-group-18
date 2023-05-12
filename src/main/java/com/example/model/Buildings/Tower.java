package com.example.model.Buildings;

import com.example.model.Assets.Asset;
import com.example.model.Governance;
import com.example.model.Map.Cell;
import com.example.model.People.Soldier;

import java.util.ArrayList;

public class Tower extends Building{
    private final int fireRange;
    private final int defendRange;
    private final int soldiersCapacity;
    private boolean hasLadder;
    private final ArrayList<Soldier> soldiers;
    private final boolean strong;

    public Tower(BuildingType buildingType, Governance governance, Cell cell) {
        super(buildingType, governance, cell);
        this.fireRange = buildingType.getFireRange();
        this.defendRange = buildingType.getDefendRange();
        hasLadder = false;
        soldiersCapacity = buildingType.getSoldiersCapacity();
        soldiers = new ArrayList<>();
        if (buildingType.equals(BuildingType.CIRCLE_TOWER) || buildingType.equals(BuildingType.SQUARE_TOWER))
            strong = true;
        else strong = false;
    }

    public int getFireRange() {
        return fireRange;
    }

    public int getDefendRange() {
        return defendRange;
    }

    public int getSoldiersCapacity() {
        return soldiersCapacity;
    }

    public boolean isHasLadder() {
        return hasLadder;
    }

    public void setLadder() {
        this.hasLadder = true;
    }

    public void removeLadder(){
        this.hasLadder = false;
    }

    public ArrayList<Soldier> getSoldiers() {
        return soldiers;
    }

    public int soldiersCount(){
        return this.soldiers.size();
    }
    public void addSoldier(Soldier soldier){
        this.soldiers.add(soldier);
    }
    public void removeSoldier(Soldier soldier){
        this.soldiers.remove(soldier);
    }

    public boolean isStrong(){
        return strong;
    }
    public boolean canRepair(){
        Asset asset = Asset.STONE;
        int stoneNeeded = this.getBuildingType().getHitpoint() - this.hitpoint;
        return governance.canRemoveAssetFromStorage(asset,stoneNeeded);
    }
    public void repair(){
        Asset asset = Asset.STONE;
        int stoneNeeded = this.getBuildingType().getHitpoint() - this.hitpoint;
        if (governance.canRemoveAssetFromStorage(asset,stoneNeeded)){
            governance.removeAssetFromStorage(asset,stoneNeeded);
            this.hitpoint = this.getBuildingType().getHitpoint();
        }
        else {
            this.hitpoint += governance.getAssetCount(asset);
            governance.removeAssetFromStorage(asset, governance.getAssetCount(asset));
        }
    }
}
