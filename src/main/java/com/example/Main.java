package com.example;

import com.example.controller.SignupController;

public class Main {
    public static void main(String[] args) {
        SignupController signupController = SignupController.getSignupController();
        signupController.run();
    }
}