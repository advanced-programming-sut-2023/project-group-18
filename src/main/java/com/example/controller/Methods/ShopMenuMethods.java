package com.example.controller.Methods;

public class ShopMenuMethods {
    private static ShopMenuMethods shopMenuMethods;
    private ShopMenuMethods() {
    }
    public static ShopMenuMethods getInstance() {
        return shopMenuMethods == null ? shopMenuMethods = new ShopMenuMethods() : shopMenuMethods;
    }
}
