package com.example.model.people;

import java.util.ArrayList;
import java.util.LinkedList;

import com.example.model.Game;
import com.example.model.Governance;
import com.example.model.map.Tile;
import com.example.view.images.TextureImages;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import com.example.model.map.Node;
import com.example.model.map.Successor;

public class Unit implements Successor, MoveInteface, AttackInterface {
    protected final Governance governance;
    private final UnitType unitType;
    private final ImageView imageView;
    private final double duration;
    protected Tile unitTile;
    protected Tile targetTile;
    protected LinkedList<Tile> path;
    private int hitpoint;
    private int index;
    private int imageIndex;

    public Unit(Governance governance, UnitType unitType, Tile unitTile) {
        this.governance = governance;
        this.unitType = unitType;
        this.unitTile = unitTile;
        this.duration = 200 / unitType.getSpeed();
        unitTile.getUnits().add(this);
        imageView = new ImageView(unitType.getType().getStanding());
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        Game.getInstance().getGameMap().getChildren().add(imageView);
        addToolTip();
    }

    private void addToolTip() {
        imageView.setOnMouseClicked(event -> {
            unitTile.getGameMap().getGame().selectUnit(this);
        });
    }

    public Governance getGovernance() {
        return governance;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public Tile getUnitTile() {
        return unitTile;
    }

    public int getHitpoint() {
        return hitpoint;
    }
    
    public Tile getTargetTile() {
        return targetTile;
    }

    public void setUnitTile(Tile unitTile) {
        this.unitTile = unitTile;
    }

    public void setTargetTile(Tile targetTile) {
        this.targetTile = targetTile;
    }

    public boolean addHitpoint(int hitpoint) {
        this.hitpoint += hitpoint;
        this.hitpoint = unitType.getMaxHitpoint() < this.hitpoint ? unitType.getMaxHitpoint() : hitpoint;
        return hitpoint > 0;
    }

    public boolean canGoTile(Tile tile) {
        return tile.getBuilding() == null || tile.getTexture().getTextureImages().equals(TextureImages.FARM)
            || tile.getTexture().getTextureImages().equals(TextureImages.GROUND);
    }


    protected void findPath() {
        Node node = findNode();
        path = new LinkedList<>();
        while (node != null) {
            path.addFirst(targetTile);
            node = node.getParent();
        }
    }

    private Node findNode() {
        final ArrayList<Node> openList = new ArrayList<>();
        final ArrayList<Node> closedList = new ArrayList<>();
        openList.add(new Node(null, unitTile, targetTile));
        while (!openList.isEmpty()) {
            System.out.println(openList.get(0).toString() + " size === " + openList.size()); // TODO: need to remove
            Node node = findLeastTotalNode(openList);
            int yIndex = node.getTile().getGameMap().getTileYIndex(node.getTile().getPoint2d().getX());
            int xIndex = node.getTile().getGameMap().getTileXIndex(node.getTile().getPoint2d().getY(), yIndex);
            l:
            for (int[] successor : SUCCESSORS[yIndex % 2]) {
                int xIndex2 = xIndex + successor[0];
                int yIndex2 = yIndex + successor[1];
                Tile tile = unitTile.getGameMap().getTileByIndex(xIndex2, yIndex2);
                if (tile == null) continue l;
                if (!canGoTile(tile)) continue l;
                Node neighbor = new Node(node, tile, targetTile);
                if (isDone(neighbor)) return neighbor;
                if (betterChoiseInList(neighbor, openList)) continue l;
                if (betterChoiseInList(neighbor, closedList)) continue l;
                openList.add(neighbor);
            }
            closedList.add(node);
        }
        return null;
    }

    private boolean isDone(Node neighbor) {
        return neighbor.getTile().equals(targetTile);
    }

    private Node findLeastTotalNode(ArrayList<Node> nodeList) {
        Node bestNode = null;
        double leastTotal = 100000000000000.0d;
        for (Node node : nodeList)
            if (node.getTotal() < leastTotal) {
                leastTotal = node.getTotal();
                bestNode = node;
            }
        return bestNode;
    }

    private boolean betterChoiseInList(Node node, ArrayList<Node> nodeList) {
        for (Node nodeListNode : nodeList)
            if (node.equals(nodeListNode)) return nodeListNode.getTotal() <= node.getTotal();
        return false;
    }

    private Tile isEnemyOverHear() {
        int yIndex = unitTile.getGameMap().getTileYIndex(unitTile.getPoint2d().getY());
        int xIndex = unitTile.getGameMap().getTileXIndex(unitTile.getPoint2d().getX(), yIndex);
        for (int yIndex2 = yIndex - 3; yIndex2 < yIndex + 3; yIndex2++)
            for (int xIndex2 = xIndex - 3; xIndex2 < xIndex + 3; xIndex2++) {
                Tile tile = unitTile.getGameMap().getTileByIndex(xIndex2, yIndex2);
                if (tile.hasEnemy(governance)) return tile;
            }
        return null;
    }


    public void gotDamage(int damage){
        if (this.hitpoint <= damage) {
            Game.getInstance().getGameMap().getChildren().remove(imageView);
            governance.getUnits().remove(this);
            unitTile.getUnits().remove(this);
        } else this.hitpoint -= damage;
    }

    private void moveOneTile(Tile tile) {
        unitTile = tile;
        TranslateTransition transition = new TranslateTransition(Duration.seconds(duration), imageView);
        final double x = tile.getPoint2d().getX();
        final double y = tile.getPoint2d().getY();
        transition.setToX(x);
        transition.setToY(y);
        transition.play();
    }

    private void changeMoveImage() {
        Timeline innerTimeline = new Timeline(new KeyFrame(Duration.seconds(duration / 4), event -> {
            imageView.setImage(unitType.getType().getMove()[imageIndex]);
            imageIndex = (imageIndex + 1) % 4;
        }));
        innerTimeline.setCycleCount(4);
        innerTimeline.play();
    }

    @Override
    public void attack(double x, double y) {
        // targetTile = unitTile.getGameMap().findClosestTile(x, y);
        // findPath();
        // final int size = path.size();
        // index = 0;
        // Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(duration), event -> {
        //     Tile tile2 = path.get(index);
        //     index++;
        //     moveOneTile(tile2);
        //     if ((tile2 = isEnemyOverHear()) != null) {
        //         path.clear();
        //         attack(tile2.getPoint2d().getX(), tile2.getPoint2d().getY());
        //     }
        //     changeMoveImage();
        // }));
        // timeline.setCycleCount(size);
        // timeline.play();
        // timeline.setOnFinished(event -> {
            
        // });
    }

    @Override
    public void move(Tile tile) {
        targetTile = tile;
        findPath();
        move(path);
    }

    @Override
    public void move(LinkedList<Tile> tiles) {
        final int size = tiles.size();
        index = 0;
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(duration), event -> {
            Tile tile2 = tiles.get(index);
            index++;
            moveOneTile(tile2);
            // if ((tile2 = isEnemyOverHear()) != null) {
            //     tiles.clear();
            //     attack(tile2.getPoint2d().getX(), tile2.getPoint2d().getY());
            // }
            changeMoveImage();
        }));
        timeline.setCycleCount(size);
        timeline.play();
    }

    // @Override
    // public String toString() {
    //     String hitpoint;
    //     if (this.hitpoint < unitType.getMaxHitpoint() / 3) hitpoint = RED_BOLD;
    //     else if (this.hitpoint < unitType.getMaxHitpoint() * 2 / 3) hitpoint = YELLOW_BOLD;
    //     else hitpoint = GREEN_BOLD;
    //     hitpoint += this.hitpoint + RESET;
    //     hitpoint += "/" + unitType.getMaxHitpoint();
    //     String owner = BLUE_BOLD + governance.getOwner().getNickname() + RESET;
    //     return unitType.getName() + " [" + hitpoint + "] \"" + owner + "\"";
    // }

}
