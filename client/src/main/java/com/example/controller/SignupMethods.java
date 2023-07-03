package com.example.controller;

import com.example.controller.responses.SignupResponses;
import com.example.model.*;

import java.util.ArrayList;

public class SignupMethods implements SignupResponses, RandomSlogan {
    private final NetworkController networkController = NetworkController.getInstance();
    private static SignupMethods controller;
    private final UsersData usersData;

    private SignupMethods() {
        usersData = UsersData.getInstance();
    }

    public static SignupMethods getInstance() {
        return controller == null ? controller = new SignupMethods() : controller;
    }


    private boolean isUsernameValid(String username) {
        return username.matches("\\w+");
    }

    public void register(String username, String password, String nickname, String email, String slogan, int recoveryQuestionNumber, String recoveryAnswer) {
        usersData.addUser(username, password, nickname, email, slogan, recoveryQuestionNumber, recoveryAnswer);
    }

    public String generateRandomPassword() {
        java.util.Random random = new java.util.Random();
        int length = random.nextInt(6, 9);
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "123456789";
        String characters = "!@#$%^&*";
        String all = uppercaseLetters + lowercaseLetters + digits + characters;
        StringBuilder password = new StringBuilder();
        password.append(uppercaseLetters.charAt(random.nextInt(uppercaseLetters.length())));
        password.append(lowercaseLetters.charAt(random.nextInt(lowercaseLetters.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(characters.charAt(random.nextInt(characters.length())));
        for (int i = 4; i < length; i++) {
            password.append(all.charAt(random.nextInt(all.length())));
        }
        return password.toString();
    }

    // TODO: need to use server
    public BooleanWrapper doesUsernameExist(String username) {
        return (BooleanWrapper) networkController.transferData(new Request(LoginController.class, "doesUsernameExist", username));
//        return usersData.getUserByUsername(username) != null;
    }

    // TODO: need to use server
    public BooleanWrapper doesEmailExist(String email) {
        return (BooleanWrapper) networkController.transferData(new Request(SignupMethods.class, "doesEmailExist", email));
//        return usersData.doesEmailExist(email);
    }

    private boolean isEmailValid(String email) {
        return email.matches("[\\w.]+@[\\w.]+\\.[\\w.]+");
    }

    public String getRandomSlogan() {
        java.util.Random random = new java.util.Random();
        int index = random.nextInt(10);
        return RandomSlogan.RANDOM_SLOGANS[index];
    }

    public String getUsernameError(String username) {
        if (!isUsernameValid(username)) return USERNAME_INVALID;
        if (doesUsernameExist(username).isValue()) return USERNAME_EXIST;
        return null;
    }

    public String getPasswordError(String password) {
        if (password.length() < 6)
            return PASSWORD_SHORT;
        else if (!password.matches(".*[a-z].*"))
            return PASSWORD_LOWER_CASE;
        else if (!password.matches(".*[A-Z].*"))
            return PASSWORD_UPPER_CASE;
        else if (!password.matches(".*\\d.*"))
            return PASSWORD_DIGIT;
        else if (!password.matches(".*[^\\sa-zA-Z0-9].*"))
            return PASSWORD_START;
        return null;
    }

    public String getEmailError(String email) {
        if (!isEmailValid(email)) return EMAIL_VALID;
        if (doesEmailExist(email).isValue()) return EMAIL_EXIST;
        return null;
    }

    // TODO: need to use server
    public String getPopularSlogan() {
        boolean flag = false;
        ArrayList<User> users = UsersData.getInstance().getUsers();
        ArrayList<String> slogans = new ArrayList<>();
        ArrayList<Integer> occurrences = new ArrayList<>();
        for (User user : users) {
            for (int i = 0; i < slogans.size(); i++) {
                if (slogans.get(i).equals(user.getSlogan())) {
                    occurrences.set(i, occurrences.get(i) + 1);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                slogans.add(user.getSlogan());
                occurrences.add(1);
            }
            flag = false;
        }
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < occurrences.size(); i++) {
            if (occurrences.get(i) > max) {
                max = occurrences.get(i);
                maxIndex = i;
            }
        }
        return slogans.get(maxIndex);
    }
}
