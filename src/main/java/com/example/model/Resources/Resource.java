package com.example.model.Resources;

public class Resource {
    private final ResourceType resourceType;
    private int price;
    private int count;

    public Resource(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // TODO: handle sell

}
