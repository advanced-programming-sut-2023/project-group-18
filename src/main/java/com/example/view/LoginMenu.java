package com.example.view;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
    private static LoginMenu instance;
    private LoginMenu() {
    }
    public LoginMenu getInstance() {
        return instance == null ? instance = new LoginMenu() : instance;
    }

    public void run(Scanner scanner) {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();

        }
    }
}
