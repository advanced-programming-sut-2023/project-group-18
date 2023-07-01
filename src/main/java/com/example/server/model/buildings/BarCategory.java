package com.example.server.model.buildings;

public enum BarCategory {
    CASTLE("CastleBuilding"),
    FOOD("FoodProcessing"),
    INDUSTRIAL("IndustryBuilding"),
    TOWN("TownBuilding"),
    WEAPON("WeaponBuilding"),
    FARM("FarmBuilding"),
    TOWER("TowerBuilding"),
    NONE("None");
    private final String name;

    BarCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static BarCategory getBarCategoryByName(String name) {
        for (BarCategory barCategory : BarCategory.values()) {
            if (barCategory.getName().equals(name))
                return barCategory;
        }
        return null;
    }
}
