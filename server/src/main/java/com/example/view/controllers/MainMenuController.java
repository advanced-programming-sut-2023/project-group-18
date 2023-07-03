package com.example.view.controllers;

import com.example.model.UsersData;
import com.example.view.Main;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MainMenuController {

    public void profileMenu(MouseEvent mouseEvent) throws IOException {
        Main.goToMenu("ProfileMenu");
    }

    public void startGame(MouseEvent mouseEvent) throws IOException {
        Main.goToMenu("StartGameMenu");
    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        Main.goToMenu("SignupMenu");
        UsersData.getInstance().addLoggedInUsers(null);
    }
}
