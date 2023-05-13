package com.example.controller.Methods;

import com.example.view.KeepMenu;

import java.util.Scanner;

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
}
