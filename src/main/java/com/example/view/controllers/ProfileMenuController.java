package com.example.view.controllers;

import com.example.controller.SignupMethods;
import com.example.model.User;
import com.example.model.UsersData;
import com.example.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

import static com.example.controller.responses.FieldResponses.EMPTY_FIELD;

public class ProfileMenuController {
    private final UsersData usersData = UsersData.getUsersData();
    @FXML
    private PasswordField oldPassword;
    @FXML
    private TextField newSlogan;
    @FXML
    private PasswordField newPassword;
    @FXML
    private Label sloganError;
    @FXML
    private Label passwordError;
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
        nickname.setText(currentUser.getNickname());
        email.setText(currentUser.getEmail());
        avatar.setImage(new Image(currentUser.getAvatarPath()));
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
        else if (!currentUser.isPasswordCorrect(oldPassword.getText())) {
            passwordError.setText("incorrect pass");
        } else {
            currentUser.setPassword(newPassword.getText());
        }
    }

    public void changeSlogan(MouseEvent mouseEvent) {
        if (newSlogan.getText().equals("")) sloganError.setText(EMPTY_FIELD);
        else currentUser.setSlogan(newSlogan.getText());
    }

    public void removeSlogan(MouseEvent mouseEvent) {
        currentUser.setSlogan(newSlogan.getText());
    }

    public void chooseAvatar(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Jpg Files", "*.jpg"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Png Files", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(Main.getStage());
        usersData.getLoggedInUser().setAvatar(selectedFile);
        avatar.setImage(new Image(selectedFile.toURI().toString()));
    }

    public void scoreboard(MouseEvent mouseEvent) throws IOException {
        Main.goToMenu("Scoreboard");
    }
}
