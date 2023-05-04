package com.example.controller.Methods;

import com.example.model.UsersData;

import java.util.ArrayList;

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
            if (field.length() < 4) {
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
        System.out.println("setting user stay-logged-in for username: " + username);
        usersData.setStayLoggedInUser(usersData.getUserByUsername(username));
    }
}
