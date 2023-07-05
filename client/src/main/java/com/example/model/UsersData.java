package com.example.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.controller.NetworkController;
import com.google.gson.Gson;

public class UsersData implements WriteInFile {
    private User seeProfileUser;
    private static UsersData usersData;
//    private ArrayList<User> users;
    private User loggedInUser;

    private UsersData() {
        readFromFile();
    }

    public void readFromFile() {
//        users = new ArrayList<>(initializeUsers());
    }

    public static UsersData getInstance() {
        return usersData == null ? usersData = new UsersData() : usersData;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void logout() {
        loggedInUser = null;
    }

    public void addUser(String username, String password, String nickname, String email, String slogan, int recoveryQuestionNumber, String recoveryAnswer) {
        NetworkController.getInstance().transferData(new Request(UsersData.class, "addUser", username, password, nickname,
                email, slogan, recoveryQuestionNumber, recoveryAnswer));
//        users.add(new User(username, password, nickname, email, slogan, recoveryQuestionNumber, recoveryAnswer));
    }

    public User getUserByUsername(String username) {
        return (User) NetworkController.getInstance().transferData(new Request(UsersData.class, "getUserByUsername", username));
    }

    public void request(String requestUser, String acceptUser) {
        NetworkController.getInstance().transferData(new Request(UsersData.class, "request", requestUser, acceptUser));
    }

//    public boolean doesEmailExist(String email) {
//        for (User user : users)
//            if (user.getEmail().equalsIgnoreCase(email)) return true;
//        return false;
//    }

    public boolean isPasswordCorrect(String username, String password) {
        User user = getUserByUsername(username);
        return user.isPasswordCorrect(password);
    }

    private List<User> initializeUsers() {
        Gson gson = new Gson();
        File main = new File("src", "main");
        File resources = new File(main, "resources");
        File json = new File(resources, "json");
        File users = new File(json, "Users.json");
        try {
            users.createNewFile();
        } catch (IOException e) {
            System.err.println("Can't create new file!!!");
        }
        try {
            Scanner scanner = new Scanner(users);
            String information = scanner.nextLine();
            scanner.close();
            return List.of(gson.fromJson(information, User[].class));
        } catch (FileNotFoundException e) {
            System.err.println("The file doesn't exist!!!");
        }
        return null;
    }

//    public ArrayList<User> getUsers() {
//        return users;
//    }

    @Override
    public void writeInFile() {
//        Gson gson = new Gson();
//        File main = new File("src", "main");
//        File resources = new File(main, "resources");
//        File json = new File(resources, "json");
//        File users = new File(json, "Users.json");
//        try {
//            FileWriter fileWriter = new FileWriter(users);
//            fileWriter.write(gson.toJson(this.users));
//            fileWriter.close();
//        } catch (IOException e) {
//            System.err.println("Can't write in file!!!");
//        }
    }

    public User getSeeProfileUser() {
        return seeProfileUser;
    }

    public void setSeeProfileUser(User seeProfileUser) {
        this.seeProfileUser = seeProfileUser;
    }
}
