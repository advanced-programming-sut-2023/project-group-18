package com.example.controller;

import com.example.model.RandomSlogan;
import com.example.model.UsersData;

import java.util.Random;
import java.util.Scanner;

public class SignupController implements RandomSlogan {
    private static SignupController controller;
    private final UsersData usersData;

    private SignupController() {
        usersData = UsersData.getUsersData();
    }

    public static SignupController getInstance() {
        return controller == null ? controller = new SignupController() : controller;
    }


    public boolean usernameValidation(String username) {
        return username.matches("\\w+");
    }

    public void register(String username, String password, String nickname, String email, String slogan, int recoveryQuestionNumber, String recoveryAnswer) {
        usersData.addUser(username, password, nickname, email, slogan, recoveryQuestionNumber, recoveryAnswer);
    }

    public String generateRandomPassword() {
        Random random = new Random();
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

    public boolean doesUsernameExist(String username) {
        return usersData.getUserByUsername(username) != null;
    }

    public boolean doesEmailExist(String email) {
        return usersData.doesEmailExist(email);
    }

    public String getRandomSlogan() {
        Random random = new Random();
        int index = random.nextInt(10);
        return RandomSlogan.RANDOM_SLOGANS[index];
    }

}
