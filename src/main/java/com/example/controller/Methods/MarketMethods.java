package com.example.controller.Methods;

import com.example.view.KeepMenu;

import java.util.Scanner;

public class MarketMethods implements SelectBuildingMenuMethods{
    private static MarketMethods marketMethods;
    private MarketMethods() {

    }
    public static MarketMethods getMarketMethods() {
        return marketMethods == null ? marketMethods = new MarketMethods() : marketMethods;
    }
    @Override
    public void run(Scanner scanner) {
        MarketMethods.getMarketMethods().run(scanner);
    }
}
