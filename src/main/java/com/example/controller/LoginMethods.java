package com.example.controller;

public class LoginMethods {
    private static LoginMethods loginMethods;

    public static LoginMethods getInstance() {
        return loginMethods == null ? loginMethods = new LoginMethods() : loginMethods;
    }

}
