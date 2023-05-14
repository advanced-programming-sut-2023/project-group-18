package com.example.view;

import com.example.controller.Commands.GameMenuCommands;
import com.example.controller.Methods.GameMenuMethods;
import com.example.controller.Methods.GateMethods;
import com.example.controller.Methods.GlobalMethods;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GateMenu {
    private static GateMenu gateMenu;
    private final GlobalMethods globalMethods;
    private final GameMenuMethods gameMenuMethods;
    private final GateMethods gateMethods;
    private GateMenu() {
        globalMethods = GlobalMethods.getInstance();
        gameMenuMethods = GameMenuMethods.gameMenuMethods();
        gateMethods = GateMethods.getGateMethods();
    }
    public static GateMenu getInstance() {
        return gateMenu == null ? gateMenu = new GateMenu() : gateMenu;
    }

    public void run(Scanner scanner) {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if (GameMenuCommands.getMatcher(input, GameMenuCommands.CLOSE_GATE).find()) {
                closeGate();
            } else if (GameMenuCommands.getMatcher(input, GameMenuCommands.OPEN_GATE).find()) {
                openGate();
            }
        }
    }

    private void closeGate() {
        //TODO close gate
        System.out.println("gate was close successfully");
    }

    private void openGate() {
        //TODO open gate
        System.out.println("gate was opened successfully");
    }
}
