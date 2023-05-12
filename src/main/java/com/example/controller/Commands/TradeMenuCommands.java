package com.example.controller.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeMenuCommands {
    TRADE("^trade (?<fields>( -\\S \\S+){4})$"),
    TRADE_LIST("^trade list$"),
    ACCEPT_TRADE("^trade accept ((-i (?<id>\\d+) -m (?<message>(\\S+)|(\".+\")))|(-m (?<message2>(\\S+)|(\".+\")) -i (?<id2>\\d+)))$"),
    TRADE_HISTORY("^trade history$");
    private final String regex;
    TradeMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, TradeMenuCommands command) {
        Pattern pattern = Pattern.compile(command.regex);
        return pattern.matcher(input);
    }
}
