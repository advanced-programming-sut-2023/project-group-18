package com.example.view;

import com.example.controller.Commands.GameMenuCommands;
import com.example.controller.Commands.ShopMenuCommands;
import com.example.controller.Methods.GameMenuMethods;
import com.example.controller.Methods.GlobalMethods;
import com.example.controller.Methods.MarketMethods;
import com.example.controller.Methods.TowerMethods;
import com.example.model.Assets.Asset;
import com.example.model.Governance;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

public class TowerMenu {
    private static TowerMenu towerMenu;
    private final GameMenuMethods gameMenuMethods;
    private final GlobalMethods globalMethods;
    private final MarketMethods marketMethods;
    private final TowerMethods towerMethods;

    private TowerMenu() {
        gameMenuMethods = GameMenuMethods.gameMenuMethods();
        globalMethods = GlobalMethods.getInstance();
        marketMethods = MarketMethods.getMarketMethods();
        towerMethods = TowerMethods.getTowerMethods();
    }
    public static TowerMenu getTowerMenu() {
        return towerMenu == null ? towerMenu = new TowerMenu() : towerMenu;
    }
    public void run(Scanner scanner) {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if (GameMenuCommands.getMatcher(input, GameMenuCommands.REPAIR).find()) {
                repair();
            }
        }
    }

    private void repair() {
        if (!towerMethods.canRepair()) {
            System.out.println("you can not repair this building!");
            return;
        } else if (gameMenuMethods.haveEnemyInNeighbourCells()) {
            System.out.println("there is an enemy nearby");
            return;
        }
        towerMethods.repair();
    }
}
