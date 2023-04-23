package com.example.view;

public class SignupMenu {
    private static SignupMenu signupMenu;

    private SignupMenu() {

    }

    public static SignupMenu getSignupMenu() {
        return signupMenu == null ? signupMenu = new SignupMenu() : signupMenu;
    }

    public void run() {
    }
    
}
