package com.example.model.Buildings;

import sun.security.mscapi.PRNG;

import java.util.HashMap;
import java.util.Map;

public class Storage extends Building{
    private final String type;
    private final int capacity;
    private final HashMap<String,Integer> products;

    public Storage(String buildingType, Governance governance, Cell cell) {
        super(buildingType, governance, cell);
        products = new HashMap<>();
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public HashMap<String, Integer> getProducts() {
        return products;
    }

    public int remainingCapacity(){
        int remain = this.getCapacity();
        for (Map.Entry<String, Integer> product : products.entrySet()) {
            remain -= product.getValue();
        }
        return remain;
    }

    public boolean isPossibleAddProduct(int count){
        return count <= this.remainingCapacity();
    }

    public boolean isPossibleRemoveProduct(String name, int count){
        return true;
    }
    public void addProduct(String name, int count){
        if (products.containsKey(name)){
            products.put(name, products.get(name) + count);
            return;
        }
        products.put(name,count);
    }
    public void removeProduct(String name, int count){
        products.put(name, products.get(name) - count);
    }
}
