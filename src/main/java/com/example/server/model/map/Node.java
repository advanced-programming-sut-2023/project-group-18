package com.example.server.model.map;

public class Node {
    private final Node parent;
    private final Tile tile;
    private double total, heuristic, given;

    public Node(Node parent, Tile tile, Tile target) {
        this.parent = parent;
        this.tile = tile;
        given = calculateGiven();
        heuristic = calculateHeuristic(target);
        total = given + heuristic;
    }

    public double getTotal() {
        return total;
    }

    public Node getParent() {
        return parent;
    }

    public Tile getTile() {
        return tile;
    }

    private double calculateGiven() {
        if (parent == null) return 0;
        return parent.given + 1;
    }

    private double calculateHeuristic(Tile target) {
        return tile.getPoint2d().distance(target.getPoint2d());
    }

    @Override
    public boolean equals(Object obj) {
        return ((Node) obj).tile.equals(this.tile);
    }

    @Override
    public String toString() {
        return "total: " + total + " given, heuristic: " + given + ", " + heuristic;
    }

}
