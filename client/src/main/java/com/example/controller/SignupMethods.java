package com.example.controller;

import com.example.controller.responses.SignupResponses;
import com.example.model.RandomSlogan;
import com.example.model.User;
import com.example.model.UsersData;

import java.util.ArrayList;
import java.util.Scanner;

public class SignupMethods implements SignupResponses, RandomSlogan {
    private static SignupMethods controller;
    private final UsersData usersData;

    private SignupMethods() {
        usersData = UsersData.getUsersData();
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

    public boolean checkRandomPassword(String password, Scanner scanner) {
        System.out.println("Your random password is: " + password + "\nPlease re-enter your password here:");
        String confirmPass = scanner.nextLine();
        while (!password.equals(confirmPass)) {
            if (confirmPass.equals("back")) return false;
            System.out.println("your entered password is incorrect. please try again");
            confirmPass = scanner.nextLine();
        }
        return true;
    }

    // TODO: need to use server
    private boolean doesUsernameExist(String username) {
        return usersData.getUserByUsername(username) != null;
    }

    // TODO: need to use server
    private boolean doesEmailExist(String email) {
        return usersData.doesEmailExist(email);
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
        if (doesUsernameExist(username)) return USERNAME_EXIST;
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
        if (doesEmailExist(email)) return EMAIL_EXIST;
        return null;
    }

    // TODO: need to use server
    public String getPopularSlogan() {
        boolean flag = false;
        ArrayList<User> users = UsersData.getUsersData().getUsers();
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
