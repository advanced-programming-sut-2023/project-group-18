package com.example.view;

import com.example.view.SelectBuildingMenu;

import java.util.Scanner;

public class KeepMenu implements SelectBuildingMenu {
    private static KeepMenu keepMenu;
    public static KeepMenu getKeepMenu() {
        return keepMenu == null ? keepMenu = new KeepMenu() : keepMenu;
    }
    @Override
    public void run(Scanner scanner) {

    }
}