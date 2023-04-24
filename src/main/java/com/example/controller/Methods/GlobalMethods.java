package com.example.controller.Methods;

import java.util.ArrayList;

public class GlobalMethods {
    public static ArrayList<String> commandSplit(String command) {
        int counter = 0;
        boolean passedFirstDash = false;
        StringBuilder temp = new StringBuilder();
        ArrayList<String> output = new ArrayList<>();
        for (char character : command.toCharArray()) {
            if (character == '\"') {
                counter++;
            }
            if (character == '-') {
                if (!passedFirstDash) {
                    passedFirstDash = true;
                }
                else if (counter % 2 == 0) {
                    output.add(temp.toString());
                    temp = new StringBuilder();
                }
            }
            temp.append(character);
        }
        output.add(temp.toString());
        return output;
    }

    public static void invalidCommand() {
        System.out.println("invalid command!");
    }
}
