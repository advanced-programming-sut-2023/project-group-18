package com.example.view.controllers;

import com.example.controller.NetworkController;
import com.example.model.Request;
import com.example.model.User;
import com.example.model.UsersData;
import com.example.model.UsersWrapper;
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

public class ScoreboardMenuController {
    private static final int PLAYERS_PER_PAGE = 100;
    private final UsersData usersData = UsersData.getInstance();
    @FXML
    private Button backButton;
    @FXML
    private Label label;
    private ArrayList<User> users;
    private ArrayList<User> onlineUsers;
    @FXML
    private VBox playerLabels;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    public void initialize() {
        System.out.println("trying to initialize scoreboard");
        // TODO: need to use server
        // getScoreboard(pageNumber)
        users = ((UsersWrapper) NetworkController.getInstance().transferData(new Request(UsersData.class, "getWrappedUsers"))).getUserArrayList();
        onlineUsers = ((UsersWrapper) NetworkController.getInstance().transferData(new Request(UsersData.class, "getWrappedOnlineUsers"))).getUserArrayList();
        System.out.println("online players size is: " + onlineUsers.size());
        System.out.println("the online player is: " + onlineUsers.get(0).getUsername());
//        users = (ArrayList<User>) usersData.getUsers().clone();
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
//            Label label = new Label(String.format("%s: %d", user.getUsername(), user.getHighscore()));
            Label label = new Label();
            if (isOnline(user.getUsername()))
                label.setText(user.getUsername() + ": " + user.getHighscore() + "  online");
            else label.setText(user.getUsername() + ": " + user.getHighscore() + "  offline");
            playerLabels.getChildren().add(label);
        }
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        Main.goToMenu("ProfileMenu");
    }

    public boolean isOnline(String username) {
        for (User onlineUser : onlineUsers) {
            if (username.equals(onlineUser.getUsername()))
                return true;
        }
        return false;
    }
}
