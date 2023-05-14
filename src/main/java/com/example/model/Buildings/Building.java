package com.example.model.Buildings;

import java.util.ArrayList;

import com.example.model.ConsoleColors;
import com.example.model.Governance;
import com.example.model.Assets.Asset;
import com.example.model.Map.Cell;
import com.example.model.People.Unit;
import com.example.model.People.UnitType;
import com.example.model.People.Worker;
import com.example.model.Map.Texture;

public class Building implements ConsoleColors {
    private final BuildingType buildingType;
    protected final ArrayList<Worker> workers;
    protected final Governance governance;
    protected final Texture groundType;
    protected final int goldCost;
    protected final Asset resourceType;
    protected final int resourceCost;
    protected final Cell cell;
    protected int hitpoint;
    protected final int width;

    public Building(BuildingType buildingType, Governance governance, Cell cell) {
        this.buildingType = buildingType;
        this.workers = new ArrayList<>();
        this.governance = governance;
        this.hitpoint = buildingType.getHitpoint();
        this.groundType = buildingType.getGroundType();
        this.goldCost = buildingType.getGoldCost();
        this.resourceType = buildingType.getResourceType();
        this.resourceCost = buildingType.getResourceCost();
        this.cell = cell;
        this.width = buildingType.getWidth();
        setCellBuilding();
        if (buildingType.equals(BuildingType.CATHEDRAL) || buildingType.equals(BuildingType.CHURCH))
            governance.getPopularityFactors().addReligiousFactor(buildingType.getPopularityEffect());
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }


    public Governance getGovernance() {
        return governance;
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public Texture getGroundType() {
        return groundType;
    }

    public Cell getCell() {
        return cell;
    }

    public int getGoldCost() {
        return goldCost;
    }

    public Asset getResourceType() {
        return resourceType;
    }

    public int getResourceCost() {
        return resourceCost;
    }

    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    public int getWorkersNumber(){
        return this.workers.size();
    }

    public void setHitpoint(int hitpoint){
        this.hitpoint = hitpoint;
    }

    public void addWorker(){
        workers.add(new Worker(governance,UnitType.getUnitTypeByBuildingType(buildingType),cell));
    }

    public void updateWorkers(){
        for (Unit unit : this.workers){
            if (unit.getHitpoint() == 0)
                workers.remove(unit);
        }
    }

    public boolean canWork(){
        return this.getWorkersNumber() == this.getBuildingType().getWorkersNumber();
    }

    public void doDamage(int damage){
        if (this.hitpoint <= damage)
            killBuilding();
        else this.hitpoint -= damage;
    }

    private void setCellBuilding() {
        for (int i = -width + 1; i < width; i++)
            for (int j = -width + 1; j < width; j++) {
                int xCoordinate = cell.getxCoordinate() + i;
                int yCoordinate = cell.getyCoordinate() + j;
                Cell underCell = cell.getGameMap().getCellByLocation(xCoordinate, yCoordinate);
                underCell.setBuilding(this);
            }
    }

    private void killBuilding() {
        for (int i = -width + 1; i < width; i++)
            for (int j = -width + 1; j < width; j++) {
                int xCoordinate = cell.getxCoordinate() + i;
                int yCoordinate = cell.getyCoordinate() + j;
                Cell underCell = cell.getGameMap().getCellByLocation(xCoordinate, yCoordinate);
                underCell.setBuilding(null);
            }
        governance.removeBuilding(this);
    }

    public boolean isReachable() {
        return true;
    }

    @Override
    public String toString() {
        String hitpoint;
        if (this.hitpoint < buildingType.getHitpoint() / 3) hitpoint = RED_BOLD;
        else if (this.hitpoint < buildingType.getHitpoint() * 2 / 3) hitpoint = YELLOW_BOLD;
        else hitpoint = GREEN_BOLD;
        hitpoint += this.hitpoint + RESET;
        hitpoint += "/" + buildingType.getHitpoint();
        final String owner = YELLOW_BOLD + governance.getOwner().getNickname() + RESET;
        return buildingType.getName() + " [" + hitpoint + "] \"" + owner + "\"";
    }

    public void run(){
        //getMaterial
        if (!this.canWork())
            return;
        for (Worker worker : workers){
            if (worker.isFree()){
                if (worker.getMaterial() != null){
                    worker.setFree(false);
                    if (governance.canRemoveAssetFromStorage(worker.getMaterial(),1)){

                    }
                }
                else if (worker.getProduct() != null){
                    worker.setFree(false);
                    if (governance.canAddAssetToStorage(worker.getProduct(),worker.getProductCount())){

                    }
                }
            }
        }
    }

}
