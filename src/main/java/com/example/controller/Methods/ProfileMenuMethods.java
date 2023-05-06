package com.example.controller.Methods;

import com.example.model.User;
import com.example.model.UsersData;

import java.util.ArrayList;

public class ProfileMenuMethods {
    private static ProfileMenuMethods profileMenuMethods;
    private ProfileMenuMethods() {
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
}
