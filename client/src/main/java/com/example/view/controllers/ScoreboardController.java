package com.example.view.controllers;

import com.example.model.User;
import com.example.model.UsersData;
import com.example.view.Main;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class ScoreboardController {
    private static final int PLAYERS_PER_PAGE = 100;
    private final UsersData usersData = UsersData.getUsersData();
    @FXML
    private Button backButton;
    @FXML
    private Label label;
    private ArrayList<User> users;
    @FXML
    private VBox playerLabels;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    public void initialize() {
        // TODO: need to use server
        // getScoreboard(pageNumber)
        users = (ArrayList<User>) usersData.getUsers().clone();
        users.sort((user1, user2) -> -Integer.compare(user1.getHighscore(), user2.getHighscore()));
        updatePlayerLabels(0);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(40);
        vBox.getChildren().add(label);
        vBox.getChildren().add(backButton);
        vBox.getChildren().add(playerLabels);
        scrollPane.setContent(vBox);
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

    public void back(MouseEvent mouseEvent) throws IOException {
        Main.goToMenu("ProfileMenu");
    }
}
