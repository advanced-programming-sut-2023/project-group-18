package com.example.model.Map;

public class Node {
    private final Node parent;
    private final Cell cell;
    private double total, heuristic, given;

    public Node(Node parent, Cell cell, Cell target) {
        this.parent = parent;
        this.cell = cell;
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

    public Cell getCell() {
        return cell;
    }

    private double calculateGiven() {
        if (parent == null) return 0;
        return cell.calculatePythagorean(parent.cell);
    }

    private double calculateHeuristic(Cell target) {
        return cell.calculatePythagorean(target);
    }

    @Override
    public boolean equals(Object obj) {
        return ((Node) obj).cell.equals(this.cell);
    }

}
