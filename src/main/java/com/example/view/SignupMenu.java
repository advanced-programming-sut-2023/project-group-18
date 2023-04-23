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
                register(matcher, scanner);
            } else if (SignupMenuCommands.getMatcher(input, SignupMenuCommands.EXIT).find()) {
                break;
            } else {
                GlobalMethods.invalidCommand();
            }
        }
    }

    private void register(Matcher matcher, Scanner scanner) {
        ArrayList<String> fields = GlobalMethods.commandSplit(matcher.group("fields"));
        SignupMenuMethods signupMenuMethods = SignupMenuMethods.getInstance();
        fields = signupMenuMethods.sortFields(fields);
        if (fields == null) {
            System.out.println("you inserted an invalid field!");
            return;
        } else if (signupMenuMethods.checkEmptyFields(fields) != null) {
            System.out.println("you left " + signupMenuMethods.checkEmptyFields(fields) + " field empty!");
            return;
        }
        String username = fields.get(0);
        String password = fields.get(1);
        String email = fields.get(2);
        if (!signupMenuMethods.usernameValidation(username)) {
            System.out.println("your username should consist of letters, digits and underline");
            return;
        } else if (signupMenuMethods.passwordValidation(password) != null) {
            System.out.println("your password is not valid.\nreason: " + signupMenuMethods.passwordValidation(password));
            return;
        } else if (!signupMenuMethods.emailValidation(email)) {
            System.out.println("your email format is invalid");
            return;
        }
        System.out.println("Please re-enter your password here:");
        if (!scanner.nextLine().equals(password)) {
            System.out.println("your re-entered password ");
        }
        System.out.println("register successful");
    }
}
