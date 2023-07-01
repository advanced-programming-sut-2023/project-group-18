package com.example.client.view;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import com.example.client.controller.NetworkController;
import com.example.server.model.Game;
import com.example.server.model.UsersData;
import com.example.client.view.images.Images;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {
    private static Stage stage;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Client.stage = stage;
        goToMenu("GameMenu");
//        Music.LOGIN_MENU.getMediaPlayer().play();
        stage.setScene(scene);
        stage.setTitle("Stronghold Crusader");
        closeApp();
        setIcon();
        stage.show();
    }

    public static void main(String[] args) {
        NetworkController networkController = NetworkController.getInstance();
        networkController.initializeNetwork();
        launch();
        networkController.terminateNetwork();
    }

    public static Stage getStage() {
        return stage;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void goToMenu(String name) throws IOException {
        URL url = Client.class.getResource("/fxml/" + name + ".fxml");
        Parent root = FXMLLoader.load(url);
        if (scene == null) scene = new Scene(root);
        else scene.setRoot(root);
        // scene.setCursor(new ImageCursor(Images.CURSOR.getImage()));
    }

    private void closeApp() {
        stage.setOnCloseRequest(event -> {
            UsersData.getUsersData().writeInFile();
            if (Game.getInstance().getGameMap() != null)
                Game.getInstance().getGameMap().writeInFile();
        });
    }

    private void setIcon() {
        stage.getIcons().clear();
        stage.getIcons().add(Images.ICON.getImage());
    }

}
/* main method for PHASE 1
Scanner scanner = new Scanner(System.in);
UsersData usersData = UsersData.getUsersData();
if (usersData.getStayLoggedInUser() == null) {
    LoginMenu loginMenu = LoginMenu.getInstance();
    loginMenu.run(scanner);
} else {
    usersData.setLoggedInUser(usersData.getStayLoggedInUser());
    ProfileMenu profileMenu = ProfileMenu.getInstance();
    profileMenu.run(scanner);
}
UsersData.getUsersData().writeUsersInFile(); */