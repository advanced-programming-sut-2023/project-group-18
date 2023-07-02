package com.example.controller;

import com.example.model.Request;

public class LoginController {
    private final NetworkController networkController;
    private static LoginController loginController;

    private LoginController() {
        networkController = NetworkController.getInstance();
    }

    public static LoginController getInstance() {
        return loginController == null ? loginController = new LoginController() : loginController;
    }

    // TODO: need to use server
    public boolean validLogin(String username, String password) {
        return (boolean) networkController.transferData(new Request("LoginController", "validLogin",
                username, password));
        // return doesUsernameExist(username) && usersData.isPasswordCorrect(username, password);
    }

    // TODO: need to use server
    public void login(String username) {
        networkController.transferData(new Request("LoginController", "login",
                username));
    }
}
