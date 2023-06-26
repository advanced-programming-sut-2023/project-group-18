package com.example.view.controllers;

import com.example.model.User;
import com.example.model.UsersData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ScoreboardController {
    private static final int PLAYERS_PER_PAGE = 10;
    private final UsersData usersData = UsersData.getUsersData();
    private ArrayList<User> users;
    @FXML
    private VBox playerLabels;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    public void initialize() {
        // Initialize the list of players
        users = (ArrayList<User>) usersData.getUsers().clone();
        users.sort(methods::compare);
        users.add(usersData.getUsers().get(0));
        users.add(usersData.getUsers().get(1));
        users.add(usersData.getUsers().get(2));
//        players.add(new Player("Player 1", 100));
//        players.add(new Player("Player 2", 90));
//        players.add(new Player("Player 3", 80));
        // Add more players...

        updatePlayerLabels(0);

        // Create the scroll pane
        scrollPane.setContent(playerLabels);
        scrollPane.setPrefViewportHeight(300);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.vvalueProperty().addListener((observable, oldValue, newValue) -> {
            int pageIndex = (int) Math.floor(newValue.doubleValue() * users.size() / PLAYERS_PER_PAGE);
            updatePlayerLabels(pageIndex);
        });
    }

    private void updatePlayerLabels(int pageIndex) {
        playerLabels.getChildren().clear();
        int startIndex = pageIndex * PLAYERS_PER_PAGE;
        int endIndex = Math.min(startIndex + PLAYERS_PER_PAGE, users.size());
        for (int i = startIndex; i < endIndex; i++) {
            User user = users.get(i);
            Label label = new Label(String.format("%s: %d", user.getUsername(), user.getHighscore()));
            playerLabels.getChildren().add(label);
        }
    }
}
