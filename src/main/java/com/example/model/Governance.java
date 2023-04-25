package com.example.model;

import java.util.ArrayList;

import com.example.model.Buildings.Building;
import com.example.model.People.Person;
import com.example.model.People.Soldier;

public class Governance {
    private final User owner;
    private final ArrayList<Building> buildings;
    private final ArrayList<Soldier> soldiers;
    private final ArrayList<Person> persons;

    public Governance(User owner) {
        this.owner = owner;
        buildings = new ArrayList<>();
        soldiers = new ArrayList<>();
        persons = new ArrayList<>();
    }

    public User getOwner() {
        return owner;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public ArrayList<Soldier> getSoldiers() {
        return soldiers;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }


}
