package com.example.model.buildings;

import java.util.ArrayList;

import com.example.model.Game;
import com.example.model.Governance;
import com.example.model.assets.Asset;
import com.example.model.map.Successor;
import com.example.model.map.Tile;
import com.example.model.people.Unit;
import com.example.model.people.UnitType;
import com.example.model.people.Worker;
import com.example.view.images.TextureImages;

public class Building implements Successor {
    private final BuildingType buildingType;
    protected final ArrayList<Worker> workers;
    protected final Governance governance;
    protected final TextureImages groundType;
    protected final int goldCost;
    protected final Asset resourceType;
    protected final int resourceCost;
    protected final ArrayList<Tile> tiles;
    protected int hitpoint;
    protected final int width;

    protected Building(BuildingType buildingType, Governance governance, Tile tile) {
        this.buildingType = buildingType;
        this.workers = new ArrayList<>();
        this.governance = governance;
        this.hitpoint = buildingType.getHitpoint();
        this.groundType = buildingType.getGroundType();
        this.goldCost = buildingType.getGoldCost();
        this.resourceType = buildingType.getResourceType();
        this.resourceCost = buildingType.getResourceCost();
        this.width = buildingType.getWidth();
        this.tiles = new ArrayList<>();
        addBuildingToTiles(tile);
        // TODO: need to create town category
        if (buildingType.equals(BuildingType.CATHEDRAL) || buildingType.equals(BuildingType.CHURCH))
            governance.getPopularityFactors().addReligiousFactor(buildingType.getPopularityEffect());
    }

    public static void dropBuilding(BuildingType buildingType, Governance governance, Tile tile) {
        switch (buildingType.getCategory()) {
            case BARRACKS -> new Barracks(buildingType, governance, tile);
            case DAIRY_PRODUCTS -> new Building(buildingType, governance, tile);
            case FARM -> new Farm(buildingType, governance, tile);
            case GATE -> new Gate(buildingType, governance, tile);
            case GUNSMITH -> new Gunsmith(buildingType, governance, tile);
            case INDUSTRIAL_BUILDING -> new IndustrialBuilding(buildingType, governance, tile);
            case PROCESSING -> new Processing(buildingType, governance, tile);
            case STABLE -> new Stable(buildingType, governance, tile);
            case STAIR -> new Stair(buildingType, governance, tile);
            case STORAGE -> new Storage(buildingType, governance, tile);
            case TOWER -> new Tower(buildingType, governance, tile);
            case TRAP -> new Trap(buildingType, governance, tile);
            case WALL -> new Wall(buildingType, governance, tile);
            default -> new Building(buildingType, governance, tile);
        }
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

    public TextureImages getGroundType() {
        return groundType;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
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
        // workers.add(new Worker(governance,UnitType.getUnitTypeByBuildingType(buildingType),tile));
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
            removeBuildingFromTiles();
        else this.hitpoint -= damage;
    }

    private void addBuildingToTiles(Tile centerTile) {
        tiles.add(centerTile);
        if (width > 1) tiles.addAll(getSuccessors(Game.getInstance().getGameMap(), centerTile));
        for (Tile tile : tiles)
            tile.setBuilding(this);
        governance.getBuildings().add(this);
    }

    private void removeBuildingFromTiles() {
        for (Tile tile : tiles)
            tile.setBuilding(null);
        governance.getBuildings().remove(this);
    }

    public boolean isReachable() {
        return true;
    }

    // @Override
    // public String toString() {
    //     String hitpoint;
    //     if (this.hitpoint < buildingType.getHitpoint() / 3) hitpoint = RED_BOLD;
    //     else if (this.hitpoint < buildingType.getHitpoint() * 2 / 3) hitpoint = YELLOW_BOLD;
    //     else hitpoint = GREEN_BOLD;
    //     hitpoint += this.hitpoint + RESET;
    //     hitpoint += "/" + buildingType.getHitpoint();
    //     final String owner = YELLOW_BOLD + governance.getOwner().getNickname() + RESET;
    //     return buildingType.getName() + " [" + hitpoint + "] \"" + owner + "\"";
    // }

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
