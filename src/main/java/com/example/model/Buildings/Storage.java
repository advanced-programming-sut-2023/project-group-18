package com.example.model.Buildings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.model.Governance;
import com.example.model.Comodities.Resource;
import com.example.model.Comodities.ResourceType;
import com.example.model.Cell;

public class Storage extends Building{
    private final ResourceType type;
    private final int capacity;
    private final ArrayList<Resource> products;
    private int currentCapacity;

    public Storage(String buildingType, Governance governance, Cell cell, ResourceType type, int capacity) {
        super(buildingType, governance, cell);
        products = new ArrayList<>();
        this.type = type;
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public boolean isPossibleAddProduct(int count) {
        return count + currentCapacity <= this.remainingCapacity();
    }

    public boolean isPossibleRemoveProduct(String name, int count) {
        return true;
    }

    public void addProduct(String name, int count){
        currentCapacity += count;
        if (products.containsKey(name)) products.put(name, products.get(name) + count);
        else products.put(name,count);
    }

    public void removeProduct(String name, int count){
        currentCapacity -= count;
        products.put(name, products.get(name) - count);
    }

}
