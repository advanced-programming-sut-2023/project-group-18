package com.example.model.buildings;

import com.example.model.map.Cell;

public class Tree extends Building{
    private final TreeType treeType;

    public Tree(Cell cell, TreeType treeType) {
        super(BuildingType.TREE, null, cell);
        this.treeType = treeType;
    }

    @Override
    public boolean isReachable() {
        return false;
    }

    @Override
    public String toString() {
        return "Tree: " + treeType.getName();
    }

}
