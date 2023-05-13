package com.example.controller.Methods;

import com.example.view.KeepMenu;

import java.util.Scanner;

public class KeepMethods implements SelectBuildingMenuMethods{
    private static KeepMethods keepMethods;
    private KeepMethods() {

    }
    public static KeepMethods getKeepMethods() {
        return keepMethods == null ? keepMethods = new KeepMethods() : keepMethods;
    }
    @Override
    public void run(Scanner scanner) {
        KeepMenu.getKeepMenu().run(scanner);
    }
}
