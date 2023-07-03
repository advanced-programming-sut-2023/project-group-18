package com.example.view.controllers;

import com.example.controller.NetworkController;
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
    private final NetworkController networkController = NetworkController.getInstance();
    private final UsersData usersData = UsersData.getInstance();
    @FXML
    private Label sloganText;
    @FXML
    private Label slogan;
    @FXML
    private ImageView avatar4;
    @FXML
    private ImageView avatar3;
    @FXML
    private ImageView avatar2;
    @FXML
    private ImageView avatar1;
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
        avatar1.setImage(new Image(ProfileMenuController.class.getResource("/avatars/1.png").toExternalForm()));
        avatar2.setImage(new Image(ProfileMenuController.class.getResource("/avatars/2.png").toExternalForm()));
        avatar3.setImage(new Image(ProfileMenuController.class.getResource("/avatars/3.png").toExternalForm()));
        avatar4.setImage(new Image(ProfileMenuController.class.getResource("/avatars/4.png").toExternalForm()));
        username.setText(currentUser.getUsername());
        nickname.setText(currentUser.getNickname());
        email.setText(currentUser.getEmail());
        if (currentUser.getSlogan() == null) {
            slogan.setVisible(false);
            sloganText.setVisible(false);
        }
        slogan.setText(currentUser.getSlogan());
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
            newPassword.setText("");
            oldPassword.setText("");
        }
    }

    public void changeSlogan(MouseEvent mouseEvent) {
        if (newSlogan.getText().equals("")) sloganError.setText(EMPTY_FIELD);
        else {
            currentUser.setSlogan(newSlogan.getText());
            newSlogan.setText("");
        }
    }

    public void removeSlogan(MouseEvent mouseEvent) {
        currentUser.setSlogan(newSlogan.getText());
    }

    public void chooseAvatar(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Jpg Files", "*.jpg"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(Main.getStage());
        usersData.getLoggedInUser().setAvatar(selectedFile);
        avatar.setImage(new Image(selectedFile.toURI().toString()));
    }

    public void scoreboard(MouseEvent mouseEvent) throws IOException {
        Main.goToMenu("Scoreboard");
    }

    public void set1(MouseEvent mouseEvent) {
        currentUser.setAvatar(new File(ProfileMenuController.class.getResource("/avatars/1.png").toExternalForm()));
        avatar.setImage(new Image(ProfileMenuController.class.getResource("/avatars/1.png").toExternalForm()));
    }

    public void set2(MouseEvent mouseEvent) {
        currentUser.setAvatar(new File(ProfileMenuController.class.getResource("/avatars/2.png").toExternalForm()));
        avatar.setImage(new Image(ProfileMenuController.class.getResource("/avatars/2.png").toExternalForm()));
    }

    public void set3(MouseEvent mouseEvent) {
        currentUser.setAvatar(new File(ProfileMenuController.class.getResource("/avatars/3.png").toExternalForm()));
        avatar.setImage(new Image(ProfileMenuController.class.getResource("/avatars/3.png").toExternalForm()));
    }

    public void set4(MouseEvent mouseEvent) {
        currentUser.setAvatar(new File(ProfileMenuController.class.getResource("/avatars/4.png").toExternalForm()));
        avatar.setImage(new Image(ProfileMenuController.class.getResource("/avatars/4.png").toExternalForm()));
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        Main.goToMenu("MainMenu");
    }
}
