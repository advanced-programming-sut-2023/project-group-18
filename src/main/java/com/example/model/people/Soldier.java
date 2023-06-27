package com.example.model.people;

import java.util.ArrayList;

import com.example.model.Governance;
import com.example.model.buildings.Category;
import com.example.model.buildings.Wall;
import com.example.model.map.Cell;
import com.example.model.map.Texture;

public class Soldier extends Unit {
    private final SoldierType soldierType;
    int hitpoint;
    private final int attackPower;
    private final int attackRange;
    private int damage;
    private boolean isAttack;

    public Soldier(Cell personCell, Governance governance, SoldierType soldierType) {
        super(governance,UnitType.SOLDIER,personCell);
        this.soldierType = soldierType;
        this.attackPower = soldierType.getAttackPower();
        this.attackRange = soldierType.getAttackRange();
        this.damage = soldierType.getAttackPower() * (20 + governance.getFearRate());
        this.isAttack = false;
        this.hitpoint = soldierType.getHitpoint();
        governance.getSoldiers().add(this);
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

    public void digHole(Cell cell) {
        // cell.setTexture(Texture.HOLE);
    }

    public void removeHole(Cell cell) {
        // cell.setTexture(Texture.GROUND);
    }

    public void attack(Cell cell) {
        isAttack = false;
        if ((cell.getBuilding() != null) || (cell.getBuilding() instanceof Wall)) {
            Wall wall = (Wall)cell.getBuilding();
            if (wall.hasShield()) {
                wall.setHasShield(false);
                return;
            }
        }
        for (Unit unit : cell.getUnits()) {
            if (!unit.getGovernance().equals(this.getGovernance()))
                unit.addHitpoint(-this.getDamage());
        }
    }

    public void attackInBuilding(int additiveRange) {
        if (findNearestEnemyInBuilding(attackRange + additiveRange)) 
            attack(targetCell);
    }


    private boolean isInRange() {
        if (targetCell.getBuilding() != null) {
            final int range = targetCell.getBuilding().getBuildingType().getDefendRange();
            if (range != 0) return path.size() <= range;
        }
        return path.size() <= attackRange;
    }

    public void attack() {
        while (speed > 0 && !path.isEmpty()) {
            if (isInRange()) {
                attack(targetCell);
                break;
            }
            moveOneCell(path.getFirst());
            path.removeFirst();
            speed--;
        }

    }

    public boolean findNearestEnemyInBuilding(int range) {
        ArrayList<Governance> governances = new ArrayList<>(unitCell.getGameMap().getGame().getGovernances());
        final int index = governances.indexOf(governance);
        governances.remove(governance);
        while (!governances.isEmpty()) {
            Governance targetGovernance = governances.get(index % governances.size());
            governances.remove(targetGovernance);
            ArrayList<Unit> units = new ArrayList<>(targetGovernance.getSoldiers());
            Unit unit = findNearestEnemy(units);
            if (unit.getUnitCell().calculatePythagorean(unitCell) > range) continue;
            targetCell = unit.getUnitCell();
            return true;
        }
        return false;
    }

    public boolean findNearestEnemy(int range) {
        ArrayList<Governance> governances = new ArrayList<>(unitCell.getGameMap().getGame().getGovernances());
        final int index = governances.indexOf(governance);
        governances.remove(governance);
        while (!governances.isEmpty()) {
            Governance targetGovernance = governances.get(index % governances.size());
            governances.remove(targetGovernance);
            ArrayList<Unit> units = new ArrayList<>(targetGovernance.getSoldiers());
            while (!units.isEmpty()) {
                Unit unit = findNearestEnemy(units);
                targetCell = unit.getUnitCell();
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
        double bestDistance = unitCell.calculatePythagorean(bestUnit.getUnitCell());
        for (Unit unit : units) {
            double distance = unitCell.calculatePythagorean(unit.getUnitCell());
            if (distance < bestDistance) {
                bestDistance = distance;
                bestUnit = unit;
            }
        }
        return bestUnit;
    }

    public void run() {
        if (unitCell.getBuilding().getBuildingType().getCategory().equals(Category.TOWER))
            attackInBuilding(unitCell.getBuilding().getBuildingType().getFireRange());
        else if (isAttack) {
            if (findNearestEnemy(attackRange + state.getAdditiveRange()))
                attack();
        } else if (targetCell != null) {
            System.out.println("heyyy");
            findPath();
            movePath();
        }
        System.out.println("hoouosij");

    }

    @Override
    public String toString() {
        String hitpoint;
        if (this.hitpoint < soldierType.getHitpoint() / 3) hitpoint = RED_BOLD;
        else if (this.hitpoint < soldierType.getHitpoint() * 2 / 3) hitpoint = YELLOW_BOLD;
        else hitpoint = GREEN_BOLD;
        hitpoint += this.hitpoint + RESET;
        hitpoint += "/" + soldierType.getHitpoint();
        String owner = BLUE_BOLD + governance.getOwner().getNickname() + RESET;
        return soldierType.getType() + " [" + hitpoint + "] \"" + owner + "\"";
    }

}
