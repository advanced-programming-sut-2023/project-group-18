package com.example.server;

import com.example.server.controller.NetworkController;

public class Server {
    public static void main(String[] args) {
        NetworkController networkController = NetworkController.getInstance();
        networkController.run();
    }
}
