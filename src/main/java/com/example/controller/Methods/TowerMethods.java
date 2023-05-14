package com.example.controller.Methods;

import java.util.Scanner;

import com.example.model.Buildings.Tower;
import com.example.view.TowerMenu;

public class TowerMethods implements SelectBuildingMenuMethods {
    private static TowerMethods towerMethods;
    private final GameMenuMethods gameMenuMethods;

    private TowerMethods() {
        gameMenuMethods = GameMenuMethods.gameMenuMethods();
    }

    public static TowerMethods getTowerMethods() {
        return towerMethods == null ? towerMethods = new TowerMethods() : towerMethods;
    }

    public void repair() {
        ((Tower)gameMenuMethods.getGame().getSelectedBuilding()).repair();
    }

    public boolean canRepair() {
        return ((Tower)gameMenuMethods.getGame().getSelectedBuilding()).canRepair();
    }

    @Override
    public void run(Scanner scanner) {
        TowerMenu.getTowerMenu().run(scanner);
    }
}
