package com.example.view.controllers;

import com.example.controller.SignupMethods;
import com.example.model.User;
import com.example.model.UsersData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import static com.example.controller.responses.FieldResponses.EMPTY_FIELD;

public class ProfileMenuController {
    private final UsersData usersData = UsersData.getUsersData();
    @FXML
    private TextField newSlogan;
    @FXML
    private PasswordField newPassword;
    @FXML
    private Label sloganError;
    @FXML
    private Label passwordError;
    @FXML
    private Label password;
    @FXML
    private Label nickname;
    @FXML
    private Label email;
    @FXML
    private ImageView avatar;
    @FXML
    private Label username;
    private final User currentUser = usersData.getLoggedInUser();

    @FXML
    public void initialize() {
        username.setText(currentUser.getUsername());
        password.setText(currentUser.getPassword());
        nickname.setText(currentUser.getNickname());
        email.setText(currentUser.getEmail());
//        avatar.setImage(new Image(currentUser.getAvatarPath()));
        addListeners();
    }

    private void addListeners() {
        newPassword.textProperty().addListener((observable, oldValue, newValue) ->
                passwordError.setText(SignupMethods.getInstance().getPasswordError(newValue))
        );
        newSlogan.textProperty().addListener((observable, oldValue, newValue) ->
                sloganError.setText(null)
        );
    }

    public void changePassword(MouseEvent mouseEvent) {
        if (newPassword.getText().equals("")) passwordError.setText(EMPTY_FIELD);
        else {
            currentUser.setPassword(newPassword.getText());
            password.setText(newPassword.getText());
        }
    }

    public void changeSlogan(MouseEvent mouseEvent) {
        if (newSlogan.getText().equals("")) sloganError.setText(EMPTY_FIELD);
        else currentUser.setSlogan(newSlogan.getText());
    }

    public void removeSlogan(MouseEvent mouseEvent) {
        currentUser.setSlogan(newSlogan.getText());
    }
}
