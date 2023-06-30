package com.example.model.buildings;

import java.util.ArrayList;

import com.example.model.Game;
import com.example.model.Governance;
import com.example.model.assets.Asset;
import com.example.model.map.Successor;
import com.example.model.map.Tile;
import com.example.model.people.Unit;
import com.example.model.people.Worker;
import com.example.view.Main;
import com.example.view.images.TextureImages;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;

public class Building implements Successor {
    private final BuildingType buildingType;
    private final ImageView imageView;
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
        this.imageView = new ImageView(buildingType.getImage());
        setImageViewOptions(tile);
        addBuildingToTiles(tile);
        // TODO: need to create town category
        if (buildingType.equals(BuildingType.CATHEDRAL) || buildingType.equals(BuildingType.CHURCH))
            governance.getPopularityFactors().addReligiousFactor(buildingType.getPopularityEffect());
    }

    public static void dropBuilding(BuildingType buildingType, Governance governance, Tile tile) {
        switch (buildingType.getCategory()) {
            case BARRACKS -> new Barracks(buildingType, governance, tile).setTooltip();
            case DAIRY_PRODUCTS -> new Building(buildingType, governance, tile).setTooltip();
            case FARM -> new Farm(buildingType, governance, tile).setTooltip();
            case GATE -> new Gate(buildingType, governance, tile).setTooltip();
            case GUNSMITH -> new Gunsmith(buildingType, governance, tile).setTooltip();
            case INDUSTRIAL_BUILDING -> new IndustrialBuilding(buildingType, governance, tile).setTooltip();
            case PROCESSING -> new Processing(buildingType, governance, tile).setTooltip();
            case STABLE -> new Stable(buildingType, governance, tile).setTooltip();
            case STAIR -> new Stair(buildingType, governance, tile).setTooltip();
            case STORAGE -> new Storage(buildingType, governance, tile).setTooltip();
            case TOWER -> new Tower(buildingType, governance, tile).setTooltip();
            case TRAP -> new Trap(buildingType, governance, tile).setTooltip();
            case WALL -> new Wall(buildingType, governance, tile).setTooltip();
            default -> new Building(buildingType, governance, tile).setTooltip();
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
        centerTile.getGameMap().getChildren().add(imageView);
    }

    public void removeBuildingFromTiles() {
        for (Tile tile : tiles)
            tile.setBuilding(null);
        governance.getBuildings().remove(this);
        tiles.get(0).getGameMap().getChildren().remove(imageView);
    }

    private void setImageViewOptions(Tile tile) {
        imageView.setFitHeight(buildingType.getImageHeight());
        imageView.setFitWidth(buildingType.getImageWidth());
        imageView.setLayoutX(tile.getPoint2d().getX() - buildingType.getResetX());
        imageView.setLayoutY(tile.getPoint2d().getY() - buildingType.getResetY());
    }

    public void setTooltip() {
        Popup popup = new Popup();
        Label label = new Label();
        popup.getContent().add(label);
        label.getStylesheets().add(Main.class.getResource("/css/Popup.css").toExternalForm());
        label.setPadding(new Insets(20, 10, 20, 10));

        imageView.setOnMouseEntered(event -> {
            label.setText(toString());
            popup.show(Main.getStage(), event.getSceneX() + 20, event.getSceneY());
        });

        imageView.setOnMouseExited(event -> {
            popup.hide();
        });

        imageView.setOnMouseClicked(event -> {
            Game.getInstance().selectBuilding(this);
        });


    }

    public boolean isReachable() {
        return true;
    }

    @Override
    public String toString() {
        String hitpoint = "Building hitpoint: ";
        hitpoint += this.hitpoint;
        hitpoint += "/" + buildingType.getHitpoint();
        final String owner = governance.getOwner().getUsername();
        String out = buildingType.getName() + " [" + hitpoint + "] \"" + owner + "\"";
        if (buildingType.getCategory().equals(Category.GATE)) {
            Gate gate = (Gate) this;
            if (gate.isOpen())
                out = out + "\ncurrent state: open";
            else out = out + "\ncurrent state: close";
        }
        return out;
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
