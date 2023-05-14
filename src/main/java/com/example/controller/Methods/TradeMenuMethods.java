package com.example.controller.Methods;

import java.util.HashMap;
import java.util.regex.Matcher;

public class TradeMenuMethods {
    private static TradeMenuMethods tradeMenuMethods;

    private TradeMenuMethods() {
    }

    public static TradeMenuMethods getTradeMenuMethods() {
        return tradeMenuMethods == null ? tradeMenuMethods = new TradeMenuMethods() : tradeMenuMethods;
    }

    public boolean checkTradeInvalidField(HashMap<String, String> fields) {
        for (String string : fields.keySet()) {
            if (!string.trim().equals("-t") && !string.trim().equals("-a") && !string.trim().equals("-p") && !string.trim().equals("-m"))
                return false;
        }
        return fields.size() == 4;
    }

    public int getId(Matcher matcher) {
        if (matcher.group("id") != null) {
            return Integer.parseInt(matcher.group("id"));
        }
        return Integer.parseInt(matcher.group("id2"));
    }

    public String getMessage(Matcher matcher) {
        if (matcher.group("message") != null) {
            boolean isQuoted = matcher.group("message").trim().charAt(3) == '\"' && matcher.group("message").trim().endsWith("\"");
            if (isQuoted) {
                return matcher.group("message").trim().substring(4, matcher.group("message").trim().length() - 1);
            }
            return matcher.group("message");
        }
        boolean isQuoted = matcher.group("message2").trim().charAt(3) == '\"' && matcher.group("message2").trim().endsWith("\"");
        if (isQuoted) {
            return matcher.group("message2").trim().substring(4, matcher.group("message2").trim().length() - 1);
        }
        return matcher.group("message2");
    }
}
