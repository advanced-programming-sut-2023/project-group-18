package com.example.view;

import com.example.controller.Methods.GameMenuMethods;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {
    private static GameMenu gameMenu;
    private final GameMenuMethods gameMenuMethods;
    private GameMenu() {
        gameMenuMethods = GameMenuMethods.gameMenuMethods();
    }
    public GameMenu getGameMenu() {
        return gameMenu == null ? gameMenu = new GameMenu() : gameMenu;
    }

    public void run(Scanner scanner) {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
        }
    }
}
