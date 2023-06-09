package com.example.model.People;

import com.example.model.Governance;
import com.example.model.Map.Cell;

import java.util.ArrayList;

public class AttackTool extends Unit {
    private final AttackToolType type;
    private final int engineersNeeded;
    private final ArrayList<Engineer> engineers;

    public AttackTool(Governance governance, AttackToolType type, Cell unitCell) {
        super(governance, UnitType.ATTACK_TOOL, unitCell);
        this.type = type;
        this.engineersNeeded = type.getEngineersNeeded();
        this.engineers = new ArrayList<>();
    }

    public boolean couldAddEngineer(){
        return this.engineers.size() < engineersNeeded;
    }
    public void addEngineer(Engineer engineer){
        this.engineers.add(engineer);
    }
    public boolean couldWork(){
        return this.engineers.size() == engineersNeeded;
    }

}
