package com.example.view.controllers;

import com.example.controller.SignupController;
import com.example.controller.responses.FieldResponses;
import com.example.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class SignupMenuController implements FieldResponses {
    private final SignupController signupController = SignupController.getInstance();
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
                usernameError.setText(signupController.getUsernameError(newValue))
        );
        password.textProperty().addListener((observable, oldValue, newValue) ->
                passwordError.setText(signupController.getPasswordError(newValue))
        );
        email.textProperty().addListener((observable, oldValue, newValue) ->
                emailError.setText(signupController.getEmailError(newValue))
        );
        nickname.textProperty().addListener((observable, oldValue, newValue) ->
                nicknameError.setText(null)
        );
        slogan.textProperty().addListener((observable, oldValue, newValue) ->
                sloganError.setText(null)
        );
    }

    public void submit() {
        if (username.getText().equals("")) usernameError.setText(EMPTY_FIELD);
        if (password.getText().equals("")) passwordError.setText(EMPTY_FIELD);
        if (email.getText().equals("")) emailError.setText(EMPTY_FIELD);
        if (nickname.getText().equals("")) nicknameError.setText(EMPTY_FIELD);
        if (slogan.getText().equals("") && showSloganCheckBox.isSelected())
            sloganError.setText(EMPTY_FIELD);
        // TODO: else go next Menu
    }

    public void goLoginMenu() throws IOException {
        Main.goToMenu("loginMenu");
    }

    public void changeVisibility() {
        password.setManaged(!password.isManaged());
        password.setVisible(!password.isVisible());
    }

    public void generateRandomPassword() {
        passwordTextField.setText(SignupController.getInstance().generateRandomPassword());
    }

    public void randomSlogan() {
        slogan.setText(signupController.getRandomSlogan());
    }
}
