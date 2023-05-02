package com.example.controller.Methods;

public class ProfileMenuMethods {
    private static ProfileMenuMethods profileMenuMethods;
    private ProfileMenuMethods() {
    }

    public static ProfileMenuMethods getInstance() {
        return profileMenuMethods == null ? profileMenuMethods = new ProfileMenuMethods() : profileMenuMethods;
    }
}
