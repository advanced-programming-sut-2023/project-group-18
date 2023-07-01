package com.example.model.map;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

import com.example.model.Game;
import com.example.model.Governance;
import com.example.model.WriteInFile;
import com.example.model.buildings.Building;
import com.example.model.buildings.BuildingType;
import com.example.model.people.Unit;
import com.example.model.people.UnitType;
import com.example.view.images.TextureImages;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

public class GameMap extends Pane implements WriteInFile, MapInterface, Successor {
    private final String name;
    private final DoubleProperty scale;
    private final ArrayList<Tile> centers;
    private final int length;
    private final ArrayList<Tree> trees;
    private final ArrayList<Tile> selectedTiles;
    private final LinkedList<Tile> path;
    private BuildingType selectedBuildingType;
    private boolean customizeMove;

    public GameMap(String name, int length) {
        this.name = name;
        this.scale = new SimpleDoubleProperty(1.0);
        this.centers = new ArrayList<>();
        this.length = length;
        this.trees = new ArrayList<>();
        this.selectedTiles = new ArrayList<>();
        this.path = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public double getScale() {
        return scale.get();
    }

    public ArrayList<Tile> getCenters() {
        return centers;
    }

    public int getLength() {
        return length;
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }

    public ArrayList<Tile> getSelectedTiles() {
        return selectedTiles;
    }

    public LinkedList<Tile> getPath() {
        return path;
    }

    public BuildingType getSelectedBuildingType() {
        return selectedBuildingType;
    }

    public void setSelectedBuildingType(BuildingType selectedBuilding) {
        this.selectedBuildingType = selectedBuilding;
    }

    public void setSelectedTiles(double startX, double startY, double endX, double endY) {
        for (Tile selectedTile : selectedTiles)
            selectedTile.deselectTile();
        selectedTiles.clear();
        int startYIndex = getTileYIndex(startY);
        int startXIndex = getTileXIndex(startX, startYIndex);
        int endYIndex = getTileYIndex(endY);
        int endXIndex = getTileXIndex(endX, endYIndex);
        for (int yIndex = startYIndex; yIndex <= endYIndex; yIndex++) {
            for (int xIndex = startXIndex; xIndex <= endXIndex; xIndex++) {
                Tile selectedTile = getTileByIndex(xIndex, yIndex);
                selectedTile.selectTile();
                selectedTiles.add(selectedTile);
            }
        }
    }

    public void setScale(double scale) {
        this.scale.set(scale);
    }

    public Tile findClosestTile(double x, double y) {
        int yIndex = getTileYIndex(y);
        int xIndex = getTileXIndex(x, yIndex);
        return getTileByIndex(xIndex, yIndex);
    }

    public int getTileYIndex(double y) {
        return (int) Math.round(y * 2 / TILE_LENGTH);
    }

    public int getTileXIndex(double x, int yIndex) {
        return (int) Math.round((x - (yIndex % 2) * TILE_LENGTH / 2) / TILE_LENGTH);
    }


    protected void addToPath(double x, double y) {
        Tile tile = findClosestTile(x, y);
        if (path.contains(tile)) return;
        path.addLast(tile);
        tile.selectTile();
    }


    public void attackUnit() {
        if (game.getSelectedUnit() != null) 
            game.getSelectedUnit().attack();
    }

    public void deselectUnits() {
        game.selectUnit(null);
    }

    public void pastFromClipboard() {
        String name = (String) Clipboard.getSystemClipboard().getContent(DataFormat.PLAIN_TEXT);
        setSelectedBuildingType(BuildingType.getBuildingTypeByName(name));
        dropBuilding(selectedTiles.get(0).getPoint2d().getX(), selectedTiles.get(0).getPoint2d().getY());
        setSelectedBuildingType(null);
    }

    public void copyToClipboard() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(game.getSelectedBuildingType().getBuildingType().getName());
        clipboard.setContent(content);
    }

    public void removeSelectedBuilding() {
        game.getSelectedBuildingType().removeBuildingFromTiles();
    }

    public void customizeMove() {
        if (game.getSelectedUnit() == null) return;
        customizeMove = !customizeMove;
        for (Tile selectedTile : selectedTiles)
            selectedTile.deselectTile();
        selectedTiles.clear();
        if (!path.isEmpty()) game.getSelectedUnit().move(path);
        for (Tile tile : path)
            tile.deselectTile();
    }

    public void removeLastBuilding() {
        int index = game.getCurrentGovernance().getBuildings().size() - 1;
        game.getCurrentGovernance().getBuildings().get(index).removeBuildingFromTiles();
    }

    public void backToMap() {
        setTranslateX(-MapGestures.RESET_X);
        setTranslateY(-MapGestures.RESET_Y);
    }

    public void setTexture(TextureImages textureImages) {
        for (Tile selectedTile : selectedTiles) {
            if (selectedTile.getTexture().getTextureImages().compareTo(textureImages) == 0) continue;
            selectedTile.setTexture(new Texture(textureImages));
            if (selectedTile.getTree() != null)
                selectedTile.getTree().removeFromMap();
        }
    }

    public void addTree() {
        for (Tile selectedTile : selectedTiles) {
            if (selectedTile.getTree() != null) continue;
            Tree.addTree(this, selectedTile);
        }
    }


    public void setKeep(double x, double y, Governance governance) {
        Building.dropBuilding(BuildingType.KEEP, governance, findClosestTile(x, y));
    }


    @Override
    public Tile getTileByIndex(int xIndex, int yIndex) {
        return centers.get(yIndex * length + xIndex);
    }

    @Override
    public void dropBuilding(double x, double y) {
        // if 1- have enough asset 2- can drop there
        Building.dropBuilding(selectedBuildingType, game.getCurrentGovernance(), findClosestTile(x, y));
        // int yIndex = getTileYIndex(y);
        // int xIndex = getTileXIndex(x, yIndex);
        // for (Tile tile : getSuccessors(this, xIndex, yIndex))
        //     tile.selectTile();
    }

    @Override
    public void dropUnit(UnitType unitType) {
        
    }

    @Override
    public void move(Unit unit, double x, double y) {
        unit.move(findClosestTile(x, y));
    }

    @Override
    public void writeInFile() {
        URL url = getClass().getResource("/maps/" + name + ".bin");
        try {
            DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(new File(url.toURI())));
            for (int yIndex = 0; yIndex < length; yIndex++)
                for (int xIndex = 0; xIndex < length; xIndex++)
                    outputStream.write(getTileByIndex(xIndex, yIndex).getSymbol());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
