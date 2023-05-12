package com.example.view;

import com.example.controller.Commands.ProfileMenuCommands;
import com.example.controller.Methods.GameMenuMethods;
import com.example.controller.Methods.GlobalMethods;
import com.example.controller.Methods.LoginMenuMethods;
import com.example.controller.Methods.ProfileMenuMethods;
import com.example.model.User;
import com.example.model.UsersData;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu {
    private static ProfileMenu profileMenu;
    private final ProfileMenuMethods profileMenuMethods;
    private final GlobalMethods globalMethods;
    private final UsersData usersData;

    private ProfileMenu() {
        profileMenuMethods = ProfileMenuMethods.getInstance();
        globalMethods = GlobalMethods.getInstance();
        usersData = UsersData.getUsersData();
    }

    public static ProfileMenu getInstance() {
        return profileMenu == null ? profileMenu = new ProfileMenu() : profileMenu;
    }

    public void run(Scanner scanner) {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_USERNAME)).find()) {
                changeUsername(matcher, scanner);
            } else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_NICKNAME)).find()) {
                changeNickname(matcher);
            } else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_PASSWORD)).find()) {
                changePassword(matcher);
            } else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_EMAIL)).find()) {
                changeEmail(matcher);
            } else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_SLOGAN)).find()) {
                changeSlogan(matcher);
            } else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.REMOVE_SLOGAN).find()) {
                removeSlogan();
            } else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.DISPLAY_HIGHSCORE).find()) {
                displayHighscore();
            } else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.DISPLAY_RANK).find()) {
                displayRank();
            } else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.DISPLAY_SLOGAN).find()) {
                displaySlogan();
            } else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.DISPLAY_PROFILE).find()) {
                displayProfile();
            } else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CURRENT_MENU).find()) {
                globalMethods.showCurrentMenu("profile menu");
            } else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.LOGOUT).find()) {
                usersData.logout();
                LoginMenu loginMenu = LoginMenu.getInstance();
                loginMenu.run(scanner);
                break;
            } else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.EXIT).find()) {
                break;
            } else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.START_GAME)).find()) {
                if (!startGame(matcher, scanner)) {
                    break;
                }
            } else {
                globalMethods.invalidCommand();
            }
        }
    }

    private void changeUsername(Matcher matcher, Scanner scanner) {
        String username = matcher.group("username");
        if (!profileMenuMethods.usernameValidation(username)) {
            System.out.println("your username doesn't have a valid format!");
            return;
        }
        while (globalMethods.doesUsernameExist(username)) {
            System.out.println("this username already exists!");
            username = globalMethods.getNewUsername(username, scanner);
            if (username.equals("0"))
                return;
        }
        UsersData.getUsersData().getLoggedInUser().setUsername(username);
        System.out.println("your username was changed successfully.");
    }

    private void changeNickname(Matcher matcher) {
        UsersData.getUsersData().getLoggedInUser().setNickname(matcher.group("nickname"));
        System.out.println("nickname changed successfully.");
    }

    private void changePassword(Matcher matcher) {
        ArrayList<String> fields = globalMethods.commandSplit(matcher.group("fields"));
        fields = profileMenuMethods.sortFields(fields);
        if (fields == null) {
            System.out.println("you inserted an invalid field!");
            return;
        } else if (globalMethods.checkEmptyFields(fields) != -1) {
            String error = "";
            switch (globalMethods.checkEmptyFields(fields)) {
                case 0 -> error = "old password";
                case 1 -> error = "new password";
            }
            System.out.println("you left " + error + " field empty!");
            return;
        }
        String oldPass = fields.get(0);
        String newPass = fields.get(1);
        if (!profileMenuMethods.checkPassword(oldPass)) {
            System.out.println("Current password is incorrect!");
        } else if (globalMethods.passwordValidation(newPass) != null) {
            System.out.println("your password is not valid.\nreason: " + globalMethods.passwordValidation(newPass));
            return;
        } else if (oldPass.equals(newPass)) {
            System.out.println("Please enter a new password!");
            return;
        }
        profileMenuMethods.setNewPassword(newPass);
    }

    private void changeEmail(Matcher matcher) {
        String email = matcher.group("email");
        if (!globalMethods.emailValidation(email)) {
            System.out.println("your email's format is not valid!");
            return;
        } else if (usersData.doesEmailExist(email)) {
            System.out.println("this email is already on an account!");
            return;
        }
        profileMenuMethods.setNewEmail(email);
        System.out.println("your email changed to " + email + " successfully.");
    }

    private void changeSlogan(Matcher matcher) {
        String slogan = matcher.group("slogan");
        String quoteSubstring = slogan.trim().substring(4, slogan.trim().length() - 1);
        boolean isQuoted = slogan.trim().charAt(3) == '\"' && slogan.trim().endsWith("\"");
        if (isQuoted) {
            profileMenuMethods.setNewSlogan(quoteSubstring);
        } else
            profileMenuMethods.setNewSlogan(slogan);
        System.out.println("your slogan changed successfully.");
    }

    private void removeSlogan() {
        profileMenuMethods.removeSlogan();
        System.out.println("removed slogan successfully.");
    }

    private void displayHighscore() {
        System.out.println("your highscore is: " + UsersData.getUsersData().getLoggedInUser().getHighscore());
    }

    private void displayRank() {
    }

    private void displaySlogan() {
        String slogan = UsersData.getUsersData().getLoggedInUser().getSlogan();
        if (slogan.equals(""))
            System.out.println("Slogan is empty!");
        else System.out.println("your slogan is: " + slogan);
    }

    private void displayProfile() {
        User user = UsersData.getUsersData().getLoggedInUser();
        System.out.println("your highscore is: " + user.getHighscore());
        ArrayList<User> users = UsersData.getUsersData().getUsers();
        ArrayList<Integer> highscores = new ArrayList<>();
        for (User user1 : users) {
            highscores.add(user1.getHighscore());
        }
        Collections.sort(highscores);
        int rank = highscores.indexOf(user.getHighscore());
        System.out.println("your rank is: " + (rank + 1));
        displaySlogan();
    }

    private boolean startGame(Matcher matcher, Scanner scanner) {
        String[] usernames = profileMenuMethods.getUsernames(matcher);
        String username;
        if ((username = profileMenuMethods.checkUsers(usernames)) != null) {
            System.out.println("user " + username + " is invalid!");
            return false;
        }
        String size = profileMenuMethods.getMapSize(scanner);
        ArrayList<User> players = profileMenuMethods.getPlayers(usernames);
        System.out.print("you entered a game with ");
        for (String user : usernames) {
            System.out.print(user + " ");
        }
        System.out.println();
        GameMenuMethods.gameMenuMethods().run(scanner, players, size);
        return true;
    }
}
