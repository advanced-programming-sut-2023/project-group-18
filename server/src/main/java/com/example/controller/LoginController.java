package com.example.controller;

import com.example.model.BooleanWrapper;
import com.example.model.UsersData;

public class LoginController {
    private final UsersData usersData = UsersData.getInstance();
    private static LoginController loginController;

    public static LoginController getInstance() {
        return loginController == null ? loginController = new LoginController() : loginController;
    }

    private BooleanWrapper doesUsernameExist(String username) {
        System.out.println("username in loginController " + username);
        return new BooleanWrapper(usersData.getUserByUsername(username) != null);
    }

    public synchronized BooleanWrapper validLogin(String username, String password) {
        return new BooleanWrapper(doesUsernameExist(username).isValue() && usersData.isPasswordCorrect(username, password));
    }

    public void login(String username) {
        usersData.addLoggedInUsers(usersData.getUserByUsername(username));
    }
}
