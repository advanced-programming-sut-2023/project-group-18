package com.example.view;

import com.example.controller.NetworkController;

import javafx.stage.Stage;

public class Main {
    public static void main(String[] args) {
        NetworkController networkController = NetworkController.getInstance();
        networkController.run();
    }

    public static Stage getStage() {
        return null;
    }

    public static void goToMenu(String string) {
    }
}