package com.example.model.People;

import com.example.model.Map.Cell;

import java.util.ArrayList;

import com.example.model.Governance;
import com.example.model.Buildings.Category;
import com.example.model.Map.Texture;

public class Soldier extends Unit {
    private final SoldierType soldierType;
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

    public boolean canDigHole(){
        return this.soldierType.canDig();
    }

    public void digHole(Cell cell){
        cell.setTexture(Texture.HOLE);
    }

    public void removeHole(Cell cell){
        cell.setTexture(Texture.GROUND);
    }

    public void attack(Cell cell) {
        for (Unit unit : cell.getUnits()){
            if (!unit.getGovernance().equals(this.getGovernance()))
                unit.addHitpoint(-this.getDamage());
        }
    }

    public void attackInBuilding(int additiveRange) {
        if (findNearestEnemyInBuilding(attackRange + additiveRange)) 
            attack(targetCell);
    }


    public void attack() {
        while (speed > 0 && !path.isEmpty()) {
            if (path.size() <= attackRange)
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
            findPath();
            movePath();
        }
    }

}
