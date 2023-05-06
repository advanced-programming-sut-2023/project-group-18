package com.example.model.People;

import java.util.ArrayList;
import java.util.LinkedList;

import com.example.model.Governance;
import com.example.model.Buildings.Building;
import com.example.model.Map.Cell;
import com.example.model.Map.Node;
import com.example.model.Map.Successor;

public class Unit extends Object implements Successor {
    private final Governance governance;
    private final UnitType unitType;
    private Cell unitCell;
    private boolean isFree;
    private Building place;
    private int hitpoint;
    private int speed;
    private Cell targetCell;
    private LinkedList<Cell> path;

    public Unit(Governance governance, UnitType unitType, Cell unitCell) {
        this.governance = governance;
        this.unitType = unitType;
        this.unitCell = unitCell;
        unitCell.getUnits().add(this);
        this.isFree = true;
        hitpoint = unitType.getMaxHitpoint();
        speed = unitType.getMaxSpeed();
    }

    public Governance getGovernance() {
        return governance;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public Cell getUnitCell() {
        return unitCell;
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

    public Cell getTargetCell() {
        return targetCell;
    }

    public void setUnitCell(Cell unitCell) {
        this.unitCell = unitCell;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public void setPlace(Building place) {
        this.place = place;
    }

    public void setTargetCell(Cell targetCell) {
        this.targetCell = targetCell;
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

    public void moveOneCell(Cell cell) {
        unitCell.getUnits().remove(this);
        unitCell = cell;
        unitCell.getUnits().add(this);
    }

    public boolean canGoCell(Cell cell) {
        return cell.getBuilding() == null;
    }

    public void movePath() {
        while (speed > 0 && !path.isEmpty()) {
            moveOneCell(path.getFirst());
            path.removeFirst();
            speed--;
        }
    }

    public void findPath() {
        Node node = findNode();
        path = new LinkedList<>();
        while (node != null) {
            path.addFirst(targetCell);
            node = node.getParent();
        }
    }

    private Node findNode() {
        final ArrayList<Node> openList = new ArrayList<>();
        final ArrayList<Node> closedList = new ArrayList<>();
        openList.add(new Node(null, unitCell, targetCell));
        while (!openList.isEmpty()) {
            Node node = findLeastTotalNode(openList);
            openList.remove(node);
            for (int[] successor : SUCCESSORS) {
                int x = node.getCell().getxCordinate() + successor[0];
                int y = node.getCell().getyCordinate() + successor[1];
                Cell cell = unitCell.getGameMap().getCellByLocation(x, y);
                if (cell == null) continue;
                if (!canGoCell(cell)) continue;
                Node neighbor = new Node(node, cell, targetCell);
                if (isDone(neighbor)) return neighbor;
                if (betterChoiseInList(neighbor, openList)) continue;
                if (betterChoiseInList(neighbor, closedList)) continue;
                openList.add(neighbor);
            }
            closedList.add(node);
        }
        return null;
    }

    private boolean isDone(Node neighbor) {
        return neighbor.getCell().equals(targetCell);
    }

    private Node findLeastTotalNode(ArrayList<Node> nodeList) {
        Node bestNode = null;
        double leastTotal = Double.MAX_VALUE;
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

}
