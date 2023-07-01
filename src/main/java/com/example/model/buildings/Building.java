package com.example.model.buildings;

import java.util.ArrayList;

import com.example.model.Game;
import com.example.model.Governance;
import com.example.model.map.Successor;
import com.example.model.map.Tile;
import com.example.view.Main;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;

public class Building implements Successor, Runnable {
    protected final BuildingType buildingType;
    protected final Governance governance;
    protected final ImageView imageView;
    protected final ArrayList<Tile> tiles;
    protected int hitpoint;
    protected int worker;
    protected int population;

    protected Building(BuildingType buildingType, Governance governance, Tile tile) {
        this.buildingType = buildingType;
        this.governance = governance;
        this.imageView = new ImageView(buildingType.getImage());
        this.tiles = new ArrayList<>();
        this.hitpoint = buildingType.getHitpoint();
    }

    // TODO: bring to GameController
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

    public ImageView getImageView() {
        return imageView;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public int getWorker() {
        return worker;
    }

    public int getPopulation() {
        return population;
    }

    public void setHitpoint(int hitpoint) {
        this.hitpoint = hitpoint;
    }

    public void setWorker(int worker) {
        this.worker = worker;
    }

    public void setPopulation(int population) {
        this.population = population;
    }


    // TODO: bring to GameController
    public boolean canWork() {
        return this.getWorker() == this.getBuildingType().getWorkersNumber();
    }

    // TODO: bring to GameController
    public void doDamage(int damage) {
        if (this.hitpoint <= damage)
            removeBuildingFromTiles();
        else this.hitpoint -= damage;
    }
    
    // TODO: bring to GameController
    private void addBuildingToTiles(Tile centerTile) {
        tiles.add(centerTile);
        if (buildingType.getWidth() > 1) tiles.addAll(getSuccessors(Game.getInstance().getGameMap(), centerTile));
        for (Tile tile : tiles)
            tile.setBuilding(this);
        governance.getBuildings().add(this);
        centerTile.getGameMap().getChildren().add(imageView);
    }
    
    // TODO: bring to GameController
    public void removeBuildingFromTiles() {
        for (Tile tile : tiles)
            tile.setBuilding(null);
        governance.getBuildings().remove(this);
        tiles.get(0).getGameMap().getChildren().remove(imageView);
    }

    // TODO: bring to GameController
    private void setImageViewOptions(Tile tile) {
        imageView.setFitHeight(buildingType.getImageHeight());
        imageView.setFitWidth(buildingType.getImageWidth());
        imageView.setLayoutX(tile.getPoint2d().getX() - buildingType.getResetX());
        imageView.setLayoutY(tile.getPoint2d().getY() - buildingType.getResetY());
    }

    // TODO: bring to GameController
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
            Game.getInstance().selectUnit(null);
        });


    }

    @Override
    public String toString() {
        final String hitpoint = "\nHitpoint: " + this.hitpoint + "/" + buildingType.getHitpoint();
        final String owner = "\nOwner: " + governance.getOwner().getUsername();
        String result = "\n";
        if (buildingType.getPopulationEffect() > 0) result += "Population: " + population + "/" + buildingType.getPopulationEffect();
        if (buildingType.getWorkersNumber() > 0) result += "Workers: " + worker + "/" + buildingType.getWorkersNumber();
        return buildingType.getName() + hitpoint + owner + result;
    }

    @Override
    public void run() {
        //getMaterial
        if (!this.canWork())
            return;
    }

}
