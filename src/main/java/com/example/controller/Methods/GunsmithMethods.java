package com.example.controller.Methods;

import java.util.Scanner;

public class GunsmithMethods implements SelectBuildingMenuMethods{
    private static GunsmithMethods gunsmithMethods;
    private GunsmithMethods() {

    }
    public static GunsmithMethods getGunsmithMethods() {
        return gunsmithMethods == null ? gunsmithMethods = new GunsmithMethods() : gunsmithMethods;
    }
    @Override
    public void run(Scanner scanner) {
        GunsmithMethods.getGunsmithMethods().run(scanner);
    }
}
