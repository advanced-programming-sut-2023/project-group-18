package com.example.view;

import com.example.controller.Methods.ShopMenuMethods;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenu {
    private static ShopMenu shopMenu;
    private final ShopMenuMethods shopMenuMethods;
    private ShopMenu() {
        shopMenuMethods = ShopMenuMethods.getInstance();
    }
    public ShopMenu getShopMenu() {
        return shopMenu == null ? shopMenu = new ShopMenu() : shopMenu;
    }
    public void run(Scanner scanner) {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
        }
    }
}
