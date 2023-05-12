package com.example.model.People;

import com.example.model.Governance;
import com.example.model.Map.Cell;

public class Worker extends Unit{
    public Worker(Governance governance, UnitType unitType, Cell unitCell) {
        super(governance, unitType, unitCell);
    }
}
