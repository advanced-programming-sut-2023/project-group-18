package com.example.view;

import com.example.controller.Commands.ProfileMenuCommands;
import com.example.controller.Methods.GlobalMethods;
import com.example.controller.Methods.LoginMenuMethods;
import com.example.controller.Methods.ProfileMenuMethods;
import com.example.model.User;
import com.example.model.UsersData;

import java.util.ArrayList;
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
                changeUsername(matcher);
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
                LoginMenu loginMenu = LoginMenu.getInstance();
                loginMenu.run(scanner);
                break;
            } else {
                globalMethods.invalidCommand();
            }
        }
    }

    private void changeUsername(Matcher matcher) {
        String username = matcher.group("username");
        if (!profileMenuMethods.usernameValidation(username)) {
            System.out.println("your username doesn't have a valid format!");
            return;
        } else if (globalMethods.doesUsernameExist(username)) {
            System.out.println("this username is occupied!");
            return;
        }
    }

    private void changeNickname(Matcher matcher) {
        String nickname = matcher.group("nickname");
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
        //TODO check old password
        if (globalMethods.passwordValidation(newPass) != null) {
            System.out.println("your password is not valid.\nreason: " + globalMethods.passwordValidation(newPass));
            return;
        } else if (oldPass.equals(newPass)) {
            System.out.println("Please enter a new password!");
            return;
        }
        //TODO set new password
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
        //TODO set email
        System.out.println("your email changed to " + email + " successfully.");
        return;
    }

    private void changeSlogan(Matcher matcher) {
        String slogan = matcher.group("slogan");
        //TODO set slogan?
        System.out.println("your slogan changed successfully.");
    }
    
    private void removeSlogan() {
        //TODO remove slogan
        System.out.println("removed slogan successfully.");
    }

    private void displayHighscore() {

    }

    private void displayRank() {

    }

    private void displaySlogan() {

    }

    private void displayProfile() {

    }
}
