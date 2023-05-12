package com.example.controller.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMenuCommands {
    SHOW_MAP("^show map (-x (?<xCoordinate>\\d+) -y (?<yCoordinate>\\d+))|(-y (?<yCoordinate2>\\d+) -x (?<xCoordinate2>\\d+))$"),
    MOVE_MAP("^map (?<directionOne>\\S+)(?<directionOneNumber> \\d+)?( (?<directionTwo>\\S+)(?<directionTwoNumber> \\d+)?)?$"),
    SHOW_DETAILS("^show details (-x (?<xCoordinate>\\d+) -y (?<yCoordinate>\\d+))|(-y (?<yCoordinate2>\\d+) -x (?<xCoordinate2>\\d+))$");
    private final String regex;
    MapMenuCommands(String regex) {
        this.regex = regex;
    }
    public static Matcher getMatcher(String input, MapMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        return pattern.matcher(input);
    }
}