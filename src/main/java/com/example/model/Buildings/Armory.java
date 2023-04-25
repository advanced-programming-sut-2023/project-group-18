package com.example.model.Buildings;

import java.util.ArrayList;
import java.util.HashMap;

public class Armory extends Building{
    private final String weapon;
    private final ArrayList<String> resources;
    private final int rate;

    public Armory(String buildingType, Governance governance, Cell cell, String weapon) {
        super(buildingType, governance, cell);
        this.weapon = weapon;
    }


}
