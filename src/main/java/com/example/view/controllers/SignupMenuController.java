package com.example.view.controllers;

import com.example.controller.SecurityMethods;
import com.example.controller.SignupMethods;
import com.example.controller.responses.FieldResponses;
import com.example.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class SignupMenuController implements FieldResponses {
    private final SignupMethods signupMethods = SignupMethods.getInstance();
    @FXML
    private Label sloganError;
    @FXML
    private Button randomSloganButton;
    @FXML
    private TextField slogan;
    @FXML
    private CheckBox showSloganCheckBox;
    @FXML
    private TextField passwordTextField;
    @FXML
    private CheckBox showPasswordCheckBox;
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
        slogan.managedProperty().bind(showSloganCheckBox.selectedProperty());
        slogan.visibleProperty().bind(showSloganCheckBox.selectedProperty());
        randomSloganButton.managedProperty().bind(showSloganCheckBox.selectedProperty());
        randomSloganButton.visibleProperty().bind(showSloganCheckBox.selectedProperty());

        passwordTextField.managedProperty().bind(showPasswordCheckBox.selectedProperty());
        passwordTextField.visibleProperty().bind(showPasswordCheckBox.selectedProperty());
        passwordTextField.textProperty().bindBidirectional(password.textProperty());
        addListeners();
    }

    private void addListeners() {
        username.textProperty().addListener((observable, oldValue, newValue) ->
                usernameError.setText(signupMethods.getUsernameError(newValue))
        );
        password.textProperty().addListener((observable, oldValue, newValue) ->
                passwordError.setText(signupMethods.getPasswordError(newValue))
        );
        email.textProperty().addListener((observable, oldValue, newValue) ->
                emailError.setText(signupMethods.getEmailError(newValue))
        );
        nickname.textProperty().addListener((observable, oldValue, newValue) ->
                nicknameError.setText(null)
        );
        slogan.textProperty().addListener((observable, oldValue, newValue) ->
                sloganError.setText(null)
        );
    }

    public void submit() throws IOException {
        if (username.getText().equals("")) usernameError.setText(EMPTY_FIELD);
        else if (password.getText().equals("")) passwordError.setText(EMPTY_FIELD);
        else if (email.getText().equals("")) emailError.setText(EMPTY_FIELD);
        else if (nickname.getText().equals("")) nicknameError.setText(EMPTY_FIELD);
        else if (slogan.getText().equals("") && showSloganCheckBox.isSelected())
            sloganError.setText(EMPTY_FIELD);
        else {
            SecurityMethods.getInstance().setTempUser(username.getText(), password.getText(),
                    email.getText(), nickname.getText(), slogan.getText());
            Main.goToMenu("SecurityMenu");
        }
    }

    public void goLoginMenu() throws IOException {
        Main.goToMenu("loginMenu");
    }

    public void changeVisibility() {
        password.setManaged(!password.isManaged());
        password.setVisible(!password.isVisible());
    }

    public void generateRandomPassword() {
        passwordTextField.setText(SignupMethods.getInstance().generateRandomPassword());
    }

    public void randomSlogan() {
        slogan.setText(signupMethods.getRandomSlogan());
    }
}
