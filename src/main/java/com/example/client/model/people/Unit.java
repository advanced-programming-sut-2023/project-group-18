package com.example.client.model.people;

import com.example.client.model.Game;
import com.example.client.model.Governance;
import com.example.client.model.map.Node;
import com.example.client.model.map.Successor;
import com.example.client.model.map.Tile;
import com.example.client.view.Client;
import com.example.client.view.images.Images;
import com.example.client.view.images.TextureImages;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.LinkedList;

public class Unit implements Successor, MoveInteface {
    protected final Governance governance;
    private final UnitType unitType;
    private final ImageView imageView;
    private final double duration;
    protected Tile unitTile;
    protected Tile targetTile;
    protected final LinkedList<Tile> path;
    private int hitpoint;
    private int imageIndex;

    public Unit(Governance governance, UnitType unitType, Tile unitTile) {
        this.governance = governance;
        this.unitType = unitType;
        this.unitTile = unitTile;
        this.duration = 10 / unitType.getSpeed();
        this.path = new LinkedList<>();
        hitpoint = unitType.getMaxHitpoint();
        unitTile.getUnits().add(this);
        imageView = new ImageView(unitType.getType().getStanding());
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        Game.getInstance().getGameMap().getChildren().add(imageView);
        addToolTip();
        imageView.setLayoutX(unitTile.getPoint2d().getX() - 10);
        imageView.setLayoutY(unitTile.getPoint2d().getY() - 10);
    }

    private void addToolTip() {
        Popup popup = new Popup();
        Label label = new Label();
        popup.getContent().add(label);
        label.getStylesheets().add(Client.class.getResource("/css/Popup.css").toExternalForm());
        label.setPadding(new Insets(20, 10, 20, 10));

        imageView.setOnMouseEntered(event -> {
            label.setText(toString());
            popup.show(Client.getStage(), event.getSceneX() + 20, event.getSceneY());
        });

        imageView.setOnMouseExited(event -> {
            popup.hide();
        });

        imageView.setOnMouseClicked(event -> {
            unitTile.getGameMap().getGame().selectUnit(this);
            label.setText("Selected");
            popup.show(Client.getStage(), event.getSceneX() + 20, event.getSceneY());
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

    public boolean isEnemy(Unit unit) {
        return !unit.getGovernance().equals(this.getGovernance());
    }


    public boolean canGoTile(Tile tile) {
        return tile.getBuilding() == null && (tile.getTexture().getTextureImages().equals(TextureImages.FARM)
            || tile.getTexture().getTextureImages().equals(TextureImages.GROUND));
    }


    protected void findPath() {
        if (unitTile.equals(targetTile)) return;
        Node node = findNode();
        path.clear();
        while (node != null) {
            path.addFirst(node.getTile());
            node = node.getParent();
        }
    }

    private Node findNode() {
        final ArrayList<Node> openList = new ArrayList<>();
        final ArrayList<Node> closedList = new ArrayList<>();
        openList.add(new Node(null, unitTile, targetTile));
        while (!openList.isEmpty()) {
            Node node = findLeastTotalNode(openList);
            System.out.println(node.toString() + " size === " + openList.size()); // TODO: need to remove
            int yIndex = node.getTile().getGameMap().getTileYIndex(node.getTile().getPoint2d().getY());
            int xIndex = node.getTile().getGameMap().getTileXIndex(node.getTile().getPoint2d().getX(), yIndex);
            l:
            for (int[] successor : SUCCESSORS[yIndex % 2]) {
                int xIndex2 = xIndex + successor[0];
                int yIndex2 = yIndex + successor[1];
                Tile tile = unitTile.getGameMap().getTileByIndex(xIndex2, yIndex2);
                if (tile == null) continue l;
                if (!canGoTile(tile)) continue l;
                Node neighbor = new Node(node, tile, targetTile);
                if (closedList.contains(neighbor)) continue l;
                if (isDone(neighbor)) return neighbor;
                if (betterChoiseInList(neighbor, openList)) continue l;
                if (betterChoiseInList(neighbor, closedList)) continue l;
                openList.add(neighbor);
            }
            closedList.add(node);
            openList.remove(node);
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

    public Tile isEnemyOverHear() {
        int yIndex = unitTile.getGameMap().getTileYIndex(unitTile.getPoint2d().getY());
        int xIndex = unitTile.getGameMap().getTileXIndex(unitTile.getPoint2d().getX(), yIndex);
        for (int yIndex2 = yIndex - 3; yIndex2 < yIndex + 3; yIndex2++)
            for (int xIndex2 = xIndex - 3; xIndex2 < xIndex + 3; xIndex2++) {
                Tile tile = unitTile.getGameMap().getTileByIndex(xIndex2, yIndex2);
                if (tile.hasEnemy(governance)) return tile;
            }
        return null;
    }


    public void gotDamage(int damage) {
        imageView.setImage(Images.DAMAGE.getImage());
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {}));
        timeline.setOnFinished(event -> imageView.setImage(unitType.getType().getStanding()));
        timeline.play();
        if (this.hitpoint <= damage) {
            Game.getInstance().getGameMap().getChildren().remove(imageView);
            governance.getUnits().remove(this);
            unitTile.getUnits().remove(this);
        } else this.hitpoint -= damage;
    }

    private void changeMoveImage() {
        imageView.setImage(unitType.getType().getMove()[imageIndex]);
        imageIndex = (imageIndex + 1) % 4;
    }

    private void changeAttackImage() {
        imageView.setImage(unitType.getType().getAttack()[imageIndex]);
        imageIndex = (imageIndex + 1) % 2;
    }

    private void move(int index, LinkedList<Tile> tiles) {
        if (tiles.size() == index) {
            imageView.setImage(unitType.getType().getStanding());
            tiles.clear();
            return;
        }
        tiles.get(index - 1).getUnits().remove(this);
        tiles.get(index).getUnits().add(this);
        unitTile = tiles.get(index);
        final double startX = tiles.get(index - 1).getPoint2d().getX();
        final double startY = tiles.get(index - 1).getPoint2d().getY();
        final double endX = tiles.get(index).getPoint2d().getX();
        final double endY = tiles.get(index).getPoint2d().getY();
        final double deltaX = (endX - startX) / 4;
        final double deltaY = (endY - startY) / 4;
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(duration), event -> {
            imageView.setLayoutX(imageView.getLayoutX() + deltaX);
            imageView.setLayoutY(imageView.getLayoutY() + deltaY);
            changeMoveImage();
        }));
        timeline.setCycleCount(4);
        timeline.setOnFinished(event -> move(index + 1, tiles));
        timeline.play();
    }

    public void attack() {
        System.out.println("FUCK");
        Tile tile = isEnemyOverHear();
        if (tile == null) return;
        System.out.println("YOU");
        ImageView imageView = new ImageView(Images.EXCLAMATION.getImage());
        imageView.setFitHeight(20);
        imageView.setFitWidth(10);
        imageView.setLayoutX(unitTile.getPoint2d().getX());
        imageView.setLayoutY(unitTile.getPoint2d().getY());
        unitTile.getGameMap().getChildren().add(imageView);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.4), event -> {
            changeAttackImage();
        }));
        timeline.setCycleCount(5);
        timeline.setOnFinished(event -> {
            unitTile.getGameMap().getChildren().remove(imageView);
            for (Unit unit : tile.getUnits())
                // if (this.isEnemy(unit))
                    unit.gotDamage(unitType.getDamage());
        });
        timeline.play();
    }

    @Override
    public void move(Tile tile) {
        targetTile = tile;
        findPath();
        if (path.isEmpty()) return;
        path.removeFirst();
        move(path);
    }

    @Override
    public void move(LinkedList<Tile> tiles) {
        if (tiles.isEmpty()) return;
        tiles.addFirst(unitTile);
        move(1, tiles);
    }

    @Override
    public String toString() {
        String hitpoint = this.hitpoint + "/" + unitType.getMaxHitpoint();
        String owner = governance.getOwner().getNickname();
        String details = unitType.toString();
        return unitType.getType().getName() + " [" + hitpoint + "] \"" + owner + "\"\n" + details;
    }

}
