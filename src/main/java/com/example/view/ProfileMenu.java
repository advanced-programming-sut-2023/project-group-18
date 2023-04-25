package com.example.view;

import com.example.controller.Methods.LoginMenuMethods;
import com.example.controller.Methods.ProfileMenuMethods;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu {
    private static ProfileMenu profileMenu;
    private final ProfileMenuMethods profileMenuMethods;
    private ProfileMenu() {
        profileMenuMethods = ProfileMenuMethods.getInstance();
    }
    public ProfileMenu getProfileMenu() {
        return profileMenu == null ? profileMenu = new ProfileMenu() : profileMenu;
    }

    public void run(Scanner scanner) {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
        }
    }
}
