package com.example.controller;

import com.example.model.BooleanWrapper;
import com.example.model.UsersData;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

public class LoginController {
    private final UsersData usersData = UsersData.getUsersData();
    private static LoginController loginController;

    public static LoginController getInstance() {
        return loginController == null ? loginController = new LoginController() : loginController;
    }

    private BooleanWrapper doesUsernameExist(String username) {
        System.out.println("username in loginController " + username);
        return new BooleanWrapper(usersData.getUserByUsername(username) != null);
    }

    public boolean validLogin(String username, String password) {
        return doesUsernameExist(username).isValue() && usersData.isPasswordCorrect(username, password);
    }

    public void login(String username) {
        usersData.setLoggedInUser(usersData.getUserByUsername(username));
    }
}
