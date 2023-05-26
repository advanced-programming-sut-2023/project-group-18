package com.example.view.controllers;

import com.example.view.Main;

import java.io.IOException;

public class LoginMenuController {
    public void back() throws IOException {
        Main.goToMenu("signupMenu");
    }
}
