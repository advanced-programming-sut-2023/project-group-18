package com.example.model.map;

import java.util.ArrayList;

import com.example.model.buildings.Building;
import com.example.model.people.Unit;
import com.example.view.images.TextureImages;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Tile {
    private final ArrayList<Unit> units;
    private final GameMap gameMap;
    private final ImageView imageView;
    private final ArrayList<Line> lines;
    private Texture texture;
    private Building building;

    protected Tile(double x, double y, TextureImages textureImages, GameMap gameMap) {
        this.texture = new Texture(textureImages);
        this.units = new ArrayList<>();
        this.gameMap = gameMap;
        this.imageView = makeImage(x, y);
        this.lines = makeLines(x, y);
    }

    private ArrayList<Line> makeLines(double x, double y) {
        ArrayList<Line> lines = new ArrayList<>();
        final double a = GameMap.TILE_LENGTH / 2;
        lines.add(makeASingleLine(x - a, y, x, y + a));
        lines.add(makeASingleLine(x, y + a, x + a, y));
        lines.add(makeASingleLine(x + a, y, x, y - a));
        lines.add(makeASingleLine(x, y - a, x - a, y));
        return lines;
    }

    private Line makeASingleLine(double startX, double startY, double endX, double endY) {
        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(Color.CYAN);
        line.setStrokeWidth(1.0);
        return line;
    }

    private ImageView makeImage(double x, double y) {
        ImageView imageView = new ImageView(texture.getImage());
        imageView.setFitWidth(GameMap.TILE_LENGTH);
        imageView.setFitHeight(GameMap.TILE_LENGTH);
        gameMap.getChildren().add(imageView);
        imageView.setLayoutX(x + GameMap.TILE_LENGTH / 2);
        imageView.setLayoutY(y + GameMap.TILE_LENGTH / 2);
        return imageView;
    }

    public void selectTile() {
        for (Line line : lines)
            gameMap.getChildren().add(line);
    }

    public void deselectTile() {
        for (Line line : lines)
            gameMap.getChildren().remove(line);
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public Texture getTexture() {
        return texture;
    }

    public Building getBuilding() {
        return building;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    // TODO
    public String showDetails() {
        return null;
    }

}
