package com.example.controller.Methods;

import com.example.model.User;
import com.example.model.UsersData;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenuMethods {
    private static ProfileMenuMethods profileMenuMethods;
    private final UsersData usersData;

    private ProfileMenuMethods() {
        usersData = UsersData.getUsersData();
    }

    public static ProfileMenuMethods getInstance() {
        return profileMenuMethods == null ? profileMenuMethods = new ProfileMenuMethods() : profileMenuMethods;
    }

    public boolean usernameValidation(String username) {
        return username.matches("\\w+");
    }

    public ArrayList<String> sortFields(ArrayList<String> fields) {
        ArrayList<String> output = new ArrayList<>();
        output.add("a");
        output.add("b");
        for (String field : fields) {
            if (field.trim().length() < 4) {
                return null;
            }
            String quoteSubstring = field.trim().substring(4, field.trim().length() - 1);
            boolean isQuoted = field.trim().charAt(3) == '\"' && field.trim().endsWith("\"");
            if (field.startsWith("-o")) {
                if (isQuoted)
                    output.add(0, quoteSubstring);
                else
                    output.add(0, field.substring(3).trim());
            } else if (field.startsWith("-n")) {
                if (isQuoted)
                    output.add(1, quoteSubstring);
                else
                    output.add(1, field.substring(3).trim());
            } else return null;
        }
        return output;
    }

    public boolean checkPassword(String password) {
        return UsersData.getUsersData().getLoggedInUser().isPasswordCorrect(password);
    }

    public void setNewPassword(String password) {
        UsersData.getUsersData().getLoggedInUser().setPassword(password);
    }

    public void setNewEmail(String email) {
        UsersData.getUsersData().getLoggedInUser().setEmail(email);
    }

    public void setNewSlogan(String slogan) {
        UsersData.getUsersData().getLoggedInUser().setSlogan(slogan);
    }

    public void removeSlogan() {
        UsersData.getUsersData().getLoggedInUser().setSlogan("");
    }

    public String[] getUsernames(Matcher matcher) {
        String allUsernames = matcher.group("users").trim();
        return allUsernames.split(" ");
    }

    public String checkUsers(String[] usernames) {
        for (String user : usernames) {
            if (usersData.getUserByUsername(user) == null || usersData.getLoggedInUser().equals(usersData.getUserByUsername(user))) {
                return user;
            }
        }
        return null;
    }

    public String getMapSize(Scanner scanner) {
        System.out.println("what size should the map be? type normal or large");
        String size = scanner.nextLine();
        while (!size.equals("normal")  && !size.equals("large")) {
            System.out.println("the entered size is not valid. please type again.");
            size = scanner.nextLine();
        }
        return size;
    }

    public ArrayList<User> getPlayers(String[] usernames) {
        ArrayList<User> players = new ArrayList<>();
        for (String username : usernames) {
            players.add(usersData.getUserByUsername(username));
        }
        players.add(usersData.getLoggedInUser());
        return players;
    }
}