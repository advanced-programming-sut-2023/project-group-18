package com.example.view;

import java.io.IOException;
import java.net.URL;

import com.example.model.UsersData;
import com.example.view.images.Images;
import com.example.view.music.Music;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage stage;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Main.stage = stage;
        goToMenu("signupMenu");
        Music.LOGIN_MENU.getMediaPlayer().play();
        stage.setScene(scene);
        stage.setTitle("Stronghold Crusader");
        closeApp();
        setIcon();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage() {
        return stage;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void goToMenu(String name) throws IOException {
        URL url = Main.class.getResource("/fxml/" + name + ".fxml");
        Parent root = FXMLLoader.load(url);
        if (scene == null) scene = new Scene(root);
        else scene.setRoot(root);
        scene.setCursor(new ImageCursor(Images.ICON.getImage()));
    }

    private void closeApp() {
        stage.setOnCloseRequest(event -> UsersData.getUsersData().writeUsersInFile());
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