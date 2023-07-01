package com.example.client.controller;

import com.example.server.model.UsersData;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class StartGameMethods {
    private static StartGameMethods startGameMethods;
    public static StartGameMethods getInstance() {
        return startGameMethods == null ? startGameMethods = new StartGameMethods() : startGameMethods;
    }
    private final UsersData usersData = UsersData.getUsersData();

    public boolean checkStartPlayers(ArrayList<TextField> usernames) {
        for (TextField textField : usernames)
            if (usersData.getUserByUsername(textField.getText()) == null ||
                    usersData.getUserByUsername(textField.getText()).equals(usersData.getLoggedInUser()))
                return false;
        return true;
    }
}
