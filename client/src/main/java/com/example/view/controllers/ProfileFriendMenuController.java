package com.example.view.controllers;

import com.example.model.User;
import com.example.model.UsersData;
import com.example.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ProfileFriendMenuController {
    private final UsersData usersData = UsersData.getInstance();
    private final User user = usersData.getSeeProfileUser();
    @FXML
    private VBox friendVBox;
    @FXML
    private VBox requestVBox;

    @FXML
    public void initialize() {
        setFriendVBox();
        setRequestVBox();
    }

    private void setFriendVBox() {
        for (User user1 : user.getFriends()) {
            friendVBox.getChildren().add(new Label(user1.getUsername()));
        }
    }

    private void setRequestVBox() {
        for (User requestUser : user.getRequests()) {
            requestVBox.getChildren().add(new Label(requestUser.getUsername()));
        }
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        Main.goToMenu("MainMenu");
    }
}
