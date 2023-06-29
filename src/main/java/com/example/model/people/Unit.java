package com.example.model.people;

import java.util.ArrayList;
import java.util.LinkedList;

import com.example.model.Governance;
import com.example.model.buildings.Building;
import com.example.model.map.Tile;
import com.example.model.map.Tree;
import com.example.model.map.Node;
import com.example.model.map.Successor;

public class Unit implements Successor {
    protected final Governance governance;
    private final UnitType unitType;
    protected Tile unitTile;
    private boolean isFree;
    private Building place;
    private int hitpoint;
    protected int speed;
    protected Tile targetTile;
    private Tile patrolTile;
    protected LinkedList<Tile> path;
    private final boolean controllable;
    protected State state;

    public Unit(Governance governance, UnitType unitType, Tile unitTile) {
        this.governance = governance;
        this.unitType = unitType;
        this.unitTile = unitTile;
        unitTile.getUnits().add(this);
        this.isFree = true;
        hitpoint = unitType.getMaxHitpoint();
        speed = unitType.getMaxSpeed();
        this.controllable = unitType.isControllable();
        this.state = State.STANDING;
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

    public boolean isFree() {
        return isFree;
    }

    public Building getPlace() {
        return place;
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public int getSpeed() {
        return speed;
    }

    public Tile getTargetTile() {
        return targetTile;
    }

    public State getState() {
        return state;
    }

    public void setUnitTile(Tile unitTile) {
        this.unitTile = unitTile;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public void setPlace(Building place) {
        this.place = place;
    }

    public void setTargetTile(Tile targetTile) {
        this.targetTile = targetTile;
    }

    public Tile getPatrolTile() {
        return patrolTile;
    }

    public void setPatrolTile(Tile patrolTile) {
        this.patrolTile = patrolTile;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean addHitpoint(int hitpoint) {
        this.hitpoint += hitpoint;
        this.hitpoint = unitType.getMaxHitpoint() < this.hitpoint ? unitType.getMaxHitpoint() : hitpoint;
        return hitpoint > 0;
    }

    public void reduceSpeed(int speed) {
        this.speed -= speed;
    }

    public void resetSpeed() {
        speed = unitType.getMaxSpeed();
    }

    public void moveOneTile(Tile tile) {
        unitTile.getUnits().remove(this);
        unitTile = tile;
        unitTile.getUnits().add(this);
    }

    public boolean canGoTile(Tile tile) {
        return tile.getBuilding() == null;
    }

    public void movePath() {
        while (speed > 0 && !path.isEmpty()) {
            moveOneTile(path.getFirst());
            path.removeFirst();
            speed--;
        }
    }

    public void findPath() {
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
            System.out.println(openList.get(0).toString() + " size === " + openList.size());
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
        double leastTotal = 100000000000000.0;
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

    public LinkedList<Tile> getPath() {
        return path;
    }

    public boolean isControllable() {
        return controllable;
    }

    public void patrol(){
        movePath();
        Tile tile = this.patrolTile;
        if (this.unitTile.equals(this.targetTile)){
            this.patrolTile = this.targetTile;
            this.targetTile = tile;
            findPath();
            patrol();
        }
    }

    public boolean findNearestTree() {
        ArrayList<Tree> trees = unitTile.getGameMap().getTrees();
        while (!trees.isEmpty()) {
            Tree tree = findNearestTree(trees);
            targetTile = unitTile.getGameMap().findClosestTile(tree.getPoint2d().getX(), tree.getPoint2d().getY());
            trees.remove(tree);
            findPath();
            if (path.isEmpty()) continue;
            return true;
        }
        return false;
    }

    public Tree findNearestTree(ArrayList<Tree> trees) {
        Tree bestTree = trees.get(0);
        double bestDistance = unitTile.getPoint2d().distance(bestTree.getPoint2d());
        for (Tree tree : trees) {
            double distance = unitTile.getPoint2d().distance(tree.getPoint2d());
            if (distance < bestDistance) {
                bestDistance = distance;
                bestTree = tree;
            }
        }
        return bestTree;
    }

    public void doDamage(int damage){
        if (this.hitpoint < damage)
            this.hitpoint = 0;
        else this.hitpoint -= damage;
    }

    public void attack(int x, int y) {
        
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
