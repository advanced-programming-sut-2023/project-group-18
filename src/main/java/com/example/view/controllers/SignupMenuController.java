package com.example.view.controllers;

import com.example.view.Main;
import javafx.fxml.FXML;

import java.io.IOException;

public class SignupMenuController {
    
    @FXML
    public void initialize() {

    }
    
    public void goLoginMenu() throws IOException {
        Main.goToMenu("loginMenu");
    }
}
