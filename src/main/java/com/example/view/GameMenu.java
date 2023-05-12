package com.example.view;

import com.example.controller.Commands.GameMenuCommands;
import com.example.controller.Commands.MapMenuCommands;
import com.example.controller.Methods.GameMenuMethods;
import com.example.controller.Methods.GlobalMethods;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {
    private static GameMenu gameMenu;
    private final GameMenuMethods gameMenuMethods;
    private final GlobalMethods globalMethods;

    private GameMenu() {
        gameMenuMethods = GameMenuMethods.gameMenuMethods();
        globalMethods = GlobalMethods.getInstance();
    }
    public GameMenu getGameMenu() {
        return gameMenu == null ? gameMenu = new GameMenu() : gameMenu;
    }

    public void run(Scanner scanner) {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.CURRENT_MENU)))
        }
    }

    private void showMap(Matcher matcher, Scanner scanner) {
        // TODO: omidreza
        
    }

}
