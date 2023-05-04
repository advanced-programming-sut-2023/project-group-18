package com.example.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

public class UsersData {
    private static UsersData usersData;
    private final ArrayList<User> users;
    private User loggedInUser;

    private UsersData() {
        users = new ArrayList<>(initializeUsers());
    }

    public static UsersData getUsersData() {
        return usersData == null ? usersData = new UsersData() : usersData;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public User getStayLoggedInUser() {
        File main = new File("src", "main");
        File resources = new File(main, "resources");
        File json = new File(resources, "json");
        File users = new File(json, "stayLoggedInUserId.txt");
        try (Scanner scanner = new Scanner(users)) {
            String username = scanner.nextLine();
            return getUserByUsername(username);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setStayLoggedInUser(User stayLoggedInUser) {
        writeStayLoggedInUserIdInFile(stayLoggedInUser);
    }

    public void logout() {
        loggedInUser = null;
        writeStayLoggedInUserIdInFile(loggedInUser);
    }

    private void writeStayLoggedInUserIdInFile(User user) {
        File main = new File("src", "main");
        File resources = new File(main, "resources");
        File json = new File(resources, "json");
        File users = new File(json, "stayLoggedInUserId.txt");
        try {
            FileWriter fileWriter = new FileWriter(users);
            String username = user.getUsername();
            username = username == null ? "?" : username;
            fileWriter.write(username);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Can't write in file!!!");
        }
    }

    public void writeUsersInFile() {
        Gson gson = new Gson();
        File main = new File("src", "main");
        File resources = new File(main, "resources");
        File json = new File(resources, "json");
        File users = new File(json, "Users.json");
        try {
            FileWriter fileWriter = new FileWriter(users);
            fileWriter.write(gson.toJson(this.users));
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Can't write in file!!!");
        }
    }

    public void addUser(String username, String password, String nickname, String email, String slogan, int recoveryQuestionNumber, String recoveryAnswer) {
        users.add(new User(username, password, nickname, email, slogan, recoveryQuestionNumber, recoveryAnswer));
    }

    public User getUserByUsername(String username) {
        for (User user : users)
            if (user.getUsername().equals(username)) return user;
        return null;
    }

    public boolean doesEmailExist(String email) {
        for (User user : users)
            if (user.getEmail().equalsIgnoreCase(email)) return true;
        return false;
    }

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


}
