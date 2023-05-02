package com.example.controller.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMenuCommands {
    private String regex;
    MapMenuCommands(String regex) {
        this.regex = regex;
    }
    public static Matcher getMatcher(String input, MapMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        return pattern.matcher(input);
    }
}
