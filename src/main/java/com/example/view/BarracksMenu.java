package com.example.view;

import com.example.controller.Commands.GameMenuCommands;
import com.example.controller.Methods.BarracksMethods;
import com.example.controller.Methods.GlobalMethods;

import java.util.Scanner;
import java.util.regex.Matcher;

public class BarracksMenu {
    private static BarracksMenu barracksMenu;
    private final GlobalMethods globalMethods;
    private final BarracksMethods barracksMethods;
    private BarracksMenu() {
        globalMethods = GlobalMethods.getInstance();
        barracksMethods = BarracksMethods.getBarracksMethods();
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
    }
}
