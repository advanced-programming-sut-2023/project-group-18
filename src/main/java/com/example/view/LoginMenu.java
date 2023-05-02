package com.example.view;

import com.example.controller.Commands.LoginMenuCommands;
import com.example.controller.Methods.GlobalMethods;
import com.example.controller.Methods.LoginMenuMethods;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
    private static LoginMenu instance;
    private final LoginMenuMethods loginMenuMethods;
    private final GlobalMethods globalMethods;

    private LoginMenu() {
        loginMenuMethods = LoginMenuMethods.getInstance();
        globalMethods = GlobalMethods.getInstance();
    }

    public static LoginMenu getInstance() {
        return instance == null ? instance = new LoginMenu() : instance;
    }

    public void run(Scanner scanner) {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if ((matcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.LOGIN_USER)).find()) {
                if (login(matcher, scanner)) {
                    ProfileMenu profileMenu = ProfileMenu.getInstance();
                    profileMenu.run(scanner);
                    break;
                }
            } else if ((matcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.FORGOT_PASS)).find()) {
                forgotPassword(matcher, scanner);
            } else if (LoginMenuCommands.getMatcher(input, LoginMenuCommands.SIGNUP_MENU).find()) {
                SignupMenu signupMenu = SignupMenu.getInstance();
                signupMenu.run(scanner);
                break;
            } else if (LoginMenuCommands.getMatcher(input, LoginMenuCommands.CURRENT_MENU).find()) {
                globalMethods.showCurrentMenu("login menu");
            } else {
                globalMethods.invalidCommand();
            }
        }
    }

    private boolean login(Matcher matcher, Scanner scanner) {
        ArrayList<String> fields = globalMethods.commandSplit(matcher.group("fields"));
        fields = loginMenuMethods.sortLoginFields(fields);
        if (fields == null) {
            System.out.println("you inserted an invalid field!");
            return false;
        } else if (globalMethods.checkEmptyFields(fields) != -1) {
            String error = "";
            switch (globalMethods.checkEmptyFields(fields)) {
                case 0 -> error = "username";
                case 1 -> error = "password";
            }
            System.out.println("you left " + error + " field empty!");
            return false;
        }
        String username = fields.get(0);
        String password = fields.get(1);
        if (!(globalMethods.doesUsernameExist(username))) {
            System.out.println("this username doesn't exist!");
            return false;
        } else if (!globalMethods.checkPassword(username, password)) {
            System.out.println("your entered password is false!");
            return false;
        }
        if (matcher.group("stayLoggedIn") != null) {
            loginMenuMethods.stayLoggedIn(username);
        }
        System.out.println("you logged in successfully.");
        return true;
    }

    private void forgotPassword(Matcher matcher, Scanner scanner) {
//        // sout security question
//        String answer = scanner.nextLine();
//        if (!loginMenuMethods.checkSecurityQuestion(answer)) {
//        }
//        // getting new password
//        String newPassword = scanner.nextLine();
//        loginMenuMethods.setNewPassword(newPassword);
    }
}
