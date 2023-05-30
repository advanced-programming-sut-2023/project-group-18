package com.example.model.people;

import com.example.model.Governance;
import com.example.model.map.Cell;

public class Engineer extends Soldier{
    public Engineer(Cell personCell, Governance governance, SoldierType soldierType) {
        super(personCell, governance, soldierType);
    }

}
