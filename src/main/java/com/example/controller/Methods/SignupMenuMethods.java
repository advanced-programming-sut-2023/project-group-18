package com.example.controller.Methods;

import com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.ArrayList;

public class SignupMenuMethods {
    private static SignupMenuMethods instance;
    private SignupMenuMethods() {
    }
    public static SignupMenuMethods getInstance() {
        if (instance == null) {
            instance = new SignupMenuMethods();
        }
        return instance;
    }
    public ArrayList<String> sortFields(ArrayList<String> fields) {
        ArrayList<String> output = new ArrayList<>();
        for (String field : fields) {
            String quoteSubstring = field.trim().substring(4, field.trim().length() - 1);
            boolean isQuoted = field.trim().charAt(3) == '\"' && field.trim().endsWith("\"");
            if (field.startsWith("-u")) {
                if (isQuoted)
                    output.add(0, quoteSubstring);
                else
                    output.add(0, field.substring(3).trim());
            } else if (field.startsWith("-p") && output.size() == 1) {
                if (isQuoted)
                    output.add(1, quoteSubstring);
                else
                    output.add(1, field.substring(3).trim());
            } else if (field.startsWith("-e") && output.size() == 2) {
                if (isQuoted)
                    output.add(2, quoteSubstring);
                else
                    output.add(2, field.substring(3).trim());
            } else if (field.startsWith("-n") && output.size() == 3) {
                if (isQuoted)
                    output.add(3, quoteSubstring);
                else
                    output.add(3, field.substring(3).trim());
            } else if (field.startsWith("-s") && output.size() == 4) {
                if (isQuoted)
                    output.add(4, quoteSubstring);
                else
                    output.add(4, field.substring(3).trim());
            } else {
                return null;
            }
        }
        return output;
    }

    public String checkEmptyFields(ArrayList<String> fields) {            /*these fields are sorted*/
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).equals("")) {
                if (i == 0) {
                    return "username";
                } else if (i == 1) {
                    return "password";
                } else if (i == 2) {
                    return "email";
                } else if (i == 3) {
                    return "nickname";
                } else if (i == 4) {
                    return "slogan";
                }
            }
        }
        return null;
    }

    public boolean usernameValidation(String username) {
        return username.matches("\\w+");
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
