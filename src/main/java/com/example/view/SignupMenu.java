package com.example.view;

import com.example.controller.Commands.SignupMenuCommands;
import com.example.controller.Methods.GlobalMethods;
import com.example.controller.Methods.SignupMenuMethods;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class SignupMenu {
    private static SignupMenu instance;
    private final SignupMenuMethods signupMenuMethods;

    private SignupMenu() {
        signupMenuMethods = SignupMenuMethods.getInstance();
    }

    public static SignupMenu getInstance() {
        if (instance == null) {
            instance = new SignupMenu();
        }
        return instance;
    }

    public void run(Scanner scanner) {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if ((matcher = SignupMenuCommands.getMatcher(input, SignupMenuCommands.CREATE_USER)).find()) {
                register(matcher, scanner);
            } else if (SignupMenuCommands.getMatcher(input, SignupMenuCommands.EXIT).find()) {
                break;
            } else {
                GlobalMethods.invalidCommand();
            }
        }
    }

    private void register(Matcher matcher, Scanner scanner) {
        ArrayList<String> fields = GlobalMethods.commandSplit(matcher.group("fields"));
        fields = signupMenuMethods.sortFields(fields);
        if (fields == null) {
            System.out.println("you inserted an invalid field!");
            return;
        } else if (GlobalMethods.checkEmptyFields(fields) != -1) {
            String error = "";
            switch (GlobalMethods.checkEmptyFields(fields)) {
                case 0 -> error = "username";
                case 1 -> error = "password";
                case 2 -> error = "email";
                case 3 -> error = "nickname";
                case 4 -> error = "slogan";
            }
            System.out.println("you left " + error + " field empty!");
            return;
        }
        String username = fields.get(0);
        String password = fields.get(1);
        String email = fields.get(2);
        String slogan = fields.get(4).equals("a") ? "" : fields.get(4);
        if (signupMenuMethods.doesUsernameExist(username)) {
            System.out.println("this username already exists!");
            return;
        } else if (signupMenuMethods.doesEmailExist(email)) {
            System.out.println("this email already exists!");
            return;
        }
        if (!signupMenuMethods.usernameValidation(username)) {
            System.out.println("your username should consist of letters, digits and underline");
            return;
        } else if (signupMenuMethods.passwordValidation(password) != null) {
            System.out.println("your password is not valid.\nreason: " + signupMenuMethods.passwordValidation(password));
            return;
        } else if (!signupMenuMethods.emailValidation(email)) {
            System.out.println("your email format is invalid");
            return;
        }
        System.out.println("Please re-enter your password here:");
        if (!scanner.nextLine().equals(password)) {
            System.out.println("your re-entered password was incorrect!");
            return;
        }
        String recoveryQuestion = signupMenuMethods.getRecoveryQuestion(scanner);      /*this is number and the question together*/
        while (!signupMenuMethods.captchaCheck(scanner)) {
            System.out.println("you didn't answer captcha correctly! try again.");
        }
        int recoveryQuestionNumber = Integer.parseInt(recoveryQuestion.substring(0, 1));
        if (recoveryQuestionNumber > 3 || recoveryQuestionNumber < 1) {
            System.out.println("the question number is not valid. please register again!");
            return;
        }
        recoveryQuestion = recoveryQuestion.substring(2);
        System.out.println("register successful");
        signupMenuMethods.register(username, password, fields.get(3), email, slogan, recoveryQuestionNumber, recoveryQuestion);
    }
}
