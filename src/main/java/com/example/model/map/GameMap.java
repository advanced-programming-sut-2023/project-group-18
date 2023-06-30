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
    public static final double TILE_LENGTH = 10.0d;
    private final Timeline timeline;
    private final DoubleProperty scale;
    private final ArrayList<Tile> centers;
    private final int length;
    private final Game game;
    private final ArrayList<Tree> trees;
    private final ArrayList<Tile> selectedTiles;
    private final LinkedList<Tile> path;
    private BuildingType selectedBuilding;
    protected boolean conditionalMove;

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
        this.path = new LinkedList<>();
        setInitScales();
        addEventFilters();
        initTiles();
        selectedBuilding = null;
        conditionalMove = false;
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
        if (game.getSelectedUnit() != null && !selectedTiles.isEmpty())
            game.getSelectedUnit().move(selectedTiles.get(0));
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

    protected void addToPath(double x, double y) {
        Tile tile = findClosestTile(x, y);
        if (path.contains(tile)) return;
        path.addLast(tile);
        tile.selectTile();
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
        addEventFilter(MouseEvent.MOUSE_DRAGGED, mapGestures.getOnMouseConditionalMoveEventHandler());
        addEventFilter(ScrollEvent.ANY, mapGestures.getOnScrollEventHandler());
    }

    public void addShortcuts() {
        this.getScene().setOnKeyPressed(key -> {
            switch (key.getCode()) {
                case G -> setTexture(TextureImages.GROUND);
                case F -> setTexture(TextureImages.FARM);
                case I -> setTexture(TextureImages.IRON);
                case S -> setTexture(TextureImages.SLAB);
                case W -> setTexture(TextureImages.WATER);
                case T -> addTree();
                case B -> backToMap();
                case Z -> removeLastBuilding();
                case M -> conditionalMove();
                case D -> removeSelectedBuilding();
                case C -> copyToClipboard();
                case V -> pastFromClipboard();
                default -> {}
            }
        });
    }

    private void pastFromClipboard() {
        String name = (String) Clipboard.getSystemClipboard().getContent(DataFormat.PLAIN_TEXT);
        setSelectedBuilding(BuildingType.getBuildingTypeByName(name));
        dropBuilding(selectedTiles.get(0).getPoint2d().getX(), selectedTiles.get(0).getPoint2d().getY());
        setSelectedBuilding(null);
    }

    private void copyToClipboard() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(game.getSelectedBuilding().getBuildingType().getName());
        clipboard.setContent(content);
    }

    private void removeSelectedBuilding() {
        game.getSelectedBuilding().removeBuildingFromTiles();
    }

    private void conditionalMove() {
        for (Tile selectedTile : selectedTiles)
            selectedTile.deselectTile();
        selectedTiles.clear();
        conditionalMove = !conditionalMove;
        if (!path.isEmpty()) game.getSelectedUnit().move(new LinkedList<>(path));
        // for (Tile tile : path)
        //     tile.deselectTile();
        // path.clear();
    }

    private void removeLastBuilding() {
        int index = game.getCurrentGovernance().getBuildings().size() - 1;
        game.getCurrentGovernance().getBuildings().get(index).removeBuildingFromTiles();
    }

    private void backToMap() {
        setTranslateX(-MapGestures.RESET_X);
        setTranslateY(-MapGestures.RESET_Y);
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
        Building.dropBuilding(selectedBuilding, game.getCurrentGovernance(), findClosestTile(x, y));
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
