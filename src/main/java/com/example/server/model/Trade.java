package com.example.server.model;

import java.util.ArrayList;

import com.example.server.model.assets.Asset;

public class Trade {
    private static int nextId;
    private final int id;
    private final Governance sender;
    private final Governance accepter;
    private final Asset resourceType;
    private final int resourceAmount;
    private final int price;
    private final String message;
    private String accepterMessage;
    private int status;

    public Trade(Governance sender, Governance accepter, Asset resourceType, int resourceAmount, int price, String message) {
        id = nextId++;
        this.sender = sender;
        this.accepter = accepter;
        this.resourceType = resourceType;
        this.resourceAmount = resourceAmount;
        this.price = price;
        this.message = message;
        status = 0;
        sender.removeAssetFromStorage(resourceType, resourceAmount);
        sender.getRequestList().add(this);
        accepter.getTradeList().add(this);
        accepter.getTradeNotifications().add(this);
    }

    public static Trade getTradebyId(int id, ArrayList<Trade> tradeList) {
        for (Trade trade : tradeList)
            if (trade.id == id) return trade;
        return null;
    }

    public int getId() {
        return id;
    }

    public Governance getSender() {
        return sender;
    }

    public Governance getAccepter() {
        return accepter;
    }

    public Asset getResourceType() {
        return resourceType;
    }

    public int getResourceAmount() {
        return resourceAmount;
    }

    public int getPrice() {
        return price;
    }

    public int getStatus() {
        return status;
    }

    private void changeTradeList(String message) {
        sender.getRequestList().remove(this);
        accepter.getTradeList().remove(this);
        accepter.getTradeNotifications().remove(this);
        sender.getTradeHistory().add(this);
        accepter.getTradeHistory().add(this);
        accepterMessage = message;
    }

    public void acceptTrade(String message) {
        status = 1;
        accepter.addAssetToStorage(resourceType, resourceAmount);
        accepter.addGold(-price);
        sender.addGold(price);
        changeTradeList(message);
    }

    public void rejectTrade(String message) {
        status = -1;
        sender.addAssetToStorage(resourceType, resourceAmount);
        changeTradeList(message);
    }

    public void cancelTrade() {
        sender.addAssetToStorage(resourceType, resourceAmount);
        sender.getRequestList().add(this);
        accepter.getTradeList().add(this);
        accepter.getTradeNotifications().remove(this);
    }

    private String statusString() {
        if (status == 0) return "Waiting for " + accepter.getOwner().getUsername();
        if (status == 1) return "Accepted!";
        if (status == -1) return "Rejected!";
        return null;
    }


    public String toStringTradeList() {
        return "From: " + sender.getOwner().getUsername()
            + "\n\tTrade Id: " + id
            + "\n\tResource Type: " + resourceType.getName()
            + "\n\tResource Amount: " + resourceAmount
            + "\n\tPrice: " + price
            + "\n\tMessage: " + message;
    }

    public String toStringRequestList() {
        return "To: " + accepter.getOwner().getUsername()
            + "\n\tTrade Id: " + id
            + "\n\tResource Type: " + resourceType.getName()
            + "\n\tResource Amount: " + resourceAmount
            + "\n\tPrice: " + price
            + "\n\tMessage: " + message;
    }

    @Override
    public String toString() {
        return "Trade Status: " + statusString()
            + "\n\tTrade Id: " + id
            + "\n\tSender: " + sender.getOwner().getUsername()
            + "\n\tAccepter: " + accepter.getOwner().getUsername()
            + "\n\tResource Type: " + resourceType.getName()
            + "\n\tResource Amount: " + resourceAmount
            + "\n\tPrice: " + price
            + "\n\tMessage: " + message
            + "\n\tAccepter Message: " + accepterMessage;
    }

}
