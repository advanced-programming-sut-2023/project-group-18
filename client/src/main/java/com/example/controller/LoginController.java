package com.example.controller;

import com.example.model.BooleanWrapper;
import com.example.model.Request;
import com.example.model.UsersData;

public class LoginController {
    private final NetworkController networkController;
    private static LoginController loginController;
    private final UsersData usersData = UsersData.getInstance();

    private LoginController() {
        networkController = NetworkController.getInstance();
    }

    public static LoginController getInstance() {
        return loginController == null ? loginController = new LoginController() : loginController;
    }

    // TODO: need to use server
    public BooleanWrapper validLogin(String username, String password) {
        return (BooleanWrapper) networkController.transferData(new Request(LoginController.class, "validLogin",
                username, password));
        // return doesUsernameExist(username) && usersData.isPasswordCorrect(username, password);
    }

    // TODO: need to use server
    public void login(String username) {
        System.out.println("testing loginController.java");
        usersData.readFromFile();
        usersData.setLoggedInUser(usersData.getUserByUsername(username));
        networkController.transferData(new Request(LoginController.class, "login",
                username));
    }
    public BooleanWrapper doesUsernameExist(String username) {
        return (BooleanWrapper) networkController.transferData(new Request(LoginController.class, "doesUsernameExist", username));
//        return usersData.getUserByUsername(username) != null;
    }
}