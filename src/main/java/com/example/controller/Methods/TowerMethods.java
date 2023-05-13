package com.example.controller.Methods;

import com.example.view.KeepMenu;

import java.util.Scanner;

public class TowerMethods implements SelectBuildingMenuMethods {
    private static TowerMethods towerMethods;
    private TowerMethods() {

    }
    public static TowerMethods getTowerMethods() {
        return towerMethods == null ? towerMethods = new TowerMethods() : towerMethods;
    }
    @Override
    public void run(Scanner scanner) {
        TowerMethods.getTowerMethods().run(scanner);
    }
}
