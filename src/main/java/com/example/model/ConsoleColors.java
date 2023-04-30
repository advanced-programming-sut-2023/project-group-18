package com.example.model;

public interface ConsoleColors {
    // Reset
    String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    String BLACK = "\033[0;30m";   // BLACK
    String RED = "\033[0;31m";     // RED
    String GREEN = "\033[0;32m";   // GREEN
    String YELLOW = "\033[0;33m";  // YELLOW
    String BLUE = "\033[0;34m";    // BLUE
    String PURPLE = "\033[0;35m";  // PURPLE
    String CYAN = "\033[0;36m";    // CYAN
    String WHITE = "\033[0;37m";   // WHITE

    // Bold
    String BLACK_BOLD = "\033[1;30m";  // BLACK
    String RED_BOLD = "\033[1;31m";    // RED
    String GREEN_BOLD = "\033[1;32m";  // GREEN
    String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    String BLUE_BOLD = "\033[1;34m";   // BLUE
    String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    String CYAN_BOLD = "\033[1;36m";   // CYAN
    String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Background
    String BLACK_BACKGROUND = "\033[40m";  // BLACK
    String RED_BACKGROUND = "\033[41m";    // RED
    String GREEN_BACKGROUND = "\033[42m";  // GREEN
    String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    String BLUE_BACKGROUND = "\033[44m";   // BLUE
    String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    String CYAN_BACKGROUND = "\033[46m";   // CYAN
    String WHITE_BACKGROUND = "\033[47m";  // WHITE

}
