package com.example.controller;

import com.example.model.UsersData;
import com.example.view.SignupMenu;

public class SignupController {
    private static SignupController signupController;
    private final UsersData usersData;

    private SignupController() {
        usersData = UsersData.getUsersData();
    }

    public static SignupController getSignupController() {
        return signupController == null ? signupController = new SignupController() : signupController;
    }

    public void run() {
        SignupMenu signupMenu = SignupMenu.getSignupMenu();
        signupMenu.run();
        register("fraxea", "omidreza#Sad", "rezoo", "reza@rezoo.com", null, 0, "WTF?!");
        usersData.writeUsersInFile();
    }

    private void register(String username, String password, String nickname, String email, String slogan, int recoveryQuestionNumber, String recoveryAnswer) {
        usersData.addUser(username, password, nickname, email, slogan, recoveryQuestionNumber, recoveryAnswer);
    }

}
