package com.example.model.Buildings;

import com.example.model.Governance;
import com.example.model.Map.Cell;

public class Tree extends Building{
    private final TreeType treeType;

    public Tree(BuildingType buildingType, Governance governance, Cell cell) {
        super(buildingType, governance, cell);
        treeType = TreeType.getRandomTreeType();
    }

    public Tree(BuildingType buildingType, Governance governance, Cell cell, TreeType treeType) {
        super(buildingType, governance, cell);
        this.treeType = treeType;
    }

    @Override
    public String toString() {
        return "Tree: " + treeType.getName();
    }

}
