package com.example.controller.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {
    CHANGE_USERNAME("^profile change -u (?<username>\\S+)$"),
    CHANGE_NICKNAME("^profile change -n (?<nickname>\\S+)$"),
    CHANGE_PASSWORD("^profile change password (?<fields>(-[a-zA-Z] .+){2})$"),
    CHANGE_EMAIL("^profile change -e (?<email>\\S+)$"),
    CHANGE_SLOGAN("^profile change slogan -s (?<slogan>\\S+)$"),
    REMOVE_SLOGAN("^profile remove slogan$"),
    DISPLAY_HIGHSCORE("^profile display highscore$"),
    DISPLAY_RANK("^profile display rank$"),
    DISPLAY_SLOGAN("^profile display slogan$"),
    DISPLAY_PROFILE("^profile display$"),
    CURRENT_MENU("^show current menu$"),
    LOGOUT("^logout$"),
    EXIT("^exit$");
    private final String regex;
    ProfileMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, ProfileMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        return pattern.matcher(input);
    }
}
