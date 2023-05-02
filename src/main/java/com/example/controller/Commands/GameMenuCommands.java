package com.example.controller.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    // TODO: omidreza should change this regex using command split
    SHOW_MAP ("^show map (<fields>-\\w \\d+)$")
    ;
    private String regex;
    GameMenuCommands(String regex) {
        this.regex = regex;
    }
    public static Matcher getMatcher(String input, GameMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        return pattern.matcher(input);
    }
}
