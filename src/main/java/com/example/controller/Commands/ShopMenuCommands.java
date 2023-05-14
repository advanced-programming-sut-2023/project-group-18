package com.example.controller.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ShopMenuCommands {
    SHOW_PRICE_LIST("^show price list$"),
    BUY("^buy ((-i (?<name>(\\S+)|(\".+\")) -a (?<amount>\\d+))|(-a (?<amount2>\\d+) -i (?<name2>(\\S+)|(\".+\"))))$"),
    SELL("^sell ((-i (?<name>(\\S+)|(\".+\")) -a (?<amount>\\d+))|(-a (?<amount2>\\d+) -i (?<name2>(\\S+)|(\".+\"))))$");
    private final String regex;
    ShopMenuCommands(String regex) {
        this.regex = regex;
    }
    public static Matcher getMatcher(String input, ShopMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        return pattern.matcher(input);
    }
}
