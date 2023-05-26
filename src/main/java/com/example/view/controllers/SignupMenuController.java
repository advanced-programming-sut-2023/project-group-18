package com.example.view.controllers;

import com.example.controller.SignupController;
import com.example.controller.responses.FieldResponses;
import com.example.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignupMenuController implements FieldResponses {
    private final SignupController controller = SignupController.getInstance();
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField email;
    @FXML
    private TextField nickname;
    @FXML
    private Label usernameError;
    @FXML
    private Label passwordError;
    @FXML
    private Label emailError;
    @FXML
    private Label nicknameError;
    
    
    @FXML
    public void initialize() {
        addListeners();
    }
    
    private void addListeners() {
        username.textProperty().addListener((observable, oldValue, newValue) ->
            usernameError.setText(controller.getUsernameError(newValue))
        );
        password.textProperty().addListener((observable, oldValue, newValue) ->
            passwordError.setText(controller.getPasswordError(newValue))
        );
        email.textProperty().addListener((observable, oldValue, newValue) ->
            emailError.setText(controller.getEmailError(newValue))
        );
        nickname.textProperty().addListener((observable, oldValue, newValue) ->
            nicknameError.setText(null)
        );
    }

    public void submit() {
        if (username.getText() == null) usernameError.setText(EMPTY_FIELD);
        else if (password.getText() == null) passwordError.setText(EMPTY_FIELD);
        else if (email.getText() == null) emailError.setText(EMPTY_FIELD);
        else if (nickname.getText() == null) nicknameError.setText(EMPTY_FIELD);
        // TODO: else go next Menu
    }
    
    public void goLoginMenu() throws IOException {
        Main.goToMenu("loginMenu");
    }
}
