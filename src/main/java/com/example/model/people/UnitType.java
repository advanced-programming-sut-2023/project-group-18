package com.example.model.people;

import com.example.model.assets.Asset;
import com.example.model.buildings.BuildingType;

public enum UnitType {
        ARCHER_BOW ("Archer Bow", BuildingType.MERCENARY_POST, 30, 100, 70.0d, 80.0, 30),
        SLAVE ("Slave", BuildingType.MERCENARY_POST, 40, 50, 70.0d, 8.0, 10),
        SLINGER ("Slinger", BuildingType.MERCENARY_POST, 30, 100, 70.0d, 80.0, 300),
        ASSASSIN ("Assassin", BuildingType.MERCENARY_POST, 350, 200, 100.0d, 8.0, 50),
        HORSE_ARCHERS ("Horse Archers", BuildingType.MERCENARY_POST, 30, 300, 120.0d, 80.0, 20),
        ARABIAN_SWORDSMEN ("Arabian Swordsmen", BuildingType.MERCENARY_POST, 30, 150, 30.0d, 80.0, 40),

        ;

        private final UnitImages type;
        private final BuildingType buildingType;
        private final int damage;
        private final int maxHitpoint;
        private final double speed;
        private final double range;
        private final Asset[] assets;
        private final int cost;

        private UnitType(String name, BuildingType buildingType, int damage, int maxHitpoint, double speed, double range, int cost, Asset... assets) {
                this.type = new UnitImages(name);
                this.buildingType = buildingType;
                this.damage = damage;
                this.maxHitpoint = maxHitpoint;
                this.speed = speed;
                this.range = range;
                this.cost = cost;
                this.assets = assets;
        }

        public Asset[] getAssets() {
            return assets;
        }

        public BuildingType getBuildingType() {
            return buildingType;
        }

        public int getCost() {
            return cost;
        }

        public int getDamage() {
            return damage;
        }

        public int getMaxHitpoint() {
            return maxHitpoint;
        }

        public double getRange() {
            return range;
        }

        public double getSpeed() {
            return speed;
        }

        public UnitImages getType() {
            return type;
        }

}
