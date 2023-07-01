package com.example.client.view.controllers;

import com.example.client.view.Client;
import com.example.server.model.UsersData;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MainMenuController {

    public void profileMenu(MouseEvent mouseEvent) throws IOException {
        Client.goToMenu("ProfileMenu");
    }

    public void startGame(MouseEvent mouseEvent) throws IOException {
        Client.goToMenu("StartGameMenu");
    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        Client.goToMenu("SignupMenu");
        UsersData.getUsersData().setLoggedInUser(null);
    }
}
