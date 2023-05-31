package com.example.view.controllers;

import com.example.controller.LoginMethods;
import com.example.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LoginMenuController {
    private final LoginMethods loginMethods = LoginMethods.getInstance();
    @FXML
    private Label usernameError;
    @FXML
    private Label passwordError;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;

    @FXML
    public void initialize() {
    }
    public void back() throws IOException {
        Main.goToMenu("signupMenu");
    }

    public void login(MouseEvent mouseEvent) {

    }
}
