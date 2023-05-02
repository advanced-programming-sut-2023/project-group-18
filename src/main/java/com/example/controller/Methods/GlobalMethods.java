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

    public static int checkEmptyFields(ArrayList<String> fields) {            /*these fields are sorted*/
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).equals("")) {
                return i;
            }
        }
        return -1;
    }
}
