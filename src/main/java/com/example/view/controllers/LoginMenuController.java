package com.example.view.controllers;

import com.example.controller.LoginMethods;
import com.example.view.Main;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.util.Duration;

import java.io.IOException;

public class LoginMenuController {
    private final LoginMethods loginMethods = LoginMethods.getInstance();
    @FXML
    private BorderPane borderPane;

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

    public void login(MouseEvent mouseEvent) throws InterruptedException, IOException {
        Popup popup = new Popup();
        Label label = new Label("username or password is incorrect");
//        if (loginMethods.validLogin(username.getText(), password.getText()))
//            label.setText("you logged in successfully");
        label.setFont(new Font(20));
        popup.getContent().add(label);
//        if (!popup.isShowing())
        if (!loginMethods.validLogin(username.getText(), password.getText()))
            popup.show(Main.getStage());
        else
            Main.goToMenu("signupMenu");
//            popup.hide();
//        wait(1000);
//        popup.hide();
//        if (!loginMethods.doesUsernameExist(username.getText())) {
//
//        }
    }
}
