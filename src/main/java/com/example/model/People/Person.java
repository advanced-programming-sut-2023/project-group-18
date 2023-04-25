package com.example.model.People;
import com.example.model.Buildings.Building;

public class Person {
    private Cell personCell;
    private boolean isFree;
    private final Governance governance;
    private Building place;

    public Person(Cell personCell, Governance governance) {
        this.personCell = personCell;
        this.isFree = false;
        this.governance = governance;
    }

    public Governance getGovernance() {
        return governance;
    }

    public Cell getPersonCell() {
        return personCell;
    }

    public void setPersonCell(Cell personCell) {
        this.personCell = personCell;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public Building getPlace() {
        return place;
    }

    public void setPlace(Building place) {
        this.place = place;
    }
}
