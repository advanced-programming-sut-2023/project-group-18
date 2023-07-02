package com.example.controller;

import com.example.model.UsersData;

public class LoginController {
    private final UsersData usersData = UsersData.getUsersData();
    private static LoginController loginController;

    public static LoginController getInstance() {
        return loginController == null ? loginController = new LoginController() : loginController;
    }

    private boolean doesUsernameExist(String username) {
        return usersData.getUserByUsername(username) != null;
    }

    public boolean validLogin(String username, String password) {
        return doesUsernameExist(username) && usersData.isPasswordCorrect(username, password);
    }

    public void login(String username) {
        usersData.setLoggedInUser(usersData.getUserByUsername(username));
    }
}
