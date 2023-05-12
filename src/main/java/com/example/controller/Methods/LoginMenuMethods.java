package com.example.controller.Methods;

import com.example.model.User;
import com.example.model.UsersData;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginMenuMethods {
    private static LoginMenuMethods loginMenuMethods;
    private final UsersData usersData;

    private LoginMenuMethods() {
        usersData = UsersData.getUsersData();
    }

    public static LoginMenuMethods getInstance() {
        return loginMenuMethods == null ? loginMenuMethods = new LoginMenuMethods() : loginMenuMethods;
    }

    public ArrayList<String> sortLoginFields(ArrayList<String> fields) {
        ArrayList<String> output = new ArrayList<>();
        output.add("a");
        output.add("b");
        for (String field : fields) {
            if (field.trim().length() < 4) {
                return null;
            }
            String quoteSubstring = field.trim().substring(4, field.trim().length() - 1);
            boolean isQuoted = field.trim().charAt(3) == '\"' && field.trim().endsWith("\"");
            if (field.trim().startsWith("-u")) {
                if (isQuoted)
                    output.add(0, quoteSubstring);
                else
                    output.add(0, field.substring(3).trim());
            } else if (field.trim().startsWith("-p")) {
                if (isQuoted)
                    output.add(1, quoteSubstring);
                else
                    output.add(1, field.substring(3).trim());
            } else return null;
        }
        return output;
    }

    public void stayLoggedIn(String username) {
        usersData.setStayLoggedInUser(usersData.getUserByUsername(username));
    }

    public boolean checkSecurityQuestion(Scanner scanner, String username) {
        User user = UsersData.getUsersData().getUserByUsername(username);
        System.out.println(user.getRecoveryQuestion());
        String answer = scanner.nextLine();
        return user.isRecoveryAnswerCorrect(answer);
    }

    public String setNewPassword(String newPassword, String username) {
        GlobalMethods globalMethods = GlobalMethods.getInstance();
        User user = UsersData.getUsersData().getUserByUsername(username);
        if (globalMethods.passwordValidation(newPassword) != null) {
            return globalMethods.passwordValidation(newPassword);
        }
        user.setPassword(newPassword);
        return null;
    }
}