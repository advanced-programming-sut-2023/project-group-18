package com.example.view;

import com.example.controller.Commands.GameMenuCommands;
import com.example.controller.Methods.BarracksMethods;
import com.example.controller.Methods.GameMenuMethods;
import com.example.controller.Methods.GlobalMethods;
import com.example.model.People.SoldierType;
import com.example.model.People.UnitType;

import java.util.Scanner;
import java.util.regex.Matcher;

public class BarracksMenu {
    private static BarracksMenu barracksMenu;
    private final GlobalMethods globalMethods;
    private final BarracksMethods barracksMethods;
    private final GameMenuMethods gameMenuMethods;

    private BarracksMenu() {
        globalMethods = GlobalMethods.getInstance();
        barracksMethods = BarracksMethods.getBarracksMethods();
        gameMenuMethods = GameMenuMethods.gameMenuMethods();
    }

    public static BarracksMenu getBarracksMenu() {
        return barracksMenu == null ? barracksMenu = new BarracksMenu() : barracksMenu;
    }
    
    public void run(Scanner scnnaer) {
        String input;
        Matcher matcher;
        while (true) {
            input = scnnaer.nextLine();
            if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.CREATE_UNIT)).find()) {
                createUnit(matcher);
            } else if (GameMenuCommands.getMatcher(input,GameMenuCommands.EXIT).find()) {
                break;
            } else globalMethods.invalidCommand();
        }
    }
    
    private void createUnit(Matcher matcher) {
        String typeName = barracksMethods.getType(matcher);
        int count = Integer.parseInt(barracksMethods.getCount(matcher));
        if (SoldierType.getSoldierTypeByName(typeName) == null) {
            System.out.println("there is no unit type with this name");
            return;
        } else if (gameMenuMethods.haveEnoughPeople(count)) {
            System.out.println("there is not enough people");
            return;
        } else if (gameMenuMethods.haveEnoughResourcesForTroop(typeName, count)) {
            System.out.println("you don't have enough resources");
            return;
        } else if (!gameMenuMethods.isCompatibleWithBarracks(typeName)) {
            System.out.println("this type is not compatible with barracks");
            return;
        }
        gameMenuMethods.deployTroop(typeName,count);
        System.out.println("troops were created successfully");
    }

}
