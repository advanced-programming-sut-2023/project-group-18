package com.example.view;

import com.example.controller.Commands.SignupMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class SignupMenu {
    private static SignupMenu instance;
    private SignupMenu() {
    }
    public static SignupMenu getInstance() {
        if (instance == null) {
            instance = new SignupMenu();
        }
        return instance;
    }
    public void run(Scanner scanner) {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if ((matcher = SignupMenuCommands.getMatcher(input, SignupMenuCommands.CREATE_USER)).find())
                register(matcher);
        }
    }

    private void register(Matcher matcher) {
    }
}
