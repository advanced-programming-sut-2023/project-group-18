package com.example.client.controller;

import com.example.client.model.UsersData;

public class LoginMethods {
    private final UsersData usersData = UsersData.getUsersData();
    private static LoginMethods loginMethods;

    public static LoginMethods getInstance() {
        return loginMethods == null ? loginMethods = new LoginMethods() : loginMethods;
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
