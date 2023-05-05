package com.example.controller.Methods;

import com.example.controller.Commands.SignupMenuCommands;
import com.example.model.CapthaCode;
import com.example.model.RandomSlogan;
import com.example.model.UsersData;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;

public class SignupMenuMethods implements RandomSlogan {
    private static SignupMenuMethods instance;
    private final UsersData usersData;
    private final GlobalMethods globalMethods;

    private SignupMenuMethods() {
        usersData = UsersData.getUsersData();
        globalMethods = GlobalMethods.getInstance();
    }

    public static SignupMenuMethods getInstance() {
        return instance == null ? instance = new SignupMenuMethods() : instance;
    }

    public ArrayList<String> sortFields(ArrayList<String> fields) {
        ArrayList<String> output = new ArrayList<>();
        output.add("a");
        output.add("b");
        output.add("c");
        output.add("d");
        output.add("e");
        for (String field : fields) {
            if (field.trim().length() < 4) {
                return null;
            }
            String quoteSubstring = field.trim().substring(4, field.trim().length() - 1);
            boolean isQuoted = field.trim().charAt(3) == '\"' && field.trim().endsWith("\"");
            if (field.startsWith("-u")) {
                if (isQuoted)
                    output.add(0, quoteSubstring);
                else
                    output.add(0, field.substring(3).trim());
            } else if (field.startsWith("-p")) {
                if (isQuoted)
                    output.add(1, quoteSubstring);
                else
                    output.add(1, field.substring(3).trim());
            } else if (field.startsWith("-e")) {
                if (isQuoted)
                    output.add(2, quoteSubstring);
                else
                    output.add(2, field.substring(3).trim());
            } else if (field.startsWith("-n")) {
                if (isQuoted)
                    output.add(3, quoteSubstring);
                else
                    output.add(3, field.substring(3).trim());
            } else if (field.startsWith("-s")) {
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


    public boolean usernameValidation(String username) {
        return username.matches("\\w+");
    }

    public String getRecoveryQuestion(Scanner scanner) {
        int error;
        Matcher matcher;
        System.out.println("""
                pick a security question:1. What is my father’s name?
                2. What was my first pet’s name?
                3. What is my mother’s last name?""");
        String recoveryQuestion = scanner.nextLine();
        while (true) {
            if (!(matcher = SignupMenuCommands.getMatcher(recoveryQuestion, SignupMenuCommands.SECURITY_QUESTION)).find()) {
                System.out.println("your answer is not in valid format! please answer again.");
                recoveryQuestion = scanner.nextLine();
            } else if (sortSecurityQuestionFields(globalMethods.commandSplit(matcher.group("fields"))) == null) {
                System.out.println("you entered an invalid field. please answer again.");
                recoveryQuestion = scanner.nextLine();
            } else if (!sortSecurityQuestionFields(globalMethods.commandSplit(matcher.group("fields"))).get(0).matches("\\d")) {
                System.out.println("you should enter a number in -q field. please answer again");
                recoveryQuestion = scanner.nextLine();
            } else if ((error = globalMethods.checkEmptyFields(sortSecurityQuestionFields(globalMethods.commandSplit(matcher.group("fields"))))) != -1) {
                if (error == 1)
                    System.out.println("you left answer field empty. please answer again.");
                if (error == 2)
                    System.out.println("you left answer confirm field empty. please answer again.");
                recoveryQuestion = scanner.nextLine();
            } else break;
        }
        String confirm = sortSecurityQuestionFields(globalMethods.commandSplit(matcher.group("fields"))).get(2);
        while (true) {
            if (!confirm.equals(sortSecurityQuestionFields(globalMethods.commandSplit(matcher.group("fields"))).get(1))) {
                System.out.println("your confirmed security question is not correct. please submit again:");
                confirm = scanner.nextLine();
            } else break;
        }
        return sortSecurityQuestionFields(globalMethods.commandSplit(matcher.group("fields"))).get(0) + " " + confirm;
    }

    public void register(String username, String password, String nickname, String email, String slogan, int recoveryQuestionNumber, String recoveryAnswer) {
        usersData.addUser(username, password, nickname, email, slogan, recoveryQuestionNumber, recoveryAnswer);
    }

    private ArrayList<String> sortSecurityQuestionFields(ArrayList<String> fields) {
        ArrayList<String> output = new ArrayList<>(3);
        for (String field : fields) {
            String quoteSubstring = field.trim().substring(3, field.trim().length() - 1);
            boolean isQuoted = field.trim().charAt(3) == '\"' && field.trim().endsWith("\"");
            if (field.startsWith("-q")) {
                if (isQuoted)
                    output.add(0, quoteSubstring);
                else output.add(0, field.substring(3).trim());
            } else if (field.startsWith("-a")) {
                if (isQuoted)
                    output.add(1, quoteSubstring);
                else output.add(1, field.substring(3).trim());
            } else if (field.startsWith("-c")) {
                if (isQuoted)
                    output.add(2, quoteSubstring);
                else output.add(2, field.substring(3).trim());
            } else return null;
        }
        return output;
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

    public boolean captchaCheck(Scanner scanner) {
        System.out.println(CapthaCode.generateCapthaCode());
        String answer = scanner.nextLine();
        return CapthaCode.isCodeCorrect(answer);
    }

    public String getRandomSlogan() {
        Random random = new Random();
        int index = random.nextInt(10);
        return RandomSlogan.RANDOM_SLOGANS[index];
    }
}
