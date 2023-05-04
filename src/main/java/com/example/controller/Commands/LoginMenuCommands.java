package com.example.controller.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    LOGIN_USER("^user login(?<fields>( -[a-zA-Z] ((\\S+)|(\".+\"))){2})( ?<stayLoggedIn>--stay-logged-in)?$"),
    FORGOT_PASS("^forgot my password -u (?<username>.+)$"),
    SIGNUP_MENU("^signup menu$"),
    CURRENT_MENU("^show current menu$");
    private final String regex;
    LoginMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, LoginMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        return pattern.matcher(input);
    }
}
