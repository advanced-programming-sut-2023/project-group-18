package com.example.controller.Methods;

import com.example.model.UsersData;

import java.util.ArrayList;

public class GlobalMethods {
    private static GlobalMethods globalMethods;
    private final UsersData usersData;
    private GlobalMethods() {
        usersData = UsersData.getUsersData();
    }
    public static GlobalMethods getInstance() {
        return globalMethods == null ? globalMethods = new GlobalMethods() : globalMethods;
    }
    public ArrayList<String> commandSplit(String command) {
        int counter = 0;
        boolean passedFirstDash = false;
        StringBuilder temp = new StringBuilder();
        ArrayList<String> output = new ArrayList<>();
        for (char character : command.toCharArray()) {
            if (character == '\"') {
                counter++;
            }
            if (character == '-') {
                if (!passedFirstDash) {
                    passedFirstDash = true;
                }
                else if (counter % 2 == 0) {
                    output.add(temp.toString());
                    temp = new StringBuilder();
                }
            }
            temp.append(character);
        }
        output.add(temp.toString());
        return output;
    }

    public void invalidCommand() {
        System.out.println("invalid command!");
    }

    public int checkEmptyFields(ArrayList<String> fields) {            /*these fields are sorted*/
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).equals("")) {
                return i;
            }
        }
        return -1;
    }

    public boolean doesUsernameExist(String username) {
        return usersData.getUserByUsername(username) != null;
    }

    public boolean doesEmailExist(String email) {
        return usersData.doesEmailExist(email);
    }

    public boolean checkPassword(String username, String password) {
        return usersData.isPasswordCorrect(username, password);
    }

    public String passwordValidation(String password) {
        if (password.length() < 6)
            return "password must contain at least six characters";
        else if (!password.matches(".*[a-z].*"))
            return "password must contain at least one lowercase letter";
        else if (!password.matches(".*[A-Z].*"))
            return "password must contain at least one uppercase letter";
        else if (!password.matches(".*\\d.*"))
            return "password must contain at least one digit";
        else if (!password.matches(".*[^\\sa-zA-Z0-9].*"))
            return "password must contain at least one character not being letter and digit";
        return null;
    }

    public boolean emailValidation(String email) {
        return email.matches("[\\w.]+@[\\w.]+\\.[\\w.]+");
    }
}
