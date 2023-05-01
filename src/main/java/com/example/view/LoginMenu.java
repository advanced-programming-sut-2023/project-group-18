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
                login(matcher, scanner);
            } else if ((matcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.FORGOT_PASS)).find()) {
                forgotPassword(matcher, scanner);
            } else if (LoginMenuCommands.getMatcher(input, LoginMenuCommands.SIGNUP_MENU).find()) {
                SignupMenu signupMenu = SignupMenu.getInstance();
                signupMenu.run(scanner);
                break;
            } else {
                GlobalMethods.invalidCommand();
            }
        }
    }

    private void login(Matcher matcher, Scanner scanner) {
        ArrayList<String> fields = GlobalMethods.commandSplit(matcher.group("fields"));
        fields = loginMenuMethods.sortLoginFields(fields);
        if (fields == null) {
            System.out.println("you inserted and invalid field!");
            return;
        } else if (GlobalMethods.checkEmptyFields(fields) != -1) {
            String error = "";
            switch (GlobalMethods.checkEmptyFields(fields)) {
                case 0 -> error = "username";
                case 1 -> error = "password";
            }
            System.out.println("you left " + error + " field empty!");
            return;
        }
        String username = fields.get(0);
        String password = fields.get(1);
        if (!(globalMethods.doesUsernameExist(username))) {
            System.out.println("this username doesn't exist!");
            return;
        } else if (!globalMethods.checkPassword(username, password)) {
            // the method is not coded
            System.out.println("your entered password is false!");
            return;
        }
        if (matcher.group("stayLoggedIn") != null) {
            loginMenuMethods.stayLoggedIn();
        }
    }

    private void forgotPassword(Matcher matcher, Scanner scanner) {
        // sout security question
        String answer = scanner.nextLine();
        if (!loginMenuMethods.checkSecurityQuestion(answer)) {
        }
        // getting new password
        String newPassword = scanner.nextLine();
        loginMenuMethods.setNewPassword(newPassword);
    }
}
