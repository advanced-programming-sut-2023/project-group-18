package com.example.controller.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignupMenuCommands {
    CREATE_USER("^user create (?<fields>(-[a-zA-Z] .+){4,5})$"),
    SECURITY_QUESTION("^question pick (?<fields>(-[a-zA-Z] .+){3})$"),
    LOGIN_MENU("^login menu$"),
    EXIT("exit"),
    CURRENT_MENU("^show current menu$");

    private final String regex;

    SignupMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, SignupMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        return pattern.matcher(input);
    }
}
