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
    private final GlobalMethods globalMethods;

    private SignupMenu() {
        signupMenuMethods = SignupMenuMethods.getInstance();
        globalMethods = GlobalMethods.getInstance();
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
            } else if (SignupMenuCommands.getMatcher(input, SignupMenuCommands.LOGIN_MENU).find()) {
                LoginMenu loginMenu = LoginMenu.getInstance();
                loginMenu.run(scanner);
                break;
            } else if (SignupMenuCommands.getMatcher(input, SignupMenuCommands.CURRENT_MENU).find()) {
                globalMethods.showCurrentMenu("signup menu");
            } else {
                globalMethods.invalidCommand();
            }
        }
    }

    private void register(Matcher matcher, Scanner scanner) {
        ArrayList<String> fields = globalMethods.commandSplit(matcher.group("fields"));
        fields = signupMenuMethods.sortFields(fields);
        boolean randomPasswordFlag = false;
        if (fields == null) {
            System.out.println("you inserted an invalid field!");
            return;
        } else if (globalMethods.checkEmptyFields(fields) != -1) {
            String error = "";
            switch (globalMethods.checkEmptyFields(fields)) {
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
        if (password.equals("random")) {
            password = signupMenuMethods.generateRandomPassword();
            randomPasswordFlag = true;
        }
        if (slogan.equals("random")) {
            slogan = signupMenuMethods.getRandomSlogan();
        }
        while (globalMethods.doesUsernameExist(username)) {
            System.out.println("this username already exists!");
            username = globalMethods.getNewUsername(username, scanner);
            if (username.equals("0"))
                return;
        }
        if (globalMethods.doesEmailExist(email)) {
            System.out.println("this email already exists!");
            return;
        }
        if (!signupMenuMethods.usernameValidation(username)) {
            System.out.println("your username should consist of letters, digits and underline");
            return;
        } else if (globalMethods.passwordValidation(password) != null) {
            System.out.println("your password is not valid.\nreason: " + globalMethods.passwordValidation(password));
            return;
        } else if (!globalMethods.emailValidation(email)) {
            System.out.println("your email format is invalid");
            return;
        }
        if (!randomPasswordFlag) {
            System.out.println("Please re-enter your password here:");
            if (!scanner.nextLine().equals(password)) {
                System.out.println("your re-entered password was incorrect!");
                return;
            }
        } else if (!signupMenuMethods.checkRandomPassword(password, scanner)) {
            System.out.println("you didn't re-enter password correctly!");
            return;
        }
        String recoveryQuestion = signupMenuMethods.getRecoveryQuestion(scanner);      /*this is number and the question together*/
        while (!globalMethods.captchaCheck(scanner)) {
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
