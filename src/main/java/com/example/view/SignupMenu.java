package com.example.view;

import com.example.controller.Commands.SignupMenuCommands;
import com.example.controller.Methods.GlobalMethods;
import com.example.controller.Methods.SignupMenuMethods;

import java.util.ArrayList;
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
            if ((matcher = SignupMenuCommands.getMatcher(input, SignupMenuCommands.CREATE_USER)).find()) {
                register(matcher);
            } else if (SignupMenuCommands.getMatcher(input, SignupMenuCommands.EXIT).find()) {
                break;
            } else {
                GlobalMethods.invalidCommand();
            }
        }
    }

    private void register(Matcher matcher) {
        ArrayList<String> fields = GlobalMethods.commandSplit(matcher.group("fields"));
        SignupMenuMethods signupMenuMethods = SignupMenuMethods.getInstance();
        fields = signupMenuMethods.sortFields(fields);
        if (fields == null) {
            System.out.println("you inserted an invalid field!");
            return;
        }
        if (signupMenuMethods.checkEmptyFields(fields) != null) {
            System.out.println("you left " + signupMenuMethods.checkEmptyFields(fields) + " field empty!");
            return;
        }
    }
}
