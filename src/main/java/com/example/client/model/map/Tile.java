package com.example.client.model.map;

import com.example.client.model.Governance;
import com.example.client.model.buildings.Building;
import com.example.client.model.people.Unit;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Tile {
    private final Point2D point2d;
    private final ArrayList<Unit> units;
    private final GameMap gameMap;
    private final ImageView imageView;
    private final ArrayList<Line> lines;
    private Texture texture;
    private Building building;
    private Tree tree;

    protected Tile(double x, double y, int symbol, GameMap gameMap) {
        this.point2d = new Point2D(x, y);
        this.texture = new Texture(symbol);
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
        imageView.setLayoutX(x - GameMap.TILE_LENGTH / 2);
        imageView.setLayoutY(y - GameMap.TILE_LENGTH / 2);
        return imageView;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Point2D getPoint2d() {
        return point2d;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public void selectTile() {
        for (Line line : lines)
            if (!gameMap.getChildren().contains(line))
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
        imageView.setImage(texture.getImage());
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public byte[] getSymbol() {
        byte[] result = new byte[2];
        result[0] = texture.getTextureImages().getSymbol();
        if (tree != null) result[1] = tree.getTreeType().getSymbol();
        else result[1] = 0;
        return result;
    }

    // TODO
    public String showDetails() {
        return null;
    }

    public boolean hasEnemy(Governance governance) {
        for (Unit unit : units)
            if (unit.getGovernance().equals(governance)) return true;
        return (building != null) && (building.getGovernance() != null && !building.getGovernance().equals(governance));
    }

}
