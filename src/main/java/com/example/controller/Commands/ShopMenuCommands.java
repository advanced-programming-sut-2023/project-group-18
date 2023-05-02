package com.example.controller.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ShopMenuCommands {
    private String regex;
    ShopMenuCommands(String regex) {
        this.regex = regex;
    }
    public static Matcher getMatcher(String input, ShopMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        return pattern.matcher(input);
    }
}
