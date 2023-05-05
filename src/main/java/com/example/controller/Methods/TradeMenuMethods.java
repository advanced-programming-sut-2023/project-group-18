package com.example.controller.Methods;

public class TradeMenuMethods {
    private static TradeMenuMethods tradeMenuMethods;

    private TradeMenuMethods() {
    }

    public static TradeMenuMethods getTradeMenuMethods() {
        return tradeMenuMethods == null ? tradeMenuMethods = new TradeMenuMethods() : tradeMenuMethods;
    }
}
