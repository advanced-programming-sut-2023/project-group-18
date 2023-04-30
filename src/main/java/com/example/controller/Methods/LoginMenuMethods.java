package com.example.controller.Methods;

import java.util.ArrayList;

public class LoginMenuMethods {
    private static LoginMenuMethods loginMenuMethods;
    private LoginMenuMethods() {
    }

    public static LoginMenuMethods getInstance() {
        return loginMenuMethods == null ? loginMenuMethods = new LoginMenuMethods() : loginMenuMethods;
    }
    public ArrayList<String> sortLoginFields(ArrayList<String> fields) {
        return null;
    }
}
