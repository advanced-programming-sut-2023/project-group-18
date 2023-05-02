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
    private LoginMenu() {
        loginMenuMethods = LoginMenuMethods.getInstance();
    }
    public LoginMenu getInstance() {
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
                resetPassword(matcher, scanner);
            }
        }
    }

    private void login(Matcher matcher, Scanner scanner) {
        ArrayList<String> fields = GlobalMethods.commandSplit(matcher.group("fields"));
        fields = loginMenuMethods.sortLoginFields(fields);
        if (fields == null) {
//            Todo sout error
        }
        else if (GlobalMethods.checkEmptyFields(fields) != -1) {
        }
        String username = fields.get(0);
        String password = fields.get(1);
        if (!loginMenuMethods.usernameExist(username)) {

        }
        if (!loginMenuMethods.checkPassword(username, password)) {

        }
        if (matcher.group("stayLoggedIn") != null) {
            loginMenuMethods.stayLoggedIn(username);
        }
    }

    private void resetPassword(Matcher matcher, Scanner scanner) {
        // sout security question
        String answer = scanner.nextLine();
        if (!loginMenuMethods.checkSecurityQuestion(answer)) {
        }
        // getting new password
        String newPassword = scanner.nextLine();
        loginMenuMethods.setNewPassword(newPassword);
    }
}
