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

import com.example.model.Game;
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
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

public class GameMap extends Pane implements WriteInFile, MapInterface, Successor {
    public static final double TILE_LENGTH = 10.0d;
    private final Timeline timeline;
    private final DoubleProperty scale;
    private final ArrayList<Tile> centers;
    private final int length;
    private final Game game;
    private final ArrayList<Tree> trees;
    private final ArrayList<Tile> selectedTiles;
    private BuildingType selectedBuilding;

    public GameMap(int length, Game game) {
        this.timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        this.scale = new SimpleDoubleProperty(1.0);
        this.centers = new ArrayList<>();
        this.length = length;
        this.game = game;
        this.trees = new ArrayList<>();
        this.selectedTiles = new ArrayList<>();
        setInitScales();
        addEventFilters();
        initTiles();
        selectedBuilding = null;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public Game getGame() {
        return game;
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }

    public ArrayList<Tile> getSelectedTiles() {
        return selectedTiles;
    }

    public BuildingType getSelectedBuilding() {
        return selectedBuilding;
    }

    public void setSelectedBuilding(BuildingType selectedBuilding) {
        this.selectedBuilding = selectedBuilding;
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


    protected double getScale() {
        return scale.get();
    }

    protected void setScale(double scale) {
        this.scale.set(scale);
    }




    private void setInitScales() {
        setMaxWidth(200);
        setMaxHeight(200);
        scaleXProperty().bind(scale);
        scaleYProperty().bind(scale);
        setScale(3.0);
    }

    private void initTiles() {
        URL url = getClass().getResource("/maps/defaultMap.bin");
        try {
            File file = new File(url.toURI());
            FileInputStream fileInputStream = new FileInputStream(file);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            for (int yIndex = 0; yIndex < length; yIndex++) {
                for (int xIndex = 0; xIndex < length; xIndex++) {
                    double x = (yIndex % 2) * TILE_LENGTH / 2 + TILE_LENGTH * xIndex;
                    double y = TILE_LENGTH * yIndex / 2;
                    byte byt = file.length() == 0 ? 0 : dataInputStream.readByte();
                    Tile tile = new Tile(x, y, byt, this);
                    centers.add(tile);
                    byt = file.length() == 0 ? 0 : dataInputStream.readByte();
                    Tree.addTree(this, byt, x, y);
                }
            }
            dataInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addEventFilters() {
        MapGestures mapGestures = new MapGestures(this);
        addEventFilter(MouseEvent.MOUSE_PRESSED, mapGestures.getOnMousePressedEventHandler());
        addEventFilter(MouseEvent.MOUSE_DRAGGED, mapGestures.getOnMouseDraggedEventHandler());
        addEventFilter(MouseEvent.MOUSE_DRAGGED, mapGestures.getOnMouseMovedEventHandler());
        addEventFilter(ScrollEvent.ANY, mapGestures.getOnScrollEventHandler());
    }

    public void addShortcuts() {
        this.getParent().setOnKeyPressed(key -> {
            switch (key.getCode()) {
                case G -> setTexture(TextureImages.GROUND);
                case F -> setTexture(TextureImages.FARM);
                case I -> setTexture(TextureImages.IRON);
                case S -> setTexture(TextureImages.SLAB);
                case W -> setTexture(TextureImages.WATER);
                case T -> addTree();
                default -> {}
            }
        });
    }

    private void setTexture(TextureImages textureImages) {
        for (Tile selectedTile : selectedTiles) {
            if (selectedTile.getTexture().getTextureImages().compareTo(textureImages) == 0) continue;
            selectedTile.setTexture(new Texture(textureImages));
            if (selectedTile.getTree() != null)
                selectedTile.getTree().removeFromMap();
        }
    }

    private void addTree() {
        for (Tile selectedTile : selectedTiles) {
            if (selectedTile.getTree() != null) continue;
            Tree.addTree(this, selectedTile);
        }
    }


    @Override
    public Tile getTileByIndex(int xIndex, int yIndex) {
        return centers.get(yIndex * length + xIndex);
    }

    @Override
    public void dropBuilding(double x, double y) {
        // if 1- have enough asset 2- can drop there
        // findClosestTile(x, y).setBuilding(building);
        Building.dropBuilding(selectedBuilding, game.getCurrentGovernance(), findClosestTile(x, y));
        int yIndex = getTileYIndex(y);
        int xIndex = getTileXIndex(x, yIndex);
        for (Tile tile : getSuccessors(this, xIndex, yIndex)) {
            tile.selectTile();
        }
    }

    @Override
    public void dropUnit(UnitType unitType) {
        
    }

    @Override
    public void move(Unit unit, Point2D start, Point2D dest) {

    }

    @Override
    public void writeInFile() {
        URL url = getClass().getResource("/maps/defaultMap.bin");
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
