package com.example.controller.Methods;

import java.util.Scanner;

public class GateMethods implements SelectBuildingMenuMethods{
    private static GateMethods gateMethods;
    private GateMethods() {

    }
    public static GateMethods getGateMethods() {
        return gateMethods == null ? gateMethods = new GateMethods() : gateMethods;
    }
    @Override
    public void run(Scanner scanner) {
        GateMethods.getGateMethods().run(scanner);
    }
}
