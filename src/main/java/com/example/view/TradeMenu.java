package com.example.view;

import com.example.controller.Methods.TradeMenuMethods;

import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeMenu {
    private static TradeMenu tradeMenu;
    private final TradeMenuMethods tradeMenuMethods;
    private TradeMenu() {
        tradeMenuMethods = TradeMenuMethods.getTradeMenuMethods();
    }
    public TradeMenu getTradeMenu() {
        return tradeMenu == null ? tradeMenu = new TradeMenu() : tradeMenu;
    }
    public void run(Scanner scanner) {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
        }
    }
}
