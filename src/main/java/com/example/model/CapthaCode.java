package com.example.model;

import java.util.Random;
/*
 * HOW TO USE:
 * First call generateCaptchaCode to get the String of a 4-8 digit code
 * Then call isCodeCorrect to check if code is correct
 * EXAMPLE:
    System.out.println(CapthaCode.generateCapthaCode());
    System.out.println(CapthaCode.isCodeCorrect(SCANNER.nextLine()));
 */

public class CapthaCode implements ConsoleColors {
    private static final int LINES = 7;
    private static final Random RANDOM = new Random();
    private static final String NOISE_CHARACTERS = "***..._/\\";
    private static final String[] COLORS = {RED_BOLD, GREEN_BOLD, YELLOW_BOLD, BLUE_BOLD, PURPLE_BOLD, CYAN_BOLD, WHITE_BOLD};
    private static final String[] NUMBERS_IN_ASCII_ART = {
        "  ___   \n / _ \\  \n| | | | \n| | | | \n| |_| | \n \\___/  \n        ",
        " __  \n/_ | \n | | \n | | \n | | \n |_| \n     ",
        "___   \n|__ \\  \n   ) | \n  / /  \n / /_  \n|____| \n       ",
        " ____   \n|___ \\  \n  __) | \n |__ <  \n ___) | \n|____/  \n        ",
        " _  _    \n| || |   \n| || |_  \n|__   _| \n   | |   \n   |_|   \n         ",
        " _____  \n| ____| \n| |__   \n|___ \\  \n ___) | \n|____/  \n        ",
        "   __   \n  / /   \n / /_   \n| '_ \\  \n| (_) | \n \\___/  \n        ",
        " ______  \n|____  | \n    / /  \n   / /   \n  / /    \n /_/     \n         ",
        "  ___   \n / _ \\  \n| (_) | \n > _ <  \n| (_) | \n \\___/  \n        ",
        "  ___   \n / _ \\  \n| (_) | \n \\__, | \n   / /  \n  /_/   \n        "
    };
    private static String code;


    public static String generateCapthaCode() {
        final int[] numbers = stringToInt(getCode());
        return connectAsciiArts(numbers);
    }

    public static boolean isCodeCorrect(String clientAnswer) {
        return clientAnswer.equals(code);
    }


    private static String getCode() {
        final int randomSize = RANDOM.nextInt(4, 9);
        final String[] digits = new String[randomSize];
        for (int i = 0; i < digits.length; i++)
            digits[i] = Integer.toString(RANDOM.nextInt(10));
        code = String.join("", digits);
        return String.join(" ", digits);
    }

    private static int[] stringToInt(String digits) {
        final String[] string = digits.split(" ");
        final int[] numbers = new int[string.length];
        for (int i = 0; i < numbers.length; i++)
            numbers[i] = Integer.valueOf(string[i]);
        return numbers;
    }

    private static String connectAsciiArts(int[] numbers) {
        final String[] colors = getRandomColors(numbers.length);
        final String[] result = new String[LINES];
        final String[][] asciiArtLineLine = getAsciiArtLineLine(numbers);
        for (int i = 0; i < LINES; i++) {
            result[i] = "";
            for (int j = 0; j < numbers.length; j++)
                result[i] += colors[j] + makeNoise(asciiArtLineLine[j][i]);
        }
        return String.join("\n", result) + RESET;
    }

    private static String[][] getAsciiArtLineLine(int[] numbers) {
        final String[] numbersInAsciiArt = new String[numbers.length];
        final String[][] asciiArtLineLine = new String[numbers.length][];
        for (int i = 0; i < numbers.length; i++)
            numbersInAsciiArt[i] = NUMBERS_IN_ASCII_ART[numbers[i]];
        for (int i = 0; i < numbers.length; i++)
            asciiArtLineLine[i] = numbersInAsciiArt[i].split("\n");
        return asciiArtLineLine;
    }

    private static String[] getRandomColors(int length) {
        final String[] colors = new String[length];
        for (int i = 0; i < colors.length; i++)
            colors[i] = getARandomColor();
        return colors;
    }

    private static String getARandomColor() {
        int index = RANDOM.nextInt(COLORS.length);
        return COLORS[index];
    }

    private static String makeNoise(String input) {
        char[] string = input.toCharArray();
        int numberOfNoises = RANDOM.nextInt(6);
        while (numberOfNoises-- > 0) {
            int index = RANDOM.nextInt(input.length());
            string[index] = getARandomNoiseChar();
        }
        return String.valueOf(string);
    }

    private static char getARandomNoiseChar() {
        return NOISE_CHARACTERS.charAt(RANDOM.nextInt(NOISE_CHARACTERS.length()));
    }

}
