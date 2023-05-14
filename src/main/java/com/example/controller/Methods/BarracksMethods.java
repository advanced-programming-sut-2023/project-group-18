package com.example.controller.Methods;

import com.example.view.KeepMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class BarracksMethods implements SelectBuildingMenuMethods{
    private static BarracksMethods barracksMethods;
    private BarracksMethods() {

    }
    public static BarracksMethods getBarracksMethods() {
        return barracksMethods == null ? barracksMethods = new BarracksMethods() : barracksMethods;
    }
    @Override
    public void run(Scanner scanner) {
        BarracksMethods.getBarracksMethods().run(scanner);
    }
    
    public String getType(Matcher matcher) {
        String string = matcher.group("type");
        String string2 = matcher.group("type2");
        if (matcher.group("type") != null) {
            boolean isQuoted = string.trim().charAt(0) == '\"' && string.trim().endsWith("\"");
            if (isQuoted) {
                return string.trim().substring(1, string.trim().length() - 1);
            }
            return matcher.group("type");
        }
        boolean isQuoted = string2.trim().charAt(0) == '\"' && string2.trim().endsWith("\"");
        if (isQuoted) {
            return string2.trim().substring(1, string2.trim().length() - 1);
        }
        return matcher.group("type2");
    }
    
    public String getCount(Matcher matcher) {
        if (matcher.group("count") != null)
            return matcher.group("count");
        return matcher.group("count2");
    }
}
