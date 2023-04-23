package com.example.controller;

import com.example.model.User;
import com.example.view.SignupMenu;

public class SignupController {
    private static SignupController signupController;

    private SignupController() {

    }

    public static SignupController getSignupController() {
        return signupController == null ? signupController = new SignupController() : signupController;
    }

    public void run() {
        SignupMenu signupMenu = SignupMenu.getSignupMenu();
        signupMenu.run();
        register("fraxea", "omidreza#Sad", "rezoo", "reza@rezoo.com", null, 0, "WTF?!");
        User.writeUsersInFile();
    }

    private void register(String username, String password, String nickname, String email, String slogan, int recoveryQuestionNumber, String recoveryAnswer) {
        User.getUsers().add(new User(username, password, nickname, email, slogan, recoveryQuestionNumber, recoveryAnswer));
    }

}
