package com.example.model.people;

import java.util.ArrayList;

import com.example.model.Governance;
import com.example.model.buildings.Category;
import com.example.model.buildings.Wall;
import com.example.model.map.Tile;

public class Soldier extends Unit {
    private final SoldierType soldierType;
    int hitpoint;
    private final int attackPower;
    private final int attackRange;
    private int damage;
    private boolean isAttack;

    public Soldier(Tile personTile, Governance governance, SoldierType soldierType) {
        super(governance,UnitType.SOLDIER,personTile);
        this.soldierType = soldierType;
        this.attackPower = soldierType.getAttackPower();
        this.attackRange = soldierType.getAttackRange();
        this.damage = soldierType.getAttackPower() * (20 + governance.getFearRate());
        this.isAttack = false;
        this.hitpoint = soldierType.getHitpoint();
        governance.getUnits().add(this);
    }

    public SoldierType getSoldierType() {
        return soldierType;
    }

    public boolean isAttack() {
        return isAttack;
    }

    public void setAttack(boolean isAttack) {
        this.isAttack = isAttack;
    }

    public void updateDamage() {
        this.damage = attackPower * (20 + this.getGovernance().getFearRate());
    }

    public int getDamage() {
        updateDamage();
        return damage;
    }

    public boolean canDigHole() {
        return this.soldierType.canDig();
    }

    public void digHole(Tile tile) {
        // tile.setTexture(Texture.HOLE);
    }

    public void removeHole(Tile tile) {
        // tile.setTexture(Texture.GROUND);
    }

    public void attack(Tile tile) {
        isAttack = false;
        if ((tile.getBuilding() != null) || (tile.getBuilding() instanceof Wall)) {
            Wall wall = (Wall)tile.getBuilding();
            if (wall.hasShield()) {
                wall.setHasShield(false);
                return;
            }
        }
        for (Unit unit : tile.getUnits()) {
            if (!unit.getGovernance().equals(this.getGovernance()))
                unit.addHitpoint(-this.getDamage());
        }
    }

    public void attackInBuilding(int additiveRange) {
        if (findNearestEnemyInBuilding(attackRange + additiveRange)) 
            attack(targetTile);
    }


    private boolean isInRange() {
        if (targetTile.getBuilding() != null) {
            final int range = targetTile.getBuilding().getBuildingType().getDefendRange();
            if (range != 0) return path.size() <= range;
        }
        return path.size() <= attackRange;
    }

    public void attack() {
        // while (speed > 0 && !path.isEmpty()) {
        //     if (isInRange()) {
        //         attack(targetTile);
        //         break;
        //     }
        //     moveOneTile(path.getFirst());
        //     path.removeFirst();
        //     speed--;
        // }
    }

    public boolean findNearestEnemyInBuilding(int range) {
        ArrayList<Governance> governances = new ArrayList<>(unitTile.getGameMap().getGame().getGovernances());
        final int index = governances.indexOf(governance);
        governances.remove(governance);
        while (!governances.isEmpty()) {
            Governance targetGovernance = governances.get(index % governances.size());
            governances.remove(targetGovernance);
            ArrayList<Unit> units = new ArrayList<>(targetGovernance.getUnits());
            Unit unit = findNearestEnemy(units);
            if (unit.getUnitTile().getPoint2d().distance(unitTile.getPoint2d()) > range) continue;
            targetTile = unit.getUnitTile();
            return true;
        }
        return false;
    }

    public boolean findNearestEnemy(int range) {
        ArrayList<Governance> governances = new ArrayList<>(unitTile.getGameMap().getGame().getGovernances());
        final int index = governances.indexOf(governance);
        governances.remove(governance);
        while (!governances.isEmpty()) {
            Governance targetGovernance = governances.get(index % governances.size());
            governances.remove(targetGovernance);
            ArrayList<Unit> units = new ArrayList<>(targetGovernance.getUnits());
            while (!units.isEmpty()) {
                Unit unit = findNearestEnemy(units);
                targetTile = unit.getUnitTile();
                units.remove(unit);
                findPath();
                if (path.isEmpty() && path.size() > range) {
                    path.clear();
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    public Unit findNearestEnemy(ArrayList<Unit> units) {
        Unit bestUnit = units.get(0);
        double bestDistance = unitTile.getPoint2d().distance(bestUnit.getUnitTile().getPoint2d());
        for (Unit unit : units) {
            double distance = unitTile.getPoint2d().distance(unit.getUnitTile().getPoint2d());
            if (distance < bestDistance) {
                bestDistance = distance;
                bestUnit = unit;
            }
        }
        return bestUnit;
    }

    public void run() {
        if (unitTile.getBuilding().getBuildingType().getCategory().equals(Category.TOWER))
            attackInBuilding(unitTile.getBuilding().getBuildingType().getFireRange());
        else if (isAttack) {
            if (findNearestEnemy(attackRange + state.getAdditiveRange()))
                attack();
        } else if (targetTile != null) {
            System.out.println("heyyy");
            findPath();
            // movePath();
        }
        System.out.println("hoouosij");

    }

    // @Override
    // public String toString() {
    //     String hitpoint;
    //     if (this.hitpoint < soldierType.getHitpoint() / 3) hitpoint = RED_BOLD;
    //     else if (this.hitpoint < soldierType.getHitpoint() * 2 / 3) hitpoint = YELLOW_BOLD;
    //     else hitpoint = GREEN_BOLD;
    //     hitpoint += this.hitpoint + RESET;
    //     hitpoint += "/" + soldierType.getHitpoint();
    //     String owner = BLUE_BOLD + governance.getOwner().getNickname() + RESET;
    //     return soldierType.getType() + " [" + hitpoint + "] \"" + owner + "\"";
    // }

}
